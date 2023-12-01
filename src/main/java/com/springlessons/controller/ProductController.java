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

import com.springlessons.bo.ProductBO;
import com.springlessons.model.Product;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductBO bo;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newProduct(ModelMap model) {
		model.addAttribute("product", new Product());
		return new ModelAndView("/product/form", model);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listProduct(ModelMap model) {
		model.addAttribute("product", bo.findAll());
		return new ModelAndView("/product/list", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute Product product, BindingResult result, RedirectAttributes att) {
		if(result.hasErrors()) return "/client/form";
		
		if(product.getId() == null)
			bo.insert(product);
		else
			bo.update(product);
		
		att.addFlashAttribute("feedback", "Dados salvos com sucesso!");
		
		return "redirect:/products/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("product", bo.findById(id));
		return new ModelAndView("product/form", model);
	}
	
	@RequestMapping(value = "/desactive/{id}", method = RequestMethod.GET)
	public String desactive(@PathVariable("id") Long id, RedirectAttributes att) {
		Product product = bo.findById(id);
		bo.toogleStatus(product);
		att.addFlashAttribute("feedback", "Cliente desativado com sucesso!");
		return "redirect:/products/list";
	}
	
	@RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
	public String active(@PathVariable("id") Long id, RedirectAttributes att) {
		Product product = bo.findById(id);
		bo.toogleStatus(product);
		att.addFlashAttribute("feedback", "Cliente ativado com sucesso!");
		return "redirect:/products/list";
	}
	
}
