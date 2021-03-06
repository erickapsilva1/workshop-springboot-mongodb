package com.ericksilva.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ericksilva.workshopmongo.domain.Post;
import com.ericksilva.workshopmongo.domain.User;
import com.ericksilva.workshopmongo.dto.AuthorDTO;
import com.ericksilva.workshopmongo.dto.CommentDTO;
import com.ericksilva.workshopmongo.repository.PostRepository;
import com.ericksilva.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User sophie = new User(null, "Sophie Turner", "sturner@gmail.com");
		User priscila = new User(null, "Priscila Egito", "pegito@gmail.com");
		User luna = new User(null, "Luna Luneta", "lluneta@gmail.com");
		
		userRepository.saveAll(Arrays.asList(sophie, priscila, luna));
		
		Post post1 = new Post(null, sdf.parse("28/08/2021"), "Partiu Viagem", "Bora para Sampa", new AuthorDTO(sophie));
		Post post2 = new Post(null, sdf.parse("29/08/2021"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(sophie));
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("28/08/2021"), new AuthorDTO(luna));
		CommentDTO comment2 = new CommentDTO("Aproveite.", sdf.parse("29/08/2021"), new AuthorDTO(priscila));
		CommentDTO comment3 = new CommentDTO("Have a nice day.", sdf.parse("29/08/2021"), new AuthorDTO(luna));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	
		sophie.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(sophie);
	}

}
