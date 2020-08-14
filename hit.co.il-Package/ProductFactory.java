package hit.co.il;
import hit.co.il.Enums.eProductType;

public class ProductFactory
{
	//use getPlan method to get object of type Plan   
	public static Product FactoryProductCreator(eProductType productType, int productCatalogNum)
	{
		if (productType.equals(eProductType.Food))
		{
			return new Food(productCatalogNum);
			//product.();
		}
		else if (productType.equals(eProductType.Medical))
		{
			return new Medical(productCatalogNum);
		}
		else if (productType.equals(eProductType.Administration))
		{
			return new Administration(productCatalogNum);
		}

		//default
		return null;
	}
}

