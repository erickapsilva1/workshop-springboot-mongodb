package com.ericksilva.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericksilva.workshopmongo.domain.Post;
import com.ericksilva.workshopmongo.repository.PostRepository;
import com.ericksilva.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(()-> new ObjectNotFoundException("Object not found: " + id));
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
}
