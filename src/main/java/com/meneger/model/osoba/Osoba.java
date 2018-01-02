package com.meneger.model.osoba;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Osoba {
    private static final int NAME_MAX_LENGTH = 30;
    private static final int PESEL_LENGTH = 11;

    @Id
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PESEL", length = PESEL_LENGTH, unique = true)
    private String pesel;

    @NotNull
    @Column(name = "IMIE", length = NAME_MAX_LENGTH)
    private String imie;

    @NotNull
    @Column(name = "NAZWISKO",length = NAME_MAX_LENGTH)
    private String nazwisko;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

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
