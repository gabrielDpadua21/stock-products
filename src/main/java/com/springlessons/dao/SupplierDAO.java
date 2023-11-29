package com.springlessons.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.springlessons.model.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SupplierDAO implements CRUD<Supplier, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Supplier findById(Long id) {
		return entityManager.find(Supplier.class, id);
	}

	@Override
	public List<Supplier> findAll() {
	    String hql = "FROM Supplier";
	    Query query = (Query) entityManager.createQuery(hql);
	    return query.getResultList();
	}

	@Override
	public void insert(Supplier supplier) {
		entityManager.persist(supplier);
	}

	@Override
	public void update(Supplier supplier) {
		entityManager.merge(supplier);
	}

	@Override
	public void delete(Supplier supplier) {
		entityManager.remove(supplier);
	}
	
}
