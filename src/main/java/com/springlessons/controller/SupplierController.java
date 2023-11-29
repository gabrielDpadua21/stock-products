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

import com.springlessons.bo.SupplierBO;
import com.springlessons.model.Supplier;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	private SupplierBO bo;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newSupplier(ModelMap model) {
		model.addAttribute("supplier", new Supplier());
		return new ModelAndView("/supplier/form", model);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listSupplier(ModelMap model) {
		model.addAttribute("suppliers", bo.findAll());
		return new ModelAndView("/supplier/list", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute Supplier supplier, BindingResult result, RedirectAttributes att) {
		if(result.hasErrors()) return "/supplier/form";
		
		if(supplier.getId() == null)
			bo.insert(supplier);
		else
			bo.update(supplier);
		
		att.addFlashAttribute("feedback", "Dados salvos com sucesso!");
		
		return "redirect:/suppliers/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("supplier", bo.findById(id));
		return new ModelAndView("supplier/form", model);
	}
	
	@RequestMapping(value = "/desactive/{id}", method = RequestMethod.GET)
	public String desactive(@PathVariable("id") Long id, RedirectAttributes att) {
		Supplier supplier = bo.findById(id);
		bo.toogleStatus(supplier);
		att.addFlashAttribute("feedback", "Fornecedor desativado com sucesso!");
		return "redirect:/suppliers/list";
	}
	
	@RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
	public String active(@PathVariable("id") Long id, RedirectAttributes att) {
		Supplier supplier = bo.findById(id);
		bo.toogleStatus(supplier);
		att.addFlashAttribute("feedback", "Fornecedor ativado com sucesso!");
		return "redirect:/suppliers/list";
	}
	
}
