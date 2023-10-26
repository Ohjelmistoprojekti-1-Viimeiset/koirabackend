package com.viimeiset.koiranvaatekauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.viimeiset.koiranvaatekauppa.domain.TuoteRepository;
//import com.viimeiset.koiranvaatekauppa.domain.ValmistajaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.viimeiset.koiranvaatekauppa.domain.Tuote;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class KauppaController {
@Autowired
	private TuoteRepository repository; 
    //private ValmistajaRepository valmistajaRepository;


    @GetMapping(value= {"/tuotelista"})
    public String tuoteLista(Model model) {	
        model.addAttribute("tuotteet", repository.findAll());
        return "tuotelista";
    }

    @RequestMapping(value = "/lisaa")
	public String addTuote(Model model){
	model.addAttribute("tuote"
	, new Tuote());
	return "lisaa";}

    @PostMapping(value = "/tallenna")
	public String save(Tuote tuote){
	repository.save(tuote);
	return "redirect:tuotelista";
	}

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTuote(@PathVariable("id") Long tuoteId, Model model) {
	repository.deleteById(tuoteId);
	return "redirect:/tuotelista";
	}
    
    //@RequestMapping(value = "/edit/{id}")
	//public String editBook(@PathVariable("id") Long bookId, Model model){
	//model.addAttribute("book", repository.findById(bookId));
	//return "editbook";
	//}
    
}
