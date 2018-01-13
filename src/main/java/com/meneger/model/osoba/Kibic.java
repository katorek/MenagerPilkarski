package com.meneger.model.osoba;

import com.meneger.model.Template;
import com.meneger.model.mecz.Bilet;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "KIBICE")
public class Kibic extends Osoba implements Template{
    private boolean znizka;
    private Set<Bilet> bilety;

    @Column(name = "ZNIZKA", columnDefinition = "TINYINT(1)")
    public boolean isZnizka() {
        return znizka;
    }

    public void setZnizka(boolean znizka) {
        this.znizka = znizka;
    }

    @OneToMany(mappedBy = "kibic", fetch = FetchType.LAZY)
    public Set<Bilet> getBilety() {
        return bilety;
    }

    public void setBilety(Set<Bilet> bilety) {
        this.bilety = bilety;
    }

    @Override
    public void prepare() {
        getBilety().forEach(Bilet::clear);
    }

    @Override
    public void clear() {
        setBilety(null);
    }

}
