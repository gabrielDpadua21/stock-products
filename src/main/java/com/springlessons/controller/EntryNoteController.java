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

import com.springlessons.bo.EntryNoteBO;
import com.springlessons.bo.ProductBO;
import com.springlessons.bo.SupplierBO;
import com.springlessons.model.EntryNote;
import com.springlessons.model.EntryNoteItem;
import com.springlessons.model.Supplier;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/entry-notes")
public class EntryNoteController {
	@Autowired
	private EntryNoteBO bo;
	
	@Autowired
	private SupplierBO supplierBo;
	
	@Autowired
	private ProductBO productBO;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newEntryNote(ModelMap model) {
		Long supplierId = null;
		model.addAttribute("supplierId", supplierId);
		model.addAttribute("note", new EntryNote());
		model.addAttribute("suppliers", new Supplier());
		return new ModelAndView("/entryNote/form", model);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listEntryNote(ModelMap model) {
		model.addAttribute("notes", bo.findAll());
		return new ModelAndView("/entryNote/list", model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute EntryNote entryNote, BindingResult result, RedirectAttributes att) {
		if(result.hasErrors()) return "/entryNote/form";
		
		if(entryNote.getId() == null)
			bo.insert(entryNote);
		else
			bo.update(entryNote);
		
		att.addFlashAttribute("feedback", "Dados salvos com sucesso!");
		
		return "redirect:/entry-notes/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("notes", bo.findById(id));
		return new ModelAndView("entryNote/form", model);
	}
	
	@RequestMapping(value="/{id}/item", method = RequestMethod.GET)
	public ModelAndView product(@PathVariable("id") Long id, ModelMap model) {
		EntryNoteItem item = new EntryNoteItem();
		EntryNote note = bo.findById(id);
		model.addAttribute("entryNoteItem", item);
		model.addAttribute("EntryNote", note);
		model.addAttribute("products", productBO.findAll());
		return new ModelAndView("/entryNoteItem/form", model);
	}
}
