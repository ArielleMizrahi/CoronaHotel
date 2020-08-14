package hit.co.il;
import java.util.*; 

public class Medical extends Product
{
	private String expirationDate;

	//public Medical next;
	//public Medical firstLink;


	public Medical(int productCatalogNum)
	{
		super(productCatalogNum);

	}


	/*********************************** methods ******************************************/

	@Override
	public void PrintProduct()
	{
		super.PrintProduct();
		System.out.println(", Expiration Date :" + expirationDate);

	}

	public String toString()
	{
		return super.toString() + ", Expiration Date :" + expirationDate;

	}

	public ArrayList<String> GetProductProperties()
	{
		ArrayList<String> productProperties = super.GetProductProperties();

		productProperties.add(("What is the product expirationDate?")); //amountOfProperties + 1 (number of question)

		return productProperties;
	}

	@Override
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

			default:
				break;
		}

		return goodInput;
	}
}

