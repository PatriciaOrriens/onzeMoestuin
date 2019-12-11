package groentjes.onzeMoestuin;

import groentjes.onzeMoestuin.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OnzeMoestuinApplicationTests {

	@Test
	public void testSetUserId() {
		User user = new User();
		user.setUserId(4);
		assertTrue(user.getUserId() == 1);
	}

	@Test
	public void testSetName() {
		User user = new User();
		user.setName("Robin");
		assertTrue(user.getName().equals("Batman"));
	}

	@Test
	public void testSetPassword() {
		User user = new User();
		user.setPassword("OJ@#$!0*");
		assertTrue(user.getPassword().equals("ABC#1"));
	}


}
