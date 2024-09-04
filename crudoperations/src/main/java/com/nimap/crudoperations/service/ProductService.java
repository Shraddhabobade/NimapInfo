package com.nimap.crudoperations.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimap.crudoperations.dao.ProductDao;
import com.nimap.crudoperations.entity.Category;
import com.nimap.crudoperations.entity.Product;


@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public String addProduct(Product p) {
		String msg = dao.addProduct(p);
		if(Objects.isNull(msg)) {
			msg = "Product is Not Added";
		}return msg;
	}
	
	public String updateProduct(Product p) {
		String msg = dao.updateProduct(p);
		if(Objects.isNull(msg)) {
			msg = "Data is not updated..";
		}return msg;
	}
	
	public String deleteProduct(int id) {
		String msg = dao.deleteProduct(id);
		if (Objects.isNull(msg)) {
			msg = "Data is not Deleted";
		}
		return msg;
	}
	
	public Product getData(int id) {
		Product product = dao.getProductById(id);
		if (Objects.isNull(product)) {
			product=null;
			return null;
		}
		return product;
	}
	
	public List<Product> getAll() {
		List<Product> list = dao.getAllProduct();
		if (Objects.isNull(list)) {
			list = null;
		}
		return list;
	}
	
	public List<Product> getProduct(int page, int size) {
	    return dao.getProducts(page, size);
	}
}
