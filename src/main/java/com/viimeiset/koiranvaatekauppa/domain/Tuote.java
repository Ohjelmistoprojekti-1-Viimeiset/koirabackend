package com.viimeiset.koiranvaatekauppa.domain;

import jakarta.persistence.*;

@Entity
public class Tuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tyyppi;
    private String väri;
    private String koko;
    private double hinta;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    public Valmistaja valmistaja;

    public Tuote() {

    }

    public Tuote(String tyyppi, String väri, String koko, double hinta, Valmistaja Valmistaja) {
        this.tyyppi = tyyppi;
        this.väri = väri;
        this.koko = koko;
        this.hinta = hinta;
        this.valmistaja = Valmistaja;
    }

    public Long getId() {
        return this.id;
    }


    public String getTyyppi() {
        return this.tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getVäri() {
        return this.väri;
    }

    public void setVäri(String väri) {
        this.väri = väri;
    }

    public String getKoko() {
        return this.koko;
    }

    public void setKoko(String koko) {
        this.koko = koko;
    }

    public double getHinta() {
        return this.hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Valmistaja getValmistaja() {
        return valmistaja;
    }

}