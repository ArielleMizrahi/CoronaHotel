package hit.co.il;
import java.util.*; 

public class Food extends Product
{

	private String expirationDate;
	private String kosherType;
	private String vegetarianOrNot;

	public Food(int productCatalogNum)
	{
		super(productCatalogNum);

	}

	public final void printFoodObject()
	{
		super.PrintProduct();
		System.out.println(" , Expiration Date :" + expirationDate + " , Kosher type: " + kosherType + " , Vegetarian or not :" + vegetarianOrNot);
	}

	public String toString()
	{
		return super.toString() + " , Expiration Date :" + expirationDate + " , Kosher type : " + kosherType + " , Vegetarian or not :" + vegetarianOrNot;
	}
	
	public ArrayList<String> GetProductProperties()
	{
		ArrayList<String> productProperties = super.GetProductProperties();

		productProperties.add(("What is the food product expirationDate?"));
		productProperties.add(("What is the food product kosher type?"));
		productProperties.add(("Do this food product is vegetarian?(Yes/No)"));

		return productProperties;
	}

	public boolean SetProductProperties(String userInput, int i_IndexToSet)
	{
		if (i_IndexToSet >= 1 && i_IndexToSet <= baseAmountOfProperties)
		{
			return super.SetProductProperties(userInput, i_IndexToSet);
		}

		boolean goodInput = false;

		switch (i_IndexToSet)
		{
			case baseAmountOfProperties + 1:
				this.expirationDate = userInput;
				goodInput = true;

				break;
			case baseAmountOfProperties + 2:
				this.kosherType = userInput;
				goodInput = true;

				break;
			case baseAmountOfProperties + 3:
				this.vegetarianOrNot = userInput;
				goodInput = true;

				break;

			default:
				break;
		}

		return goodInput;
	}
}

