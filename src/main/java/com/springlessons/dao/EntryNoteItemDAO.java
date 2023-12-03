package com.springlessons.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.springlessons.model.EntryNote;
import com.springlessons.model.EntryNoteItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteItemDAO implements CRUD<EntryNoteItem, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntryNoteItem findById(Long id) {
		return entityManager.find(EntryNoteItem.class, id);
	}

	@Override
	public List<EntryNoteItem> findAll() {
		String hql = "FROM EntryNoteItem";
		Query query = (Query) entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public void insert(EntryNoteItem item) {
		entityManager.persist(item);
	}

	@Override
	public void update(EntryNoteItem item) {
		entityManager.merge(item);
	}

	@Override
	public void delete(EntryNoteItem item) {
		entityManager.remove(item);
	}
	
	public List<EntryNoteItem> listItems(long id) {
		String hql = "From EntryNoteITem where entryNote.id := id";
		Query query = (Query) entityManager.createQuery(hql).setParameter("id", id);
		return query.getResultList();
	}
	
}
