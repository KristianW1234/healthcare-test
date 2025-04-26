package com.testing.healthcare.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testing.healthcare.utils.UtilFunctions;
import com.testing.healthcare.entities.Category;
import com.testing.healthcare.entities.Product;
import com.testing.healthcare.repositories.CategoryRepository;
import com.testing.healthcare.repositories.ProductRepository;
import com.testing.healthcare.services.ProductService;

@Service("ProductServiceImpl")
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Value("${image.dir}")
	private String imageDir;

	@Override
	public JSONObject getSingleProduct(Long id) {
		JSONObject result = new JSONObject();
		Product product = productRepository.findProductById(id);
		result.put("id", product.getId());
		result.put("productName", product.getProductName());
		result.put("productSku", product.getProductSku());
		result.put("productPrice", product.getProductPrice());
		result.put("productDescription", product.getProductDescription());
		result.put("productImg", product.getProductImg());
		result.put("productCategories", setCategoryArray(product));
		
		return result;
		
	}

	@Override
	public JSONArray getAllProducts() {
		List<Product> products = productRepository.findAll();
		JSONArray result = new JSONArray();
		JSONObject json;
		
		for (Product product : products) {
			json = new JSONObject();
			json.put("id", product.getId());
			json.put("productName", product.getProductName());
			json.put("productSku", product.getProductSku());
			json.put("productPrice", product.getProductPrice());
			json.put("productDescription", product.getProductDescription());
			json.put("productImg", product.getProductImg());
			json.put("productCategories", setCategoryArray(product));
			result.put(json);
			
		}
		return result;
	}
	
	@Override
	public JSONObject addProduct(String productName, String productSku, MultipartFile productImg, String productDescription, BigDecimal productPrice, Long[] categories) {
		JSONObject result = new JSONObject();
		if (checkExistingSku(productSku)) {
			result.put("Result", "Fail");
			result.put("Message", "Same SKU already existed");
			return result;
		}
		
		
		Product product = new Product();
		List<Category> categoryList = new ArrayList<Category>();
		product.setProductName(productName);
		product.setProductSku(productSku);
		product.setProductDescription(productDescription);
		product.setProductPrice(productPrice);
		for (Long categoryId : categories) {
			if (!checkExistingCategory(categoryId)) {
				result.put("Result", "Fail");
				result.put("Message", "Category with Id " + categoryId +" does not exist");
				return result;
			}
			Category category = categoryRepository.findCategoryById(categoryId);
			categoryList.add(category);
		}
		product.setCategories(categoryList);
		result = UtilFunctions.uploadFile(productImg, imageDir, "product");

		if (result.optString("Result").equals("Success")) {
			product.setProductImg(productImg.getOriginalFilename());
			productRepository.save(product);
		}
		
		return result;
	}

	@Override
	public JSONObject deleteProduct(String jsonStr) {
		JSONObject result = new JSONObject();
		JSONObject json = new JSONObject(jsonStr);
		try {
			productRepository.deleteById(json.getLong("id"));
			result.put("Result", "Success");
			result.put("Message", "Product successfully deleted");

		} catch (NumberFormatException ex) {
			result.put("Result","Fail");
			result.put("Message","Invalid ID");
		}
		
		return result;
	}

	@Override
	public JSONObject updateProduct(Long id, String productName, String productSku, MultipartFile productImg,
			String productDescription,  BigDecimal productPrice, Long[] categories) {
		JSONObject result = new JSONObject();
		if (checkExistingSku(productSku)) {
			result.put("Result", "Fail");
			result.put("Message", "Same SKU already existed");
			return result;
		}
		
		Product product = productRepository.findProductById(id);
		if (product != null) {
			if (productName != null && !productName.isEmpty() && !productName.isBlank()) {
				product.setProductName(productName);
			}
			if (productSku != null && !productSku.isEmpty() && !productSku.isBlank()) {
				product.setProductSku(productSku);
			}
			
			if (productDescription != null && !productDescription.isEmpty() && !productDescription.isBlank()) {
				product.setProductDescription(productDescription);
			}
			
			if (productPrice != null && productPrice.doubleValue() >= 0) {
				product.setProductPrice(productPrice);
			}
			
			if (categories != null && categories.length > 0) {
				List<Category> categoryList = new ArrayList<Category>();
				for (Long categoryId : categories) {
					Category category = categoryRepository.findCategoryById(categoryId);
					categoryList.add(category);
				}
				product.setCategories(categoryList);
			}
			
			if (productImg != null) {
				result = UtilFunctions.uploadFile(productImg, imageDir, "product");
				if (result.optString("Result").equals("Success")) {
					product.setProductImg(productImg.getOriginalFilename());
					productRepository.save(product);
				}
			} else {
				result.put("Result", "Success");
				productRepository.save(product);

			}
		} else {
			result.put("Result", "Fail");
			result.put("Message", "Invalid ID");
		}
		
		

		return result;
	}
	
	private JSONArray setCategoryArray(Product product){
		JSONArray array = new JSONArray();
		JSONObject json;
		for (Category category : product.getCategories()) {
			json = new JSONObject();
			json.put("id", category.getId());
			json.put("categoryName", category.getCategoryName());
			array.put(json);
		}
		
		return array;
	}
	
	private boolean checkExistingSku (String sku) {
		return productRepository.findProductBySku(sku) != null;
		
	}
	
	private boolean checkExistingCategory (Long id) {
		return categoryRepository.findCategoryById(id) != null;
		
	}
	
	

}
