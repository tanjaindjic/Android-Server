package com.mastercart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadSevice {

	public static final Path rootLocation = Paths.get("resources/images");

	public String uploadImage(MultipartFile file) {
		String fileName = null;
		try {
			fileName = file.getOriginalFilename();
			if(!(fileName.endsWith(".jpg") || !fileName.endsWith(".JPG") || !fileName.endsWith(".JPEG") || !fileName.endsWith(".jpeg") 
					|| !fileName.endsWith(".png") || !fileName.endsWith(".PNG"))) {
				return fileName;
			}
			Path filePath = rootLocation.resolve(fileName);
            Resource resource =  new UrlResource(filePath.toUri());
            if(resource.exists()) {
            	String ending = ".png";
            	if(fileName.endsWith(".jpg") || fileName.endsWith(".JPG"))
            		ending = ".jpg";
            	else if(fileName.endsWith(".jpeg") || fileName.endsWith(".JPEG"))
            		ending = ".jpeg";            		
            	fileName = fileName.substring(0, fileName.length() - 4);
				fileName = fileName + "_"  + System.currentTimeMillis() + ending;
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName));
        } catch (Exception e) {
        	throw new RuntimeException();
        }
		return fileName;
	}

}
