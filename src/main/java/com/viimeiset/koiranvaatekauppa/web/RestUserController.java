package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viimeiset.koiranvaatekauppa.domain.AppUser;
import com.viimeiset.koiranvaatekauppa.domain.AppUserRepository;

@RestController
@RequestMapping("/api/kayttajat")
public class RestUserController {
	
	@Autowired
	AppUserRepository userRepository;

	//Tämä rest metodi on toistaiseksi vain manuaalitestausta varten.
	
		@GetMapping
		public Iterable<AppUser> getKayttajat() {
			return userRepository.findAll();
		}
		
		@PostMapping
		public AppUser luoKayttaja(AppUser user) {
			return userRepository.save(user);
		}
	
}