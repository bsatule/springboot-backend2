package net.javaguides.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringbootBackend2ApplicationTests {

	@Autowired
	UserRepository userRepository;
	
//	@Test
//	void contextLoads() {
//	}
	
	@Test
	@Order(1)
	public void testAddUser() {
	User user= new User();
	user.setFirstName("TESTCASE1");
	user.setLastName("CASE");
	//user.setMoNo(98776);
	user.setPinCode("98877");
	user.setDateOfBirth(Date.valueOf("2013-09-04"));
	user.setDateOfJoining(Date.valueOf("2021-09-09"));
	userRepository.save(user);
	assertNotNull(userRepository.findById((long) 12).get());
	}
	
	@Test
	@Order(2)
	public void testReadAll() {
		List<User> list = userRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testSingleUserData() {
		User user1 = userRepository.findById((long) 24).get();
		System.out.println(user1);
		assertEquals("Patil", user1.getLastName());
	}
	
	@Test
	@Order(4)
	public void testUpdateUser() {
		User user = userRepository.findById((long) 65).get();
		user.setLastName("Mahale");
		userRepository.save(user);
		assertNotEquals("Atule", userRepository.findById((long) 15).get().getLastName());
	}
	
	@Test
	@Order(5)
	public void testDelete() {
		userRepository.deleteById((long) 94);
		assertThat(userRepository.existsById((long) 22)).isFalse();
	}

}
