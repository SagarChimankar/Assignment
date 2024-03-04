package com.assign.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.assign.services.service;



@RestController
public class controlers {
	
	@Autowired
	private final service excelService=null;
	
	@Autowired
	private final service xmlService=null;
	
	//used to itrate over the/ we need pass file from postman  
    @PostMapping("/uploadExcel")
    public ResponseEntity<List<List<String>>> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException, EncryptedDocumentException, InvalidFormatException {
        
    	return new ResponseEntity<List<List<String>>>(excelService.readExcel(file, 40),HttpStatus.OK); // Start from the 40th row consider we have 50th row
    }
    
    //convert our file into json file 
    @PostMapping("/uploadxml")
    public void uploadXML() throws IOException {
    	
    	 xmlService.XmlToJsonFileCreated();
    }
}
