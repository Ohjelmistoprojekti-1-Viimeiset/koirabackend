package com.viimeiset.koiranvaatekauppa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.viimeiset.koiranvaatekauppa.domain.Koko;
import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
import com.viimeiset.koiranvaatekauppa.domain.Tyyppi;
import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;

@SpringBootTest(classes = KoiranvaatekauppaApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TuoteTest {
	
	
	
	// injektoi TuoteRepossitoryn
		@Autowired
		private TuoteRepository repository;
		
	// injektoi ValmistajaRepossitoryn
		@Autowired
		ValmistajaRepository valmistajaRepository;
		
		
		// Testi tuotteen lisäämiseen
		@Test
		public void addTuote() {
		// luodaan uusi tuote
		Tuote tuote = new Tuote("Nappitakki", Tyyppi.VAATE, "Musta", Koko.M, 29.99,
				valmistajaRepository.findByNimi("Northface"), 10);
		// tallennetaan repositoryn avulla uusi tuote
		repository.save(tuote);
		// testataan löytyykö uusi tuote tietokannasta
		assertThat(tuote.getId()).isNotNull();
			
		}
		
		
		// Testi tuotteen poistamiseen
		
		@Test 
		public void removeTuote() {
		// tuodaan listaan tuote jonka teidetään olevan tietokanassa
		List<Tuote> tuotteet = repository.findByTuoteNimi("Takki");
		Tuote tuote = tuotteet.get(0);
		// poistetaan tuote repositoryn avulla
		repository.delete(tuote);
		// koitetaan tuoda poistrettu tuote uuteen listaan
		List<Tuote> newTuotteet = repository.findByTuoteNimi("Takki");
		// jos tuotteen poisto on onnistunut uuden listan koko on nolla
		assertThat(newTuotteet).hasSize(0);
		
		}
		

}
