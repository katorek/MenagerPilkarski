package com.meneger.model.druzyna;

import com.meneger.model.Template;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "LIGI")
public class Liga implements Template {
    private Integer id;
    private String nazwa;
    private Set<Druzyna> druzyny;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "NAZWA", unique = true)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @OneToMany(mappedBy = "liga", fetch = FetchType.LAZY)
    public Set<Druzyna> getDruzyny() {
        return druzyny;
    }

    public void setDruzyny(Set<Druzyna> druzyny) {
        this.druzyny = druzyny;
    }

    @Override
    public void prepare() {
        if (getDruzyny() != null)
            getDruzyny().forEach(Druzyna::clear);
    }

    @Override
    public void clear() {
        setDruzyny(null);
    }
}
