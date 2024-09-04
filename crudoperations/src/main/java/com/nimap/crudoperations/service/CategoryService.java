package com.nimap.crudoperations.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimap.crudoperations.dao.CategoryDao;
import com.nimap.crudoperations.entity.Category;
import com.nimap.crudoperations.entity.Product;

@Service
public class CategoryService {

	@Autowired
	CategoryDao dao;
	
	public String addCategory(Category c) {
		String msg = dao.addCategory(c);
		if(Objects.isNull(msg)) {
			msg = "Product is Not Added";
		}return msg;
	}
	
	
	public String updateCategory(Category category) {
        return dao.updateCategory(category);
    }
	
	public String deleteCategory(int id) {
		String msg = dao.deleteProduct(id);
		if (Objects.isNull(msg)) {
			msg = "Data is not Deleted";
		}return msg;
		
	}
	
	public Category getData(int id) {
		Category category = dao.getCategory(id);
		if (Objects.isNull(category)) {
			category=null;
			return null;
		}
		return category;
	}
	
	public List<Category> getAll() {
		List<Category> list = dao.getAllCategory();
		if (Objects.isNull(list)) {
			list = null;
		}
		return list;
	}
	
	public List<Category> getCategories(int page, int size) {
	    return dao.getCategories(page, size);
	}
}
