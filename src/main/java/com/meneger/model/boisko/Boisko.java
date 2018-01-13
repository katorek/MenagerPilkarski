package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meneger.model.Template;
import com.meneger.model.mecz.Mecz;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "BOISKA")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.IntSequenceGenerator.class,
//        property = "id")
@JsonSerialize
public class Boisko implements Template {
    private Integer id;//
    private Integer iloscMiejsc;//
    private String miejscowosc;//
    private String nazwa;//
    private Orlik orlik;//
    private Stadion stadion;//
    private Set<Mecz> mecze;

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
    @Column(name = "ILOSC_MIEJSC")
    public Integer getIloscMiejsc() {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(Integer iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }

    @NotNull
    @Column(name = "MIEJSCOWOSC")
    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    @NotNull
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @OneToMany(mappedBy = "boisko", fetch = FetchType.LAZY)
    public Set<Mecz> getMecze() {
        return mecze;
    }

    public void setMecze(Set<Mecz> mecze) {
        this.mecze = mecze;
    }

    @Nullable
    @OneToOne(mappedBy = "boisko")
    public Orlik getOrlik() {
        return orlik;
    }

    public void setOrlik(Orlik orlik) {
        this.orlik = orlik;
    }

    @Nullable
    @OneToOne(mappedBy = "boisko")
    public Stadion getStadion() {
        return stadion;
    }

    public void setStadion(Stadion stadion) {
        this.stadion = stadion;
    }

    @Override
    public String toString() {
        return "Boisko{" +
                "id=" + id +
                ", iloscMiejsc=" + iloscMiejsc +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", orlik=" + orlik +
                ", stadion=" + stadion +
                ", mecze=" + mecze +
                '}';
    }

    @Override
    public void prepare() {
        getMecze().forEach(Mecz::clear);
        if(getOrlik()!=null)getOrlik().clear();
        if(getStadion()!=null)getStadion().clear();
    }

    @Override
    public void clear() {
        setStadion(null);
        setOrlik(null);
        setMecze(null);
    }
}
