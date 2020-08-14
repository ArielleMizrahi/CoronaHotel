package hit.co.il;
import java.util.*;

import hit.co.il.Enums.eProductType;


public class ProductsHash
{

	public HashMap<Integer, Product> productHashMap;

	public ProductsHash()
	{
		productHashMap = new HashMap<Integer, Product>();
	}
//**************************************************************************************************************
	public final boolean isEmpty()
	{
		return (productHashMap == null || (new Integer(productHashMap.size())).equals(0));
		//return (productHashMap == null);
	}

	public final void InsertNewProductModel(eProductType productType, int productCatalogNum) //, String productName, int productQuantity, int minimumQuantity, int productCatalogNum)
	{
		if (productHashMap.containsKey(productCatalogNum))
		{
			throw new RuntimeException("A value for '" + productCatalogNum + "' is already present.");
		}

		Product newProductToAdd = ProductFactory.FactoryProductCreator(productType, productCatalogNum);
		productHashMap.put(productCatalogNum, newProductToAdd);
	}

	public void update(int key, Product value)
	{
		if (!productHashMap.containsKey(key))
		{
			throw new RuntimeException("There is no value to update for key '" + key + "'.");
		}

		if (value == null)
		{
			throw new RuntimeException("Value cannot be null.");
		}

		productHashMap.put(key, value);
	}

	public void remove(int key)
	{
		productHashMap.remove(key);
	}


	public boolean IsProductExist(int catalogNumber)
	{
		return productHashMap.containsKey(catalogNumber);
	}

	public Product GetProduct(int catalogNum)
	{
		Product product;
		if (productHashMap.containsKey(catalogNum))
		{
			product = productHashMap.get(catalogNum);
		}
		else
		{
			product = null;
		}

		return product;
	}

	public String ShowProductDetails(int catalogNum)
	{
		if (!productHashMap.containsKey(catalogNum))
		{
			throw new IllegalArgumentException("There is no such product");
		}

		return productHashMap.get(catalogNum).toString();
	}

	public ArrayList<Product> GetListOfProducts(eProductType productType)
	{
		//StringBuilder listOfLicensePlateNumbers = new StringBuilder();
		ArrayList<Product> toReturn = new ArrayList<Product>();

		for (Product product : productHashMap.values())
		{
			String s = product.getClass().getSimpleName();
			String k = productType.toString();
			if (product.getClass().getSimpleName().equals(productType.toString()))
			{
				toReturn.add(product);
			}
		}

		return toReturn;
	}

	public void changeAmount(int amountToSet, int catalogNum)
	{
		if (productHashMap.containsKey(catalogNum))
		{
			productHashMap.get(catalogNum).setProductQuantity(amountToSet);
		}
		else
		{
			throw new IllegalArgumentException("There is no such product");
		}
	}
}

