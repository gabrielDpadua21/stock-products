package com.springlessons.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlessons.dao.CRUD;
import com.springlessons.dao.EntryNoteDAO;
import com.springlessons.model.EntryNote;

@Service
public class EntryNoteBO implements CRUD<EntryNote, Long> {
	
	@Autowired
	private EntryNoteDAO dao;

	@Override
	public EntryNote findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<EntryNote> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(EntryNote entryNote) {
		dao.insert(entryNote);
	}

	@Override
	public void update(EntryNote entryNote) {
		dao.update(entryNote);
	}

	@Override
	public void delete(EntryNote entryNote) {
		dao.delete(entryNote);
	}
}
