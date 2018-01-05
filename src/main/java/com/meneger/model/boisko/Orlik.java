package com.meneger.model.boisko;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "ORLIKI")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonSerialize
public class Orlik{
    private Integer id;
    private Boisko boisko;

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "boisko"))
    @GeneratedValue(generator = "generator")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne @JoinColumn(name = "boisko")
    public Boisko getBoisko() {
        return boisko;
    }

    public void setBoisko(Boisko boisko) {
        this.boisko = boisko;
    }
}
