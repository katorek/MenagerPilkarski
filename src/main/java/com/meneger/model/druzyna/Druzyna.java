package com.meneger.model.druzyna;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.meneger.model.Template;
import com.meneger.model.osoba.Pilkarz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "DRUZYNY")
public class Druzyna implements Template{
    private static final int NAME_MAX_LENGTH = 30;

    private Integer id;
    private String nazwa;
    private Liga liga;
    private Set<Pilkarz> pilkarze;
    private Set<Sponsor> sponsorzy;

    @Id @NotNull @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull @Column(name = "NAZWA", length = NAME_MAX_LENGTH, unique = true)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @ManyToOne @JoinColumn(name = "LIGA_ID")
    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    @OneToMany(mappedBy = "druzyna", fetch = FetchType.LAZY)
    public Set<Pilkarz> getPilkarze() {
        return pilkarze;
    }

    public void setPilkarze(Set<Pilkarz> pilkarze) {
        this.pilkarze = pilkarze;
    }

    @OneToMany(mappedBy = "druzyna", fetch = FetchType.LAZY)
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
                ", liga=" + liga +
                ", pilkarze=" + pilkarze +
                ", sponsorzy=" + sponsorzy +
                '}';
    }

    @Override
    public void prepare() {
        getPilkarze().forEach(Pilkarz::clear);
        getSponsorzy().forEach(Sponsor::clear);
        if(getLiga()!=null)getLiga().clear();
    }

    @Override
    public void clear() {
        setSponsorzy(null);
        setPilkarze(null);
        setLiga(null);
    }
}
