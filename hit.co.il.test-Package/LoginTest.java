package hit.co.il.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hit.co.il.Model;

class LoginTest {
	Model loginController;

	public void SetUp() {
		loginController = new Model();
	}

	// @Disabled - test not working/ quieting
	// RepeatedTest(5) - running 5 times
	// BeforEach - THIS ONE WILL RUN BEFOR EACH TEST
	// AfterEach - THIS ONE WILL RUN AFTER EACH TEST
	// BeforAll- THIS ONE WILL RUN BEFOR ALL TEST
	// AfterAll- THIS ONE WILL RUN AFTER ALL TEST
	@Test
	public void loginFailedWhenUsernameIsEmpty() {
		SetUp();
		loginController.CreateAccessFile();
		boolean RetValue = loginController.StringFinder(" ,123.", loginController.AccessFile.getAbsolutePath());
		Assertions.assertFalse(RetValue, "The Login passed but should fail");
	}

	@Test
	public void loginFailedWhenPasswordIsEmpty() {
		SetUp();
		loginController.CreateAccessFile();
		boolean RetValue = loginController.StringFinder("123, .", loginController.AccessFile.getAbsolutePath());
		Assertions.assertFalse(RetValue, "The Login passed but should fail");
	}
	@Test
	public void loginSucceededWhenUserExists() {
		SetUp();
		loginController.CreateAccessFile();
		boolean RetValue = loginController.StringFinder("ori,1234.", loginController.AccessFile.getAbsolutePath());
		Assertions.assertTrue(RetValue, "The Login Succeeded!");
	}
	@Test
	public void loginFailedWhenUserNotExists() {
		SetUp();
		loginController.CreateAccessFile();
		boolean RetValue = loginController.StringFinder("ori,123.", loginController.AccessFile.getAbsolutePath());
		Assertions.assertFalse(RetValue, "The Login passed but should fail");
	}
}
