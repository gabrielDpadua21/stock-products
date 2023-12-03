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

	public boolean itemDuplicate(EntryNoteItem item) {
		Long entryNoteId = item.getEntryNote().getId();
		List<EntryNoteItem> itens = dao.listItems(entryNoteId);
		Long productId = item.getProduct().getId();
		if (item.getId() == null) {
			for (EntryNoteItem entryNoteItem : itens) {
				if (entryNoteItem.getProduct().getId() == productId) return false;
			}
			return false;
		} else {
			Long entryNoteItemId = item.getId();
			for (EntryNoteItem entryNoteItem : itens) {
				if (entryNoteItem.getProduct().getId() == productId && entryNoteItemId == entryNoteItem.getId()) return false;
			}
			return false;
		}
	}
	
}
