package com.meneger.controllers;

import com.meneger.model.druzyna.Druzyna;
import com.meneger.repositories.DruzynaRepository;
import com.meneger.repositories.MeczRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping(path = DruzynaRestController.CONTROLLER_BASE)
public class DruzynaRestController extends AbstractRestController<Druzyna, DruzynaRepository> {
    static final String CONTROLLER_BASE = "/druzyny";

    private MeczRepository meczRepository;
    private SimpleJdbcCall call;

    @Autowired
    public DruzynaRestController(DruzynaRepository teamRepository, MeczRepository meczRepository, @Qualifier("dataSource") DataSource dataSource) {
        super(teamRepository);
        this.meczRepository = meczRepository;
        this.call = new SimpleJdbcCall(new JdbcTemplate(dataSource))
                .withFunctionName("WygranychMeczyPrzezDruzyne");
    }


    @Override
    @GetMapping
    public ResponseEntity getList() {
        try {
            List<Druzyna> all = repository.findAll();
            all.forEach(e -> {
                e.prepare();
                e.setWygranychMeczy(getWygraneMecze(e.getId()));
            });
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    private Integer getWygraneMecze(Integer id) {
//        Druzyna druzyna = repository.findOne(id);

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_druzynaId", id);

        return call.executeFunction(Integer.class, paramMap);

//        return meczRepository.findAll()
//                .stream()
//                .filter(e -> gralaWmeczu(e, druzyna))
//                .mapToInt(e -> czyMeczWygralaDruzyna(e, druzyna))
//                .sum();
    }

    @GetMapping(path = "/{id}/wygrane")
    public ResponseEntity get(@PathVariable Integer id) {
        return ResponseEntity.ok(getWygraneMecze(id));
    }

//    private boolean gralaWmeczu(Mecz e, Druzyna druzyna) {
//        return e.getGospodarz().equals(druzyna) || e.getPrzyjezdni().equals(druzyna);
//    }

//    private Integer czyMeczWygralaDruzyna(Mecz m, Druzyna d) {
//        return czyMeczWygralaDruzyna(m.getWynikMeczu(), m.getGospodarz().equals(d));
//    }

//    private Integer czyMeczWygralaDruzyna(String val, boolean isGosp) {
//        SqlParameterSource paramMap = new MapSqlParameterSource()
//                .addValue("p_wynik", val)
//                .addValue("p_druzyna", (isGosp) ? 0 : 1);
//
//        return call.executeFunction(Integer.class, paramMap);
//    }

}
