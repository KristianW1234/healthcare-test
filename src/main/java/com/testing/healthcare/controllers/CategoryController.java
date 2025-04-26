package com.testing.healthcare.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.healthcare.utils.UtilFunctions;
import com.fasterxml.jackson.databind.JsonNode;
import com.testing.healthcare.services.CategoryService;

@RestController
@RequestMapping("/healthcare/category")
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@GetMapping("/getAll")
	public JsonNode getAllCategories() {
		JSONArray arr = service.getAllCategories(); 
		return UtilFunctions.convertJSONArrayToNode(arr);
	}
	
	@GetMapping("/get/{id}")
	public JsonNode getCategory(@PathVariable Long id) {
		JSONObject json = service.getSingleCategory(id);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	@PostMapping("/add")
	public JsonNode addCategory(@RequestBody String jsonStr) {
		JSONObject json = service.addCategory(jsonStr);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	@PutMapping("/update")
	public JsonNode updateCategory(@RequestBody String jsonStr) {
		JSONObject json = service.updateCategory(jsonStr);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
	
	
	
	@DeleteMapping("/delete")
	public JsonNode deleteCategory(@RequestBody String jsonStr) {
		JSONObject json = service.deleteCategory(jsonStr);
		return UtilFunctions.convertJSONObjectToNode(json);
	}
}
