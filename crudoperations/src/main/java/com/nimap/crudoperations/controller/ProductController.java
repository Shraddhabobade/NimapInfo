package com.nimap.crudoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nimap.crudoperations.entity.Product;
import com.nimap.crudoperations.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping("products")
	public String addProduct(@RequestBody Product p) {
		String msg = service.addProduct(p);
		return msg;
	}
	
	@PutMapping("products/{id}")
	public String updateProduct(@PathVariable long id, @RequestBody Product p) {
		p.setId(id);
		String msg = service.updateProduct(p);
		return msg;
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable int id) {
		String msg = service.deleteProduct(id);
		return msg;
	}
	
	@GetMapping("/products/{id}")
	public Product getData(@PathVariable int id) {
		Product pro = service.getData(id);
		return pro;
	}
	
//	@GetMapping("/products")
//	public List<Product> getAll() {
//		List<Product> list = service.getAll();
//		return list;
//	}
	
	@GetMapping("/products")
	public List<Product> getProducts(@RequestParam(required = false, defaultValue = "0") Integer page,
	                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
	    if (page == 0 && size == 10) {
	        return service.getAll(); // Return all categories if no pagination is applied
	    } else {
	        return service.getProduct(page, size); // Return paginated categories
	    }
	}
}
