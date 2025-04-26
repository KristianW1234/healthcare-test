package com.testing.healthcare.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilFunctions {
	
	public static JsonNode convertJSONObjectToNode(JSONObject json) {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode result = null;
		try {
			result = mapper.readTree(json.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static JsonNode convertJSONArrayToNode(JSONArray arr) {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode result = null;
		try {
			result = mapper.readTree(arr.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static JSONObject uploadFile(MultipartFile file, String imageDir, String folder) {
		JSONObject result = new JSONObject();
		try {
    		String absolutePath = imageDir + folder;
    		folderCheckAdd(absolutePath);
    		File file2 = new File(absolutePath + "/" + file.getOriginalFilename());
        	String ext = FilenameUtils.getExtension(file2.getAbsolutePath());
            if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg")) {
            	InputStream is;
        		FileOutputStream os = null;
        		try {
        			is = file.getInputStream();
        			os = new FileOutputStream(file2);
        			byte[] buffer = new byte[1024];
        			int length;
        			while ((length = is.read(buffer)) > 0) {
        				os.write(buffer, 0, length);
        			}
        		
        		} catch (Exception ex){
        			ex.printStackTrace();
        		} finally {
        			os.close();
        		}
        		result.put("Result", "Success");
        		result.put("Message", "Product successfully added");
            } else {
            	result.put("Result", "Fail");
        		result.put("Message", "Picture not in PNG/JPG format");
            }
    	} catch (IOException ex) {
    		result.put("Result", "Fail");
    		result.put("Message", "IO Exception caught");
    		
    	}
		return result;
	}
	
	
	
	public static void folderCheckAdd(String path) throws IOException{
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	
}
