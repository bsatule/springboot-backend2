package net.javaguides.springboot.controller;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	

	@Autowired
	private UserRepository userRepository;
	
	// get all students
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// create user rest api
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// get user by id rest api
	@GetMapping("/users/{id}")
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id).get();
				//.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(user);
	}
	
	// update user rest api
	@PutMapping("/users/{id}")
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) throws ParseException{
		User user = userRepository.findById(id).get();
				//.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		//Format format = new SimpleDateFormat("yyyy-MM-dd");
		//String dateOfJoining = format.format(userDetails.getDateOfJoining());
		//String dateOfBirth = format.format(userDetails.getDateOfBirth());
		//Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfJoining);
		//Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		//user.setDateOfJoining(date1);
		//user.setDateOfBirth(date2);
		user.setDateOfJoining(userDetails.getDateOfJoining());
		user.setDateOfBirth(userDetails.getDateOfBirth());
		user.setPinCode(userDetails.getPinCode());
		user.setIsActive(userDetails.getIsActive());
		
		User updatedUser = userRepository.save(user);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete employee rest api
	@DeleteMapping("/users/{id}/{val}")
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id, @PathVariable int val){
		if(val==0) {
			User user = userRepository.findById(id).get();
					//.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			//user.setIsActive(false);
			user.setIsActive(false);
			User updatedUser = userRepository.save(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	else {
		User user = userRepository.findById(id).get();
				//.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	     }
	}
	
	@GetMapping(value = "/users/search/{query}")
	public ResponseEntity<List<User>> getUsersByFirstNameOrLastNameOrPincode(@PathVariable("query") String query){
		//return new ResponseEntity<>(userRepository.findByFirstNameOrLastNameOrPinCodeContaining(query), HttpStatus.OK);
		String firstname = query;
		String lastname = query;
		String pincode = query;
		return new ResponseEntity<>(userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPinCodeContainingIgnoreCase(firstname, lastname, pincode), HttpStatus.OK);
	}
}
