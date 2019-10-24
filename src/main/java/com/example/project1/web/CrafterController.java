package com.example.project1.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.project1.domain.Crafter;
import com.example.project1.domain.CrafterRepository;

@Controller
public class CrafterController {
	@Autowired
	private CrafterRepository crafterRepo;

	@RequestMapping("/crafters")
	public String crafters(Model model) {
		model.addAttribute("crafters", crafterRepo.findAll());
		return "crafterList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("/addCrafter")
	public String addCrafter(Model model) {
		model.addAttribute("crafter", new Crafter());
		return "addCrafter";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveNewCrafter", method = RequestMethod.POST)
	public String save(@Valid Crafter crafter, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addCrafter";
		}
		crafterRepo.save(crafter);
		return "redirect:crafters";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editCrafter/{id}")
	public String editCrafter(@PathVariable("id") Long crafterId, Model model) {
		model.addAttribute("crafter", crafterRepo.findById(crafterId));
		return "editCrafter";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveCrafter", method = RequestMethod.POST)
	public String saveCrafter(@Valid Crafter crafter, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "editCrafter";
		}
		crafterRepo.save(crafter);
		return "redirect:crafters";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteCrafter/{id}", method = RequestMethod.GET)
	public String deleteCrafter(@PathVariable("id") Long crafterId, Model model) {
		crafterRepo.deleteById(crafterId);
		return "redirect:../crafters";
	}
}
