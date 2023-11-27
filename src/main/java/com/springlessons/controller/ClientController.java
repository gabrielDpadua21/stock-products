package com.springlessons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springlessons.bo.ClientBO;
import com.springlessons.model.Client;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientBO bo;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newClient(ModelMap model) {
		model.addAttribute("client", new Client());
		return new ModelAndView("/client/form", model);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listClient(ModelMap model) {
		model.addAttribute("clients", bo.findAll());
		return new ModelAndView("/client/list", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute Client client, BindingResult result, RedirectAttributes att) {
		if(result.hasErrors()) return "/client/form";
		
		if(client.getId() == null)
			bo.insert(client);
		else
			bo.update(client);
		
		att.addFlashAttribute("feedback", "Dados salvos com sucesso!");
		
		return "redirect:/clients/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("client", bo.findById(id));
		return new ModelAndView("client/form", model);
	}
	
	@RequestMapping(value = "/desactive/{id}", method = RequestMethod.GET)
	public String desactive(@PathVariable("id") Long id, RedirectAttributes att) {
		Client client = bo.findById(id);
		bo.toogleStatus(client);
		att.addFlashAttribute("feedback", "Cliente desativado com sucesso!");
		return "redirect:/clients/list";
	}
	
	@RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
	public String active(@PathVariable("id") Long id, RedirectAttributes att) {
		Client client = bo.findById(id);
		bo.toogleStatus(client);
		att.addFlashAttribute("feedback", "Cliente ativado com sucesso!");
		return "redirect:/clients/list";
	}
}	







