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
import com.springlessons.bo.EntryNoteItemBO;
import com.springlessons.bo.ProductBO;
import com.springlessons.model.EntryNote;
import com.springlessons.model.EntryNoteItem;

import jakarta.validation.Valid;

@Controller
@RequestMapping(name="/entry-note-item")
public class EntryNoteItemController {
	@Autowired
	private EntryNoteBO entryNoteBO;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private EntryNoteItemBO bo;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute EntryNoteItem entryNoteItem, BindingResult result, RedirectAttributes att, ModelMap model) {
		
		Long productId = entryNoteItem.getProduct().getId();
		
		if(productId == null) result.reject("product", "field.required");
		
		if (bo.itemDuplicate(entryNoteItem)) result.reject("product", "duplicate");
		
		if(result.hasErrors()) {
			model.addAttribute("products", productBO.findAll());
			return "/entryNoteItem/form";
		}
		
		EntryNote entryNote = entryNoteBO.findById(entryNoteItem.getEntryNote().getId());
		
		entryNoteItem.setEntryNote(entryNote);
		
		if (entryNoteItem.getId() == null)
			bo.insert(entryNoteItem);
		else
			bo.update(entryNoteItem);
		
		att.addFlashAttribute("feedback", "Dados salvos com sucesso!");
		
		Long entryNoteId = entryNote.getId();
		
		return "redirect:/entryNote/edit/" + entryNoteId;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, RedirectAttributes att, ModelMap model) {
		model.addAttribute("entryNoteItem", bo.findById(id));
		model.addAttribute("products", productBO.findAll());
		return new ModelAndView("entryNoteItem/form", model);
	}
}












