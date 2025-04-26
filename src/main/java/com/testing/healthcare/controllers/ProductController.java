package com.testing.healthcare.controllers;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testing.healthcare.utils.UtilFunctions;
import com.fasterxml.jackson.databind.JsonNode;
import com.testing.healthcare.entities.Product;
import com.testing.healthcare.services.ProductService;

@RestController
@RequestMapping("/healthcare/product")
public class ProductController {
	@Autowired
	ProductService service;
	
	@GetMapping("/getAll")
	public JsonNode getAllProducts() {
		JSONArray arr = service.getAllProducts(); 
		return UtilFunctions.convertJSONArrayToNode(arr);
	}
	
	@GetMapping("/get/{id}")
	public JsonNode getProduct(@PathVariable Long id) {
		JSONObject json = service.getSingleProduct(id);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	@PostMapping("/add")
	public JsonNode addProduct(@RequestParam MultipartFile file, 
			@RequestParam String name, 
			@RequestParam String sku, 
			@RequestParam(required=false) String description, 
			@RequestParam BigDecimal price,
			@RequestParam Long[] categories) {
		JSONObject json = service.addProduct(name, sku, file, description, price, categories);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	@PatchMapping("/update")
	public JsonNode updateProduct(@RequestParam Long id, 
			@RequestParam(required=false) MultipartFile file, 
			@RequestParam(required=false) String name, 
			@RequestParam(required=false) String sku, 
			@RequestParam(required=false) String description,
			@RequestParam(required=false) BigDecimal price,
			@RequestParam(required=false) Long[] categories) {
		JSONObject json = service.updateProduct(id, name, sku, file, description, price, categories);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	
	
	@DeleteMapping("/delete")
	public JsonNode deleteProduct(@RequestBody String jsonStr) {
		JSONObject json = service.deleteProduct(jsonStr);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
}
