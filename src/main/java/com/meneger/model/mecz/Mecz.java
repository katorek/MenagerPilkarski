package com.meneger.model.mecz;

import com.meneger.model.boisko.Boisko;
import com.meneger.model.druzyna.Druzyna;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MECZE")
public class Mecz {
    private Integer id;
    private Boisko boisko;
    private Date data;

    private Double cenaBiletu;
    private String wynikMeczu;

    private Druzyna gospodarz;
    private Druzyna przyjezdni;
    private Set<Bilet> bilety;

    @Column(name = "CENA_BILETU")
    public Double getCenaBiletu() {
        return cenaBiletu;
    }

    public void setCenaBiletu(Double cenaBiletu) {
        this.cenaBiletu = cenaBiletu;
    }

    @Column(name = "WYNIK_MECZU")
    public String getWynikMeczu() {
        return wynikMeczu;
    }

    public void setWynikMeczu(String wynikMeczu) {
        this.wynikMeczu = wynikMeczu;
    }

    @ManyToOne @JoinColumn(name="GOSPODARZ_ID")
    public Druzyna getGospodarz() {
        return gospodarz;
    }

    public void setGospodarz(Druzyna gospodarz) {
        this.gospodarz = gospodarz;
    }

    @ManyToOne @JoinColumn(name="PRZYJEZDNI_ID")
    public Druzyna getPrzyjezdni() {
        return przyjezdni;
    }

    public void setPrzyjezdni(Druzyna przyjezdni) {
        this.przyjezdni = przyjezdni;
    }

    @Id @GeneratedValue @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DATA")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @ManyToOne @JoinColumn(name = "BOISKO_ID")
    public Boisko getBoisko() {
        return boisko;
    }

    public void setBoisko(Boisko boisko) {
        this.boisko = boisko;
    }

    @OneToMany(mappedBy = "mecz", fetch = FetchType.LAZY)
    public Set<Bilet> getBilety() {
        return bilety;
    }

    public void setBilety(Set<Bilet> bilety) {
        this.bilety = bilety;
    }

    public void clear() {
        setBoisko(null);
        setBilety(null);
        setGospodarz(null);
        setPrzyjezdni(null);
    }
}
