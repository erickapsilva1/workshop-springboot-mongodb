package com.ericksilva.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ericksilva.workshopmongo.domain.User;
import com.ericksilva.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User sophie = new User(null, "Sophie Turner", "sturner@gmail.com");
		User priscila = new User(null, "Priscila Egito", "pegito@gmail.com");
		User luna = new User(null, "Luna Luneta", "lluneta@gmail.com");
		
		userRepository.saveAll(Arrays.asList(sophie, priscila, luna));
		
	}

}
