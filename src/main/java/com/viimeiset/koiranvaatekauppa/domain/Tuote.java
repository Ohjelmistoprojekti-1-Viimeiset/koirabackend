package com.viimeiset.koiranvaatekauppa.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tyyppi;
    private String vari;
    private String koko;
    private double hinta;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    public Valmistaja valmistaja;

    public Tuote() {

    }

    public Tuote(String tyyppi, String vari, String koko, double hinta, Valmistaja valmistaja) {
        this.tyyppi = tyyppi;
        this.vari = vari;
        this.koko = koko;
        this.hinta = hinta;
        this.valmistaja = valmistaja;
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

    public String getVari() {
        return this.vari;
    }

    public void setVari(String vari) {
        this.vari = vari;
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