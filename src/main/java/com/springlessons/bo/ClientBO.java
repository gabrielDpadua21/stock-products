package com.springlessons.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlessons.dao.CRUD;
import com.springlessons.dao.ClientDAO;
import com.springlessons.model.Client;

@Service
public class ClientBO implements CRUD<Client, Long> {
	
	@Autowired
	private ClientDAO dao;

	@Override
	public Client findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Client> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Client client) {
		dao.insert(client);
	}

	@Override
	public void update(Client client) {
		dao.update(client);
	}

	@Override
	public void delete(Client client) {
		dao.delete(client);
	}

	public void toogleStatus(Client client) {
		client.setStatus(!client.isStatus());
		dao.update(client);
	}

}













