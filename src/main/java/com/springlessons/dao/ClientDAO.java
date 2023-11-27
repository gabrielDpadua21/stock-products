package com.springlessons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springlessons.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.query.Query;


@Repository
@Transactional
public class ClientDAO implements CRUD<Client, Long>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Client findById(Long id) {
		return entityManager.find(Client.class, id);
	}

	@Override
	public List<Client> findAll() {
	    String hql = "FROM Client";
	    Query query = (Query) entityManager.createQuery(hql);
	    return query.getResultList();
	}

	@Override
	public void insert(Client client) {
		entityManager.persist(client);
	}

	@Override
	public void update(Client client) {
		entityManager.merge(client);
	}

	@Override
	public void delete(Client client) {
		entityManager.remove(client);
	}
	
	
	
}
