package com.ericksilva.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericksilva.workshopmongo.domain.User;
import com.ericksilva.workshopmongo.dto.UserDTO;
import com.ericksilva.workshopmongo.repository.UserRepository;
import com.ericksilva.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(()-> new ObjectNotFoundException("Object not found: " + id));
	}
	
	public User insert(User usr) {
		return repo.insert(usr);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User usr) {
		User newUser = repo.findById(usr.getId()).orElse(null);
		updateData(newUser, usr);
		return repo.save(newUser);
	}

	private void updateData(User newUser, User usr) {
		newUser.setName(usr.getName());
		newUser.setEmail(usr.getEmail());
	}

	public User fromDTO(UserDTO usrDTO) {
		return new User(usrDTO.getId(),
						usrDTO.getName(),
						usrDTO.getEmail());
	}
}
