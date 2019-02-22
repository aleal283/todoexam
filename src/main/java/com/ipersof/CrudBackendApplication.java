package com.ipersof;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ipersof.entities.User;
import com.ipersof.repositories.UserRepository;
import com.ipersof.services.StorageService;

@SpringBootApplication
public class CrudBackendApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		storageService.deleteAll();
		storageService.init();
		/*
		userRepository.save(new User("Analisis","","",true));
		userRepository.save(new User("Recoleccion de Informacion","Pasos a seguir","",false));
		userRepository.save(new User("Programacion","Se deberan programar modulos en etapas","",false));
		userRepository.save(new User("Pruebas continuas","Las pruebas deben realizarse con el equipo","",false));*/
		
		
	}

}

