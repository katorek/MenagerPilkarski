package com.meneger.model.osoba;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Osoba {
    private static final int NAME_MAX_LENGTH = 30;
    private static final int PESEL_LENGTH = 11;

    private Integer id;
    private String pesel;
    private String imie;
    private String nazwisko;

    @Id
    @NotNull
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PESEL", length = PESEL_LENGTH, unique = true)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @NotNull
    @Column(name = "IMIE", length = NAME_MAX_LENGTH)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @NotNull
    @Column(name = "NAZWISKO", length = NAME_MAX_LENGTH)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", pesel='" + pesel + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
