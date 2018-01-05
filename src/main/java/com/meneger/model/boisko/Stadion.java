package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "STADIONY")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Stadion {
    private Integer id;
    private Boisko boisko;
    private boolean chroniPrzedDeszczem;

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "boisko"))
    @GeneratedValue(generator = "generator")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CHRONI_PRZED_DESZCZEM", columnDefinition = "TINYINT(1)")
    public boolean isChroniPrzedDeszczem() {
        return chroniPrzedDeszczem;
    }

    public void setChroniPrzedDeszczem(boolean chroniPrzedDeszczem) {
        this.chroniPrzedDeszczem = chroniPrzedDeszczem;
    }

    @OneToOne @JoinColumn(name = "boisko")
    public Boisko getBoisko() {
        return boisko;
    }

    public void setBoisko(Boisko boisko) {
        this.boisko = boisko;
    }


}
