package com.meneger.model.mecz;

import com.meneger.model.Template;
import com.meneger.model.osoba.Kibic;

import javax.persistence.*;

@Entity
@Table(name = "BILETY")
public class Bilet implements Template{
    private Integer id;
    private Mecz mecz;
    private double znizka;
    private Kibic kibic;

    @Id @GeneratedValue @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne @JoinColumn(name="MECZ_ID")
    public Mecz getMecz() {
        return mecz;
    }

    public void setMecz(Mecz mecz) {
        this.mecz = mecz;
    }

    @Column(name="ZNIZKA")
    public double getZnizka() {
        return znizka;
    }

    public void setZnizka(double znizka) {
        this.znizka = znizka;
    }

    @ManyToOne @JoinColumn(name="KIBIC_ID")
    public Kibic getKibic() {
        return kibic;
    }

    public void setKibic(Kibic kibic) {
        this.kibic = kibic;
    }

    @Override
    public void prepare() {
        getMecz().clear();
        getKibic().clear();
    }

    @Override
    public void clear(){
        setMecz(null);
        setKibic(null);
    }
}
