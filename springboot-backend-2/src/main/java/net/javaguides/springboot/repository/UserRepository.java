package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	// // working below line of commented code for searching based on one condition
		//List<User> findUsersByFirstName(String firstName);
		//@Query("select u from User u where u.firstName = :firstName or u.lastName = :lastName")
		//List<User> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
		// working below line of commented code for "And" condition
		//List<User> findByFirstNameAndLastName(String firstName, String lastName);
		
		// working below line of commented code for "Or" condition
		//List<User> findByFirstNameOrLastName(String firstName, String lastName);
		
	     //List<User> findByFirstNameOrLastNameOrPinCode(String firstName, String lastName, String pincode);
	
	       List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPinCodeContainingIgnoreCase(String firstname, String lastname, String pincode);

}
