package com.springlessons.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlessons.dao.CRUD;
import com.springlessons.dao.SupplierDAO;
import com.springlessons.model.Supplier;

@Service
public class SupplierBO implements CRUD<Supplier, Long> {

	@Autowired
	private SupplierDAO dao;

	@Override
	public Supplier findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Supplier> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Supplier supplier) {
		dao.insert(supplier);
	}

	@Override
	public void update(Supplier supplier) {
		dao.update(supplier);
	}

	@Override
	public void delete(Supplier supplier) {
		dao.delete(supplier);
	}

	public void toogleStatus(Supplier supplier) {
		supplier.setStatus(!supplier.isStatus());
		dao.update(supplier);
	}
	
}
