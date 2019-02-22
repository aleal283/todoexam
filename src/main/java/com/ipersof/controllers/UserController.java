package com.ipersof.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.StoreType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ipersof.entities.User;
import com.ipersof.repositories.UserRepository;
import com.ipersof.services.StorageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	private StorageService uploadService;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		return userRepository.getOne(id);
	}
	
	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return true;
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/fileIdUser")
	public String getFile(MultipartFile file) {
		uploadService.store(file);
		return uploadService.getNameStore();
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
