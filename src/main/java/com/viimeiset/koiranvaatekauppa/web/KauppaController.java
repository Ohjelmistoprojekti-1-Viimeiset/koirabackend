package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;

import jakarta.validation.Valid;

import com.viimeiset.koiranvaatekauppa.domain.TuoteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import com.viimeiset.koiranvaatekauppa.domain.Valmistaja;
import org.springframework.web.bind.annotation.PostMapping;
import com.viimeiset.koiranvaatekauppa.domain.SignupForm;

@Controller
public class KauppaController {

	private final TuoteRepository repository;
	private final ValmistajaRepository valmistajaRepository;

	@Autowired
	public KauppaController(TuoteRepository repository, ValmistajaRepository valmistajaRepository) {
		this.repository = repository;
		this.valmistajaRepository = valmistajaRepository;
	}

	@Autowired
	private TuoteService tuoteService;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@PostMapping("/signup")
	public String signup(@Valid SignupForm signupForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		return "redirect:/success";
	}

	@GetMapping(value = { "/index" })
	public String index() {
		return "index";
	}

	@GetMapping(value = { "/tuotelista" })
	public String tuoteLista(Model model) {
		model.addAttribute("tuotteet", repository.findAll());
		return "tuotelista";
	}

	@GetMapping(value = { "/valmistajalista" })
	public String valmistajaLista(Model model) {
		model.addAttribute("valmistajat", valmistajaRepository.findAll());
		return "valmistajalista";
	}

	@GetMapping(value = "/lisaa")
	public String addTuote(Model model) {
		model.addAttribute("tuote", new Tuote());
		model.addAttribute("valmistaja", new Valmistaja());
		return "lisaa";
	}

	@PostMapping(value = "/lisaa")
	public String save(Tuote tuote, @RequestParam String valmistajaNimi) {
		Valmistaja valmistaja = tuote.getValmistaja();
		if (valmistaja == null) {
			valmistaja = new Valmistaja();
			tuote.setValmistaja(valmistaja);
		}
		valmistaja.setNimi(valmistajaNimi);

		tuoteService.saveTuoteWithValmistaja(tuote);
		return "redirect:tuotelista";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTuote(@PathVariable("id") Long tuoteId, Model model) {
		repository.deleteById(tuoteId);
		return "redirect:/tuotelista";
	}

	// Valmistajan poisto
	@RequestMapping(value = "/deleteValmistaja/{id}", method = RequestMethod.GET)
	public String deleteValmistaja(@PathVariable("id") Long valmistajaId, Model model) {
		valmistajaRepository.deleteById(valmistajaId);
		return "redirect:/valmistajalista";
	}

	@GetMapping(value = "/muokkaa/{id}")
	public String editBook(@PathVariable("id") Long tuoteId, Model model) {
		Tuote tuote = repository.findById(tuoteId).orElse(null);
		model.addAttribute("tuote", tuote);
		return "muokkaa";
	}

	@PostMapping(value = "/muokkaa/{id}")
	public String updateTuote(@PathVariable("id") Long tuoteId, Tuote tuote, Model model) {
		Tuote existingTuote = repository.findById(tuoteId).orElse(null);

		existingTuote.setTyyppi(tuote.getTyyppi());
		existingTuote.setVari(tuote.getVari());
		existingTuote.setKoko(tuote.getKoko());
		existingTuote.setHinta(tuote.getHinta());

		Valmistaja valmistaja = valmistajaRepository.findByNimi(tuote.getValmistaja().getNimi());
		if (valmistaja == null) {
			valmistaja = new Valmistaja();
			valmistaja.setNimi(tuote.getValmistaja().getNimi());
			valmistajaRepository.save(valmistaja);
		}

		existingTuote.setValmistaja(valmistaja);
		tuoteService.saveTuoteWithValmistaja(existingTuote);

		return "redirect:/tuotelista";
	}

}