package com.viimeiset.koiranvaatekauppa.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
public class Valmistaja {

  @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimi;

    public Valmistaja() {

    }


    public Valmistaja(String nimi) {
        this.nimi = nimi;
    }



    public Long getId() {
        return this.id;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }


}