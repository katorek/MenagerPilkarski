package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.meneger.model.mecz.Mecz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@MappedSuperclass
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Boisko {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "ILOSC_MIEJSC")
    private Integer iloscMiejsc;

    @NotNull
    @Column(name = "MIEJSCOWOSC")
    private String miejscowosc;

    @NotNull
    @Column(name = "nazwa")
    private String nazwa;

    @OneToMany(mappedBy = "boisko", fetch = FetchType.LAZY)
    private Set<Mecz> mecze;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIloscMiejsc() {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(Integer iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Set<Mecz> getMecze() {
        return mecze;
    }

    public void setMecze(Set<Mecz> mecze) {
        this.mecze = mecze;
    }
}
