package com.testing.healthcare.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.healthcare.entities.Category;
import com.testing.healthcare.repositories.CategoryRepository;
import com.testing.healthcare.services.CategoryService;

@Service("CategoryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public JSONObject getSingleCategory(Long id) {
		JSONObject result = new JSONObject();
		Category category = categoryRepository.findCategoryById(id);
		result.put("id", category.getId());
		result.put("categoryName", category.getCategoryName());
		return result;
	}

	@Override
	public JSONArray getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		JSONArray result = new JSONArray();
		JSONObject json;
		for (Category category : categories) {
			json = new JSONObject();
			json.put("id", category.getId());
			json.put("categoryName", category.getCategoryName());
			result.put(json);
		}
		return result;
	}

	@Override
	public JSONObject addCategory(String jsonStr) {
		JSONObject result = new JSONObject();
		Category category = new Category();
		JSONObject json = new JSONObject(jsonStr);
		if (json.has("categoryName")) {
			category.setCategoryName(json.getString("categoryName"));
		} else {
			result.put("Result", "Fail");
			result.put("Message", "Missing key 'categoryName'");
			return result;
		}
		
		
		try {
			categoryRepository.save(category);
			result.put("Result", "Success");
			result.put("Message", "Category " + json.getString("categoryName") + " has been added");
		} catch (Exception ex) {
			result.put("Result", "Fail");
			result.put("Message", "Category " + json.getString("categoryName") + " failed to be added due to error");
		}
		
		return result;
	}

	@Override
	public JSONObject deleteCategory(String jsonStr) {
		JSONObject result = new JSONObject();
		JSONObject json = new JSONObject(jsonStr);
		if (json.has("id")) {
			try {
				categoryRepository.deleteById(json.getLong("id"));
				result.put("Result", "Success");
				result.put("Message", "Category ID " + json.getLong("id") + " successfully deleted");

			} catch (NumberFormatException ex) {
				result.put("Result", "Fail");
				result.put("Message", "Invalid Id");
				return result;
			}
		} else {
			result.put("Result", "Fail");
			result.put("Message", "Missing key 'id'");
			return result;
		}
		
		return result;
	}

	@Override
	public JSONObject updateCategory(String jsonStr) {
		JSONObject result = new JSONObject();
		JSONObject json = new JSONObject(jsonStr);
		Category category = new Category();
		if (json.has("id")) {
			try {
				category = categoryRepository.findCategoryById(json.getLong("id"));
				if (category == null) {
					result.put("Result", "Fail");
					result.put("Message", "Category Not Found");
					return result;
				}
			} catch (NumberFormatException ex) {
				result.put("Result", "Fail");
				result.put("Message", "Invalid Id");
				return result;
			}
			
		} else {
			result.put("Result", "Fail");
			result.put("Message", "Missing key 'id'");
			return result;
		}
		
		if (json.has("categoryName")) {
			category.setCategoryName(json.getString("categoryName"));
		} else {
			result.put("Result", "Fail");
			result.put("Message", "Missing key 'categoryName'");
			return result;
		}
		
		categoryRepository.save(category);
		result.put("Result", "Success");
		result.put("Message", "Category ID " + json.getLong("id") + " has been updated");
		return result;
	}

}
