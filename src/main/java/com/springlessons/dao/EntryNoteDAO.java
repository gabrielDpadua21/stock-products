package com.springlessons.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.springlessons.model.EntryNote;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteDAO implements CRUD<EntryNote, Long> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntryNote findById(Long id) {
		return entityManager.find(EntryNote.class, id);
	}

	@Override
	public List<EntryNote> findAll() {
		String hql = "FROM EntryNote";
		Query query = (Query) entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public void insert(EntryNote entryNote) {
		entityManager.persist(entryNote);
	}

	@Override
	public void update(EntryNote entryNote) {
		entityManager.merge(entryNote);
	}

	@Override
	public void delete(EntryNote entryNote) {
		entityManager.remove(entryNote);
	}
}
