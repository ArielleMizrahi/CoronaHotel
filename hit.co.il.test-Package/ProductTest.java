package hit.co.il.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hit.co.il.Model;

class ProductTest {

	Model ProductController;

	public void SetUp() {
		ProductController = new Model();
	}

	// @Disabled - test not working/ quieting
	// RepeatedTest(5) - running 5 times
	// BeforEach - THIS ONE WILL RUN BEFOR EACH TEST
	// AfterEach - THIS ONE WILL RUN AFTER EACH TEST
	// BeforAll- THIS ONE WILL RUN BEFOR ALL TEST
	// AfterAll- THIS ONE WILL RUN AFTER ALL TEST
	@Test
	public void showCompleteDataOfProductFailedWhencatalogNumberIsEmpty() {
		SetUp();
		boolean RetValue = ProductController.productsCollection.IsProductExist(2);
		Assertions.assertFalse(RetValue, "Show complete data of product passed passed but should fail");
	}

	@Test
	public void showCompleteDataOfProductFailedWhencatalogNumberIsNotExists() {
		SetUp();
		boolean RetValue = ProductController.productsCollection.IsProductExist(0);
		Assertions.assertFalse(RetValue, "Show complete data of product passed but should fail");
	}
	@Test
	public void showCompleteDataOfProductSucceededWhenUserExists() {
		SetUp();
		boolean RetValue = ProductController.productsCollection.IsProductExist(1);
		Assertions.assertFalse(RetValue, "Show complete data of product passed Succeeded!");
	}
	@Test
	public void showCompleteDataOfProductFailedWhenUserNotNumbers() {
		SetUp();
		boolean RetValue = ProductController.productsCollection.IsProductExist(123);
		Assertions.assertFalse(RetValue, "Show complete data of product passed but should fail");
	}
}