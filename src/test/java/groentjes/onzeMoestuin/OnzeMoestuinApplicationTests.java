package groentjes.onzeMoestuin;

import groentjes.onzeMoestuin.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnzeMoestuinApplicationTests {

	@Test
	public void testUserName0() {
		// Arrange
		User user0 = new User();

		// Activate
		user0.setUserName("Batman");

		// Assert
		assertEquals("Batman", user0.getUsername());
	}

	@Test
	public void testUserName1() {
		// Arrange
		User user1 = new User();

		// Activate
		user1.setUserName("");

		// Assert
		assertEquals("", user1.getUsername());
	}

	@Test
	public void testPassword0() {
		// Arrange
		User user0 = new User();

		// Activate
		user0.setPassword("%^&9BuRq");

		// Assert
		assertEquals("%^&9BuRq", user0.getPassword());
	}

	@Test
	public void Password1() {
		// Arrange
		User user1 = new User();

		// Activate
		user1.setPassword("");

		// Assert
		assertEquals("", user1.getPassword());
	}
}
