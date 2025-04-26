package com.testing.healthcare.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;


public interface CategoryService {
	public JSONObject getSingleCategory(Long id);
	public JSONArray getAllCategories();	
	public JSONObject addCategory(String jsonStr);
	public JSONObject deleteCategory(String jsonStr);
	public JSONObject updateCategory(String jsonStr);

}
