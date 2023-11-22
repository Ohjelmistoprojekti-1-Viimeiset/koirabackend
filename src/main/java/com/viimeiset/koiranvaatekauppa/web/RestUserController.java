package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.viimeiset.koiranvaatekauppa.domain.AppUser;
import com.viimeiset.koiranvaatekauppa.domain.AppUserRepository;

@RestController
@RequestMapping("/api/kayttajat")
public class RestUserController {

	@Autowired
	AppUserRepository userRepository;

	// Tämä rest metodi on toistaiseksi vain manuaalitestausta varten.

	@GetMapping
	public Iterable<AppUser> getKayttajat() {
		return userRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<AppUser> luoKayttaja(@RequestBody AppUser user) {
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
