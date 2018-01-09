package com.meneger.model.druzyna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.meneger.model.osoba.Pilkarz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "DRUZYNY")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Druzyna {
    private static final int NAME_MAX_LENGTH = 30;

    @Id
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAZWA", length = NAME_MAX_LENGTH, unique = true)
    private String nazwa;

    @OneToMany(mappedBy = "druzyna", fetch = FetchType.LAZY)
    private Set<Pilkarz> pilkarze;

    @OneToMany(mappedBy = "druzyna", fetch = FetchType.LAZY)
    private Set<Sponsor> sponsorzy;

    public Set<Pilkarz> getPilkarze() {
        return pilkarze;
    }

    public void setPilkarze(Set<Pilkarz> pilkarze) {
        this.pilkarze = pilkarze;
    }

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

    public Set<Sponsor> getSponsorzy() {
        return sponsorzy;
    }

    public void setSponsorzy(Set<Sponsor> sponsorzy) {
        this.sponsorzy = sponsorzy;
    }

    @Override
    public String toString() {
        return "Druzyna{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    public void clear() {
        setSponsorzy(null);
        setPilkarze(null);
    }
}
