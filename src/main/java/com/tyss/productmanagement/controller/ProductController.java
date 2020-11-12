package com.tyss.productmanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.productmanagement.beans.Product;
import com.tyss.productmanagement.beans.ProductResponce;
import com.tyss.productmanagement.repo.ProductRepository;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	private static final String SUCCESS = "Success";
	private static final String FAILURE = "Failure";
	@Autowired
	private ProductRepository repo;

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ProductResponce getProductByName(@PathVariable("name")String name) {
		ProductResponce responce = new ProductResponce();
		Product product = repo.findByName(name);
		if (product != null) {
			responce.setStatusCode(201);
			responce.setMessage(SUCCESS);
			responce.setDescription("Data found in DB");
			responce.setProducts(Arrays.asList(product));
		} else {
			responce.setStatusCode(401);
			responce.setMessage(FAILURE);
			responce.setDescription("Data not found in DB");
		}
		return responce;
	}

	@PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductResponce addProduct(@RequestBody Product product) {
		ProductResponce responce = new ProductResponce();
		product = repo.save(product);
		if (product != null) {
			responce.setStatusCode(201);
			responce.setMessage(SUCCESS);
			responce.setDescription("Data added in DB");
			responce.setProducts(Arrays.asList(product));
		} else {
			responce.setStatusCode(401);
			responce.setMessage(FAILURE);
			responce.setDescription("Data not added in DB");
		}
		return responce;
	}

	@PutMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductResponce modifyProduct(@RequestBody Product product) {
		ProductResponce responce = new ProductResponce();
		product = repo.update(product.getQuantity(), product.getDetails(), product.getImageUrl(), product.getId());
		if (product != null) {
			responce.setStatusCode(201);
			responce.setMessage(SUCCESS);
			responce.setDescription("Data updated in DB");
			responce.setProducts(Arrays.asList(product));
		} else {
			responce.setStatusCode(401);
			responce.setMessage(FAILURE);
			responce.setDescription("Data not updated in DB");
		}
		return responce;
	}

	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductResponce getAllProduct() {
		ProductResponce responce = new ProductResponce();
		List<Product> products = repo.findAll();
		if (products != null) {
			responce.setStatusCode(201);
			responce.setMessage(SUCCESS);
			responce.setDescription("Data found in DB");
			responce.setProducts(products);
		} else {
			responce.setStatusCode(401);
			responce.setMessage(FAILURE);
			responce.setDescription("Data not found in DB");
		}
		return responce;
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductResponce deleteProduct(@PathVariable("id") int id) {
		ProductResponce responce = new ProductResponce();
		repo.deleteById(id);
		responce.setStatusCode(201);
		responce.setMessage(SUCCESS);
		responce.setDescription("Data deleted from DB");
		return responce;
	}
}
