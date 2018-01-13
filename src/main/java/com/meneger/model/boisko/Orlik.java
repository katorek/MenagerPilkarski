package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meneger.model.Template;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "ORLIKI")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "id")
@JsonSerialize
public class Orlik implements Template{
    private Integer id;
    private Boisko boisko;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Orlik{" +
                "id=" + id +
                ", boisko=" + boisko +
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
