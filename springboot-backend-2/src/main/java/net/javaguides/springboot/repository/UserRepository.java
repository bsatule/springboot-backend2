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
	
  List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPinCodeContainingIgnoreCase(String firstname, String lastname, String pincode);

}
