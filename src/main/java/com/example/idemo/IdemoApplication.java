package com.example.idemo;

import com.example.idemo.model.Role;
import com.example.idemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdemoApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(IdemoApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepository;

	public void run(String... args) throws Exception
	{
		if(roleRepository.count() == 0){
			Role firstSet = new Role((long) 1,"ADMIN");
			// ob.save() method
			roleRepository.save(firstSet);
			Role secondSet = new Role((long) 2,"USER");
			// ob.save() method
			roleRepository.save(secondSet);

		}
	}

}
