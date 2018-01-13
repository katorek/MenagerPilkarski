package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.meneger.model.Template;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "STADIONY")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "id")
public class Stadion implements Template{
    private Integer id;
    private Boisko boisko;
    private boolean chroniPrzedDeszczem;

    @Id
    @GeneratedValue
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

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "boisko")
    public Boisko getBoisko() {
        return boisko;
    }

    public void setBoisko(Boisko boisko) {
        this.boisko = boisko;
    }

    @Override
    public String toString() {
        return "Stadion{" +
                "id=" + id +
                ", boisko=(" + boisko.getNazwa()+"id: " + boisko.getId()+
                "), chroniPrzedDeszczem=" + chroniPrzedDeszczem +
                '}';
    }

    @Override
    public void prepare() {
        getBoisko().clear();
    }

    @Override
    public void clear() {
//        setBoisko(null);
    }
}
