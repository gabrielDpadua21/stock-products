package com.springlessons.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlessons.dao.CRUD;
import com.springlessons.dao.ProductDAO;
import com.springlessons.model.Product;

@Service
public class ProductBO implements CRUD<Product, Long> {

	@Autowired
	private ProductDAO dao;

	@Override
	public Product findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Product product) {
		dao.insert(product);
	}

	@Override
	public void update(Product product) {
		dao.update(product);
	}

	@Override
	public void delete(Product product) {
		dao.delete(product);
	}

	public void toogleStatus(Product product) {
		product.setStatus(!product.isStatus());
		dao.update(product);
	}
	
}
