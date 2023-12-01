package com.springlessons.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.springlessons.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductDAO implements CRUD<Product, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Product findById(Long id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
	    String hql = "FROM Product";
	    Query query = (Query) entityManager.createQuery(hql);
	    return query.getResultList();
	}

	@Override
	public void insert(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void delete(Product product) {
		entityManager.remove(product);
	}
	
}
