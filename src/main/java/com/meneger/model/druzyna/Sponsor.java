package com.meneger.model.druzyna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "SPONSORZY")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Sponsor {

    @Id @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAZWA")
    private String nazwa;

    @Column(name = "RODZAJ")
    private String rodzaj;

    @ManyToOne
    @JoinColumn(name = "DRUZYNA_ID")
    private Druzyna druzyna;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Druzyna getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyna druzyna) {
        this.druzyna = druzyna;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", rodzaj='" + rodzaj + '\'' +
                ", druzyna=" + druzyna +
                '}';
    }
}
