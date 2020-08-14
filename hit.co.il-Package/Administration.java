package hit.co.il;
import java.util.*;

import tangible.OutObject;
import tangible.TryParseHelper;


public class Administration extends Product
{

	private int weight;

	public Administration(int productCatalogNum)
	{
		super(productCatalogNum);

	}

	
	public final void printFoodObject()
	{
		super.PrintProduct();
		System.out.println(" , weight :" + weight);
	}
	
	public String toString()
	{
		return super.toString() + " , weight :" + weight;
	}

	public ArrayList<String> GetProductProperties()
	{
		ArrayList<String> productProperties = super.GetProductProperties();

		productProperties.add(("What is the product weight?"));

		return productProperties;
	}

	public boolean SetProductProperties(String userInput, int indexToSet)
	{
		if (indexToSet >= 1 && indexToSet <= baseAmountOfProperties)
		{
			return super.SetProductProperties(userInput, indexToSet);
		}

		boolean goodInput = false;
		int integerInput;
		switch (indexToSet)
		{
			case baseAmountOfProperties + 1:
				tangible.OutObject<Integer> tempOut_integerInput = new tangible.OutObject<Integer>();
				goodInput = tangible.TryParseHelper.tryParseInt(userInput, tempOut_integerInput);
			integerInput = tempOut_integerInput.outArgValue;
				if (goodInput && integerInput >= 1 && integerInput <= 4)
				{
					this.weight = integerInput;
					goodInput = true;
				}
				else
				{
					goodInput = false;
				}

				break;

			default:
				break;
		}

		return goodInput;
	}
}




