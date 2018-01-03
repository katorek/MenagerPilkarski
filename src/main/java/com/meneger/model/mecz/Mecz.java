package com.meneger.model.mecz;

import com.meneger.model.boisko.Boisko;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mecz {

    @ManyToOne
    @JoinColumn(name = "BOISKO_ID")
    private Boisko boisko;

}
