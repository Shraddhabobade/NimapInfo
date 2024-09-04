package com.nimap.crudoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.crudoperations.entity.Category;
import com.nimap.crudoperations.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    
	@Autowired
	CategoryService service;
	
	@PostMapping("categories")
	public String addCategory(@RequestBody Category c) {
		String msg = service.addCategory(c);
		return msg;
	}
	

	
	 @PutMapping("/categories/{id}")
	    public ResponseEntity<String> updateCategory(@PathVariable long id, @RequestBody Category category) {
	        // Set the id in the category object
	        category.setId(id);
	        
	        // Call the service to update the category
	        String message = service.updateCategory(category);
	        
	        return ResponseEntity.ok(message);
	    }
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategory(@PathVariable int id) {
		String msg = service.deleteCategory(id);
		return msg;
	}
	
	@GetMapping("/categories/{id}")
	public Category getById(@PathVariable int id) {
		Category c = service.getData(id);
		return c;
	}
	
	@GetMapping("/categories")
	public List<Category> getCategories(@RequestParam(required = false, defaultValue = "0") Integer page,
	                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
	    if (page == 0 && size == 10) {
	        return service.getAll(); // Return all categories if no pagination is applied
	    } else {
	        return service.getCategories(page, size); // Return paginated categories
	    }
	}
}
