package com.ericksilva.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ericksilva.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User sophie = new User("1", "Sophie Ferreira", "sophie@gmail.com");
		User priscila = new User("2", "Priscila Ferreira", "sophie@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(sophie, priscila));
		return ResponseEntity.ok().body(list);
	}
	
}
