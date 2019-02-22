package com.ipersof.services;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private String nameStore;
	
	

	private final Path rootLocation = Paths.get("upload-dir");
	static final int MAX=999999;
	
	
	
	public void storageUpdate(MultipartFile arch,String file) {
		try {
			System.out.println("ingresa al refresh con el valor "+ file);
		
			File myfile = new File(rootLocation+"/"+file);
			
		
		if (myfile.exists()) {
			System.out.println("lo encontro lo tieene que borarr");
			myfile.delete();
			this.store(arch);
		}
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	public void store(MultipartFile file) {
		int nameRnd =0;
		String ext = "";
		String nameStore="";
		try {
			System.out.println("quiere ingresasr "+file.getSize());
			if (file.getSize()>0) {
				
				nameRnd = generateRandomInt(MAX);
				
				//obtengo la extension
				ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));				
				
				nameStore =  Integer.toString(nameRnd)+ext;
				
				File af = new File(nameStore);
				
				if (af.exists()) {
			
					System.out.println("ya existe un archivo con ese nombre");
					nameRnd = generateRandomInt(MAX);
					nameStore =  Integer.toString(nameRnd)+ext;
					
				}
				
				
				Files.copy(file.getInputStream(), this.rootLocation.resolve(nameStore));
				this.setNameStore(nameStore);
				}
				
				
				//Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error");
		}
		
	}
	
	public static int generateRandomInt(int upperRange){
		  Random random = new Random();
		  return random.nextInt(upperRange);
	}
		
	
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists()||resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("Error");
			}
			
		} catch (MalformedURLException e) {
			// TODO: handle exception
			throw new RuntimeException("Error");
		}
	}
	
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
	}
 
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
	
	
	public String getNameStore() {
		return nameStore;
	}

	public void setNameStore(String nameStore) {
		this.nameStore = nameStore;
	}
	
	

}
