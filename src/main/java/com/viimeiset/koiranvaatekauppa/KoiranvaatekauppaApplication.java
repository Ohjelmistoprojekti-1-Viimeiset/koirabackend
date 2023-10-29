package com.viimeiset.koiranvaatekauppa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;
import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import com.viimeiset.koiranvaatekauppa.domain.Valmistaja;

@SpringBootApplication
public class KoiranvaatekauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KoiranvaatekauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KoiranvaatekauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(TuoteRepository tuoteRepository, ValmistajaRepository valmistajaRepository) {
		return (args) -> {
			log.info("Tallennetaan tuote ja valmistajatiedot");

			Valmistaja valmistaja1 = new Valmistaja("Northface");
			Valmistaja valmistaja2 = new Valmistaja("Adidas");
			Valmistaja valmistaja3 = new Valmistaja("Puma");
			valmistajaRepository.saveAll(Arrays.asList(valmistaja1, valmistaja2, valmistaja3));

			Tuote tuote1 = new Tuote("Takki", "Musta", "M", 29.99, valmistaja1);
			Tuote tuote2 = new Tuote("Kengät", "Sininen", "L", 59.99, valmistaja2);
			Tuote tuote3 = new Tuote("Liivi", "Punainen", "S", 14.99, valmistaja3);
			tuoteRepository.saveAll(Arrays.asList(tuote1, tuote2, tuote3));

			log.info("Lisätään kaikki data.");
			for (Tuote tuote : tuoteRepository.findAll()) {
				log.info(tuote.toString());
			}
		};

	}

}