package com.springlessons.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlessons.dao.CRUD;
import com.springlessons.dao.EntryNoteItemDAO;
import com.springlessons.model.EntryNoteItem;

@Service
public class EntryNoteItemBO implements CRUD<EntryNoteItem, Long> {

	@Autowired
	private EntryNoteItemDAO dao;

	@Override
	public EntryNoteItem findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<EntryNoteItem> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(EntryNoteItem item) {
		dao.insert(item);
	}

	@Override
	public void update(EntryNoteItem item) {
		dao.update(item);
	}

	@Override
	public void delete(EntryNoteItem item) {
		dao.delete(item);
	}

	
}
