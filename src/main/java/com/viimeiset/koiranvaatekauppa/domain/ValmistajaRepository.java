package com.viimeiset.koiranvaatekauppa.domain;


import org.springframework.data.repository.CrudRepository;


public interface ValmistajaRepository extends CrudRepository <Valmistaja, Long> {
    Valmistaja findByNimi(String nimi);

}