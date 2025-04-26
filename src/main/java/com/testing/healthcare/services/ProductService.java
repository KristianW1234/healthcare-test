package com.testing.healthcare.services;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;


public interface ProductService {
	public JSONObject getSingleProduct(Long id);
	public JSONArray getAllProducts();	
	public JSONObject addProduct(String productName, String productSku, MultipartFile productImg, String productDescription, BigDecimal productPrice, Long[] categories);
	public JSONObject deleteProduct(String jsonStr);
	public JSONObject updateProduct(Long id, String productName, String productSku, MultipartFile productImg, String productDescription, BigDecimal productPrice, Long[] categories);

}
