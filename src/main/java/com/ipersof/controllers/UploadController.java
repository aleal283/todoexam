package com.ipersof.controllers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import com.ipersof.services.StorageService;

//@ControllerAdvice
@RequestMapping("/upload")
@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UploadController {
	@Autowired
	StorageService storageService;
	
	
	List<String> files = new ArrayList<String>();
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/idFileStore")
	public @ResponseBody String response() {		
		//String json = "[{\"username\":\"Hello\",\"email\":\"hello@email.com\",\"credits\":\"100\",\"twitter_username\":\"\"},{\"username\":\"Goodbye\",\"email\":\"goodbye@email.com\",\"credits\":\"0\",\"twitter_username\":\"\"}]";
		
		JSONObject objJson = new JSONObject();			
		objJson.put("rta", this.storageService.getNameStore());
		
		return objJson.toJSONString();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/postfilerefresh")
	public ResponseEntity<String> handleFileUploadRefresh(@RequestParam("arch") MultipartFile file,@RequestParam("file") String name){
		JSONObject obj = new JSONObject();
		try {
			
			storageService.storageUpdate(file, name);
			
			obj.put("rta",this.storageService.getNameStore());
			
			this.files.add(this.storageService.getNameStore());
			
			String mje = "El archivo "+file.getOriginalFilename()+" se subió exitosamente";
			
			System.out.println(mje);
			
			return ResponseEntity.status(HttpStatus.OK).body(obj.toJSONString());
			
		} catch (Exception e) {
			// TODO: handle exception
			obj.put("rta","Error al subir "+file.getOriginalFilename());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(obj.toString());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/postfile")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){

		JSONObject obj = new JSONObject();
		
		try {
			this.storageService.store(file);
			
			//this.files.add(file.getOriginalFilename());
			obj.put("rta",this.storageService.getNameStore());
			
			this.files.add(this.storageService.getNameStore());
			
			String mje = "El archivo "+file.getOriginalFilename()+" se subió exitosamente";
			
			System.out.println(mje);
			return ResponseEntity.status(HttpStatus.OK).body(obj.toJSONString());
			
		} catch (Exception e) {
			// TODO: handle exception
			obj.put("rta","Error al subir "+file.getOriginalFilename());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(obj.toString());
		}

	}
	
	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {		
		List<String> fileNames = this.files.stream()
				.map(fileName->MvcUriComponentsBuilder
								.fromMethodName(UploadController.class , "getFile", fileName)
								.build().toString())
				.collect(Collectors.toList());
		
 
		return ResponseEntity.ok().body(fileNames);
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	
	
	
}
