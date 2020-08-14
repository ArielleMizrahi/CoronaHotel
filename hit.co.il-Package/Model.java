package hit.co.il;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import hit.co.il.Enums.eProductType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
   
public class Model{

	
	public Model(){}
	public static Scanner input = new Scanner(System.in);	
	
	//Variables
    protected int userCount=1;
    protected int numOfGuestsToCheckIn;
    protected String userName;
    protected String userID="User"+userCount;
    protected String pass="000";
    String AccessfileName = ("RESEPTION_ACCESS.txt") ;    //file name = source file name + _parsed.txt
    String varFileName = "varFile.txt";
    String GuestfileName = ("Guest.txt") ;    //file name = source file name + _parsed.txt
	boolean existAccessFile = false;
	String append_value;
	boolean loggedIn=false;
	public File AccessFile;
	public File GuestFile;
	public File VarFile;
	FileChannel fileChannel;
	long fileSize;
	int userChose;
	SingletonReception mySingleton;
	
	public ProductsHash productsCollection = new ProductsHash();
    
    /*********************************************************************************************************************************************/
    public int userDecision()
    {
    	ClearScreen();
    	Access.accessDecision = 0;
    	while(Access.accessDecision != 1 && Access.accessDecision != 2)
    	{
    	//go to login scenario
    	System.out.println();
    	System.out.println("MENU");
    	System.out.println("CHOOSE ACTION:");
    	System.out.println();
    	System.out.println();
    	System.out.println("Login - 1");
    	System.out.println("Register - 2");
    	Access.accessDecision  = input.nextInt();
    	if(Access.accessDecision != 1 && Access.accessDecision != 2)
    		System.out.println("------> PLEASE CHOOSE ONLY 1 OR 2.");
    	}
    	return Access.accessDecision;
    }

	/*********************************************************************************************************************************************/
    public void AbsoluteFilePath(File thisFile) 
    {
     String absolutePath = thisFile.getAbsolutePath();
     System.out.println("File path : " + absolutePath);
     String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
     System.out.println("File path : " + filePath);
    }
	
	/*********************************************************************************************************************************************/
    public boolean StringFinder(String user, String path) {
    	  {
    	   BufferedReader br;
    	   String Thisline = "";

    	   try {

    	    br = new BufferedReader(new FileReader(path));

    	    try {
    	     while ((Thisline = br.readLine()) != null) {
    	      if (Thisline.equals(user)) {
    	       br.close();
    	       return true;
    	      }
    	     }
    	     br.close();
    	     return false;

    	    } catch (IOException e) {
    	     // TODO Auto-generated catch block
    	     e.printStackTrace();
    	    }
    	   } catch (FileNotFoundException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	   }
    	  }
    	  return false;
    	 }
	/*********************************************************************************************************************************************/
    public int CreateAccessFile() {
    	  //creating text file to save user names + passwords 
    	  AccessFile = new File(AccessfileName); //create the file.
    	  try {

    	   if (AccessFile.createNewFile()) {
    	    System.out.println("Debug info:");
    	    System.out.println("* Access file created"); //means no access file existed before->software havn't been used yet->go to register flow
    	    Access.absolutePath = AccessFile.getAbsolutePath();
    	    //get file path + get file size  
    	    Path filePath = Paths.get(AccessfileName);
    	    fileChannel = FileChannel.open(filePath);
    	    fileSize = fileChannel.size();
    	    System.out.format("* Size: %d bytes", fileSize); //debug 
    	    System.out.println("* Path: " + Access.absolutePath);
    	    System.out.println();

    	    return 0;
    	   } else {
    	    System.out.println("Debug info:");
    	    System.out.println("* Access file Exist"); //means no access file existed before->software havn't been used yet->go to register flow
    	    Access.absolutePath = AccessFile.getAbsolutePath();
    	    //get file path + get file size  
    	    Path filePath = Paths.get(AccessfileName);
    	    fileChannel = FileChannel.open(filePath);
    	    long fileSize = fileChannel.size();
    	    System.out.format("* Size: %d bytes", fileSize); //debug 
    	    System.out.println();
    	    System.out.println("* Path: " + Access.absolutePath);
    	    System.out.println();
    	    return 1;
    	   }
    	  } catch (IOException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  }
    	  return 1;
    	 }
 	/*********************************************************************************************************************************************/
    public int CreateGuestFile() {
    	  //creating text file to save guests by room.
    	  GuestFile = new File(GuestfileName); //create the file.
    	  try {

    	   if (GuestFile.createNewFile()) {
    	    System.out.println("Debug info:");
    	    System.out.println("* Guest file created"); 
    	    Access.absolutePath = GuestFile.getAbsolutePath();
    	    //get file path + get file size  
    	    Path filePath = Paths.get(GuestfileName);
    	    fileChannel = FileChannel.open(filePath);
    	    fileSize = fileChannel.size();
    	    System.out.format("* Size: %d bytes", fileSize); //debug 
    	    System.out.println("* Path: " + Access.absolutePath);
    	    System.out.println();

    	    return 0;
    	   } else {
    	    System.out.println("Debug info:");
    	    System.out.println("* Guest file Exist"); 
    	    Access.absolutePath = GuestFile.getAbsolutePath();
    	    //get file path + get file size  
    	    Path filePath = Paths.get(GuestfileName);
    	    fileChannel = FileChannel.open(filePath);
    	    long fileSize = fileChannel.size();
    	    System.out.format("* Size: %d bytes", fileSize); //debug 
    	    System.out.println();
    	    System.out.println("* Path: " + Access.absolutePath);
    	    System.out.println();
    	    return 1;
    	   }
    	  } catch (IOException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  }
    	  return 1;
    	 }
    	 /**
    	 * @throws InterruptedException *******************************************************************************************************************************************/
    	 public int Register() throws InterruptedException {
    	  ClearScreen();
    	  int passValidation = 0;

    	  System.out.println("REGISTER");
    	  System.out.println();
    	  //get Access user name
    	  System.out.print("Please Enter Username: ");
    	  userName = input.next();

    	  //get Access user password + validate password
    	  for (int i = 0; i < 4; i++) {
    	   if (i == 3) return 3;
    	   System.out.print("Please Enter Password: ");
    	   pass = input.next();
    	   System.out.print("Verify Password: ");
    	   String verifyPass = input.next();
    	   if (pass.equalsIgnoreCase(verifyPass)) {
    	    passValidation = 1;
    	    break;
    	   } else {
    		ClearScreen();
    	    System.out.println();
    	    System.out.println();
    	    System.out.println("Passwords does not match");
    	    System.out.println();
    	    System.out.println();
    	    continue;
    	   }
    	  }
    	  FileWriter writer;
    	  try {
    	   writer = new FileWriter(AccessfileName, true);
    	   writer.write(userName + "," + pass + ".");
    	   writer.write('\n');
    	   writer.flush();
    	  } catch (IOException e) {
    	   System.out.println("Error writing to Access users file");
    	   e.printStackTrace();
    	  }

    	  System.out.println();
    	  System.out.println("User " + userName + " Registerd succesfully");
    	  TimeUnit.SECONDS.sleep(2);
    	  ClearScreen();
    	  return (1);
    	 }
    	 /*********************************************************************************************************************************************/
    	 public boolean foundUserInAccessFile(String user, String file) {
    	  String line;

    	  Scanner scanner = new Scanner(file);
    	  while (scanner.hasNextLine()) {
    	   String nextToken = scanner.nextLine();
    	   if (nextToken.equalsIgnoreCase(user))
    	    return true;
    	  }
    	  return false;
    	 }
    	 /**
    	  * @throws FileNotFoundException *******************************************************************************************************************************************/

    	 /**
    	  * @throws InterruptedException *******************************************************************************************************************************************/
    	 public int Login() throws InterruptedException {
    	  ClearScreen();
    	  String loginUserName, loginPassword, userDetails;

    	  while (loggedIn == false) {

    	   System.out.println("LOGIN" + '\n');
    	   System.out.println("Enter UserName: ");
    	   loginUserName = input.next();
    	   System.out.println("Enter Password: ");
    	   loginPassword = input.next();

    	   userDetails = loginUserName + "," + loginPassword + ".";

    	   if (StringFinder(userDetails, Access.absolutePath)) {
    	    System.out.println();
    	    System.out.println();
    	    System.out.println("Logged in succesfully");
    	    TimeUnit.SECONDS.sleep(2);
    	    loggedIn = true;
    	    return 1;
    	   }
    	   input.reset();
    	   System.out.println();
    	   System.out.println();
    	   System.out.println("PASSWORD OR USERNAME DID NOT MATCH");
    	   System.out.println();
    	   System.out.println("TRY AGAIN - 1");
    	   System.out.println("LEAVE - 0");
    	   userChose = input.nextInt();
    	   ClearScreen();
    	   if (userChose == 0) {
    	    ClearScreen();
    	    System.out.println("USER DID NOT LOGGED IN - GOING BACK TO ACCESS MENU");
    	    return 3;
    	   } else if (userChose == 1) continue;

    	   System.out.println("PLEASE CHOOSE ONLY 0 OR 1.");
    	   break;
    	  }

    	  return 0;

    	 }
    	 /*********************************************************************************************************************************************/
    	 public void ClearScreen() {
    	  for (int i = 0; i < 100; i++) {
    	   System.out.println();
    	  }
    	 }
    	 /**
    	  * @throws IOException *******************************************************************************************************************************************/
    	 public Room getRoom(int numOfGuests) throws IOException {

    	  Room room = new Room(numOfGuests);

    	  FileWriter writer;
    	  try {
    	   writer = new FileWriter(varFileName);
    	   writer.write("roomNumber-" + Room.roomNumber);
    	   writer.write('\n');
    	   writer.flush();
    	  } catch (IOException e) {
    	   System.out.println("Error writing roomNumber to varFile.txt");
    	   e.printStackTrace();
    	  }

    	  if (Room.roomNumber < 100)
    	   return room;
    	  System.out.println("Hotel Capacity is full - no available rooms.");
    	  return null;
    	 }
    	 /**
    	  * @throws IOException *******************************************************************************************************************************************/
    	 public void getVars() throws IOException {

    	  VarFile = new File(varFileName);
    	  try {

    	   VarFile.createNewFile();


    	   FileWriter writer;
    	   Room.roomNumber = getRoomNumberFromVarFile();
    	   try {
    	    writer = new FileWriter(varFileName);
    	    writer.write("RoomNumber" + Room.roomNumber); //update VarFile
    	    writer.write('\n');
    	    writer.flush();
    	   } catch (IOException e) {
    	    System.out.println("Error writing room to Guest users file");
    	    e.printStackTrace();
    	   }
    	  } catch (IOException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  }
    	 }

    	 /**
    	  * @throws FileNotFoundException *******************************************************************************************************************************************/
    	 public int getRoomNumberFromVarFile() throws FileNotFoundException {
    	  int num = 0, i, intFromline, numericValue;

    	  String line;
    	  Scanner scanner = new Scanner(VarFile);


    	  while (scanner.hasNextLine()) {


    	   line = scanner.nextLine();
    	   i = line.length();

    	   while (i > 0 && Character.isDigit(line.charAt(i - 1))) {

    	    intFromline = line.charAt(i - 1);
    	    numericValue = Character.getNumericValue(intFromline);
    	    num += numericValue;
    	    i--;

    	   }


    	  }
    	  scanner.close();

    	  return num;
    	 }
    	 /*********************************************************************************************************************************************/
    	 /**
    	  * @throws InterruptedException 
    	  * @throws IOException *******************************************************************************************************************************************/
    	 public void CheckIn(SingletonReception control) throws InterruptedException, IOException {

    	  ClearScreen();
    	  int thisNumOfGuests = -1;

    	  System.out.println("Check-In \n\n");
    	  while (thisNumOfGuests <= 0 || thisNumOfGuests > 5) {
    	   System.out.println("Please Choose number of guests to check in: 1 / 2 / 3 / 4");
    	   thisNumOfGuests = input.nextInt();
    	   if (thisNumOfGuests <= 0 || thisNumOfGuests > 5) {
    	    System.out.println("Please Choose 1 / 2 / 3 / 4 only.");
    	    TimeUnit.SECONDS.sleep(2);
    	    ClearScreen();
    	   }

    	  }

    	  String guestString = "-Guest:ID,name,age,sex,religious-";

    	  Room r = getRoom(thisNumOfGuests);

    	  if (r != null) {
    	   switch (thisNumOfGuests) {
    	    case 1:
    	     r.roomtype = "ONE_BEDRM";
    	     break;
    	    case 2:
    	     r.roomtype = "TWO_BEDRM";
    	     break;
    	    case 3:
    	     r.roomtype = "THREE_BEDRM";
    	     break;
    	    case 4:
    	     r.roomtype = "PENTHOUS";
    	     break;
    	   }
    	  } else {
    	   System.out.println();
    	   System.out.println("Hotel is full");
    	   System.out.println();
    	   //deal with this situation
    	  }

    	  //create guests and save them to file using one manipulated string
    	  for (int i = 0; i < thisNumOfGuests; i++) {
    	   Guest g = createGuest();
    	   guestString = guestString.concat(GuesttoString(g));
    	  }
    	  guestString = guestString.concat(RoomtoString(r));

    	  FileWriter writer;
    	  try {
    	   writer = new FileWriter(GuestfileName, true);
    	   writer.write(guestString);
    	   writer.write('\n');
    	   writer.flush();
    	  } catch (IOException e) {
    	   System.out.println("Error writing room to Guest users file");
    	   e.printStackTrace();
    	  }

    	  TimeUnit.SECONDS.sleep(1);
    	  ClearScreen();
    	  System.out.println(thisNumOfGuests + " Guests Checked-In Successfully\nRoom-number: " + Room.roomNumber + "\n\nGoing back to Main Menu.");
    	  TimeUnit.SECONDS.sleep(4);
    	  ClearScreen();

    	 }
    	 /**
    	  * @throws IOException 
    	  * @throws InterruptedException *******************************************************************************************************************************************/
    	 public void CheckOut(SingletonReception control) throws IOException, InterruptedException {

    	  ClearScreen();
    	  System.out.println("Check-Out \n\n");

    	  String roomtoCheckout;
    	  String line;
    	  String manipulatedString;
    	  String manipulatedStringValidation;
    	  String currentLine;
    	  File tempFile = new File("myTempFile.txt");
    	  File Gfile = new File(GuestfileName);
    	  boolean found = false;

    	  BufferedReader reader = new BufferedReader(new FileReader(GuestfileName));
    	  BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


    	  System.out.print("Please Enter Room-number to Chck-Out: ");
    	  roomtoCheckout = input.next();
    	  manipulatedString = "roomtype-" + roomtoCheckout + ",";
    	  manipulatedStringValidation = manipulatedString;

    	  while ((currentLine = reader.readLine()) != null) { //while not reached EOF
    	   if (currentLine.matches("(.*)" + manipulatedString + "(.*)")) {
    	    line = currentLine;
    	    found = true; 									//announce room is found
    	    continue; 										//skip this line (don't copy line to tmp file)
    	   }
    	   
    	   writer.write(currentLine + System.getProperty("line.separator"));
    	   writer.flush();
    	  }
    	  writer.close();
    	  reader.close();

    	  boolean successful = tempFile.renameTo(Gfile);

    	  TimeUnit.SECONDS.sleep(1);
    	  ClearScreen();
    	  if (found) { 									//if announced room was found
    	   System.out.println("Room number " + roomtoCheckout + " Checked-out Successfully ");
    	   System.out.println("\n***Contacted Ministry of Health and the request for release was approved");
    	   System.out.println("\nGoing back to Main Menu.");
    	  } else { 											//else->room was not found in file
    	   System.out.println("No room Checked-Out: Room number " + roomtoCheckout + " is currently empty and under disinfection process ");
    	   System.out.println("\nGoing back to Main Menu.");
    	  }
    	  TimeUnit.SECONDS.sleep(4);
    	  ClearScreen();

    	 }
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/

	/*********************************************************************************************************************************************/
	   public static Guest createGuest() {
   	  	Guest guest = new Guest();	
   	  	System.out.println("Please fill-up Guest Details for Check-in :");
   	  	System.out.println();
       	System.out.println();
       	
       	
       	System.out.println("Guest Details:");
       	System.out.println("Guest Name:");
       	guest.Name = input.next();
       	System.out.println("Guest ID: ");
   		guest.UID = input.nextDouble();
       	
       	while(guest.Sex != 1 && guest.Sex !=2) {
       		System.out.println("Guest Sex: 1-male, 2-female ");
       		guest.Sex = input.nextInt();
           	if(guest.Sex != 1 && guest.Sex !=2) {
           		System.out.println("Please Choose 1 or 2 only.");
           		System.out.println();
           	}
       	}  	
       	while(guest.Age <= 0 || guest.Age > 120) {
       		System.out.println("Guest Age:");
       		guest.Age = input.nextInt();
           	if(guest.Age <= 0 || guest.Age > 120) {
           		System.out.println("Please enter valid age only.");
           		System.out.println();
           	}
       	}
       	while(guest.Religious != 1 && guest.Religious !=2) {
       		System.out.println("Religious?:  1-Yes, 2-No ");
       		guest.Religious = input.nextInt();
           	if(guest.Religious != 1 && guest.Religious !=2) {
           		System.out.println("Please Choose 1 or 2 only.");
           		System.out.println();
           	}
       	}
       	return guest;
   	}
	/*********************************************************************************************************************************************/
	/*********************************************************************************************************************************************/
	 public static String GuesttoString(Guest g){//overriding toString() method  
		  String parsedGuest =  g.UID+","+g.Name+","+g.Age+","+g.Sex+","+g.Religious+",";  
		  return parsedGuest;
		 }  
	/*********************************************************************************************************************************************/
	 public static String RoomtoString(Room r){//overriding toString() method  
		  String parsedRoom =  "Room:roomNumber,roomtype-"+r.roomNumber+","+r.roomtype+".";  
		  return parsedRoom;
		 }  
	 /*** @throws InterruptedException *******************************************************************************************************************************************/
	 public void Restart(SingletonReception S) throws InterruptedException {
		 int validateDecision=0;
	  ClearScreen();
	  while(validateDecision!=1 &&  validateDecision !=2) {
		  System.out.print("Are you sure you want to log-out?\n\nPlease choose 1-YES, 2-No: ");
		  validateDecision = input.nextInt();
		  if(validateDecision !=1 &&  validateDecision !=2) {
			  validateDecision = 0;
			  ClearScreen();
			  System.out.print("Invalid input -  ");

		  }
		  if(validateDecision == 2) {
			  ClearScreen();
			  System.out.print("Going back to Main Menu ");
			  TimeUnit.SECONDS.sleep(2);
			  ClearScreen();
			  return;
		  }
		  else if(validateDecision == 1) ClearScreen();
			  }
	  System.out.println("Thanks for using Corona Hotel Management System.\nsee you soon!\n\nLogging Out.....");
	  TimeUnit.SECONDS.sleep(4);
	  SingletonReception.restart = true;
	  Access.userLoggedIn=0;
	 }
	 /*********************************************************************************************************************************************/
	 /**************************************Product********************************************************************************************/
	 /*********************************************************************************************************************************************/
		public void ProductReservation(SingletonReception control) 
			{

				

					boolean userWantToExit = false;
					String firstMenuChosen;
					do
					{
						firstMenuChosen = firstMenu();
						ClearScreen();
						switch (firstMenuChosen)
						{
							case "1":
								insertNewProduct();
								break;
							case "2":
								showListOfProductsByType();
								break;
							case "3":
								changeAmountOfProduct();
								break;
							case "4":
								showCompleteDataOfProduct();
								break;

							case "8":
								userWantToExit = true;
								break;
						}
					} while (userWantToExit == false);
				}


				public void showCompleteDataOfProduct()
				{
					int catalogNumber;
					catalogNumber = askFromeUserCatalogNum();
					if (productsCollection.IsProductExist(catalogNumber))
					{
						System.out.printf("%1$s - full  details:" + "\r\n", catalogNumber);
						System.out.println(productsCollection.ShowProductDetails(catalogNumber));
					}
					else
					{
						System.out.println("There is no such product");
					}
				}

				public void changeAmountOfProduct()
				{
					int catalogNum;
					catalogNum = askFromeUserCatalogNum();
					if (productsCollection.IsProductExist(catalogNum))
					{
						try
						{
							int amountToAdd = askFromUserToChooseAndCheckHim("What the amount you want to add?", 1, Integer.MAX_VALUE);
							productsCollection.changeAmount(productsCollection.GetProduct(catalogNum).getProductQuantity() + amountToAdd, catalogNum);
							System.out.printf("product - %1$s, amount was changed to %2$s." + "\r\n", catalogNum, productsCollection.GetProduct(catalogNum).getProductQuantity());
						}
						catch (IllegalArgumentException ex)
						{
							System.out.println(ex.getMessage());
						}
					}
					else
					{
						System.out.println("There is no such product");
					}
				}

				public void showListOfProductsByType()
				{
					ArrayList<Product> cooshenTypeProduct;
					eProductType enumSelectedCondition;
					enumSelectedCondition = askFromeUserProductType();
					cooshenTypeProduct = productsCollection.GetListOfProducts(enumSelectedCondition);
					System.out.printf("List of "+ enumSelectedCondition.toString() +"\r\n", enumSelectedCondition);

					for (Product product : cooshenTypeProduct)
					{
						//can also add catalog number and minimum amount..
						System.out.println(product.getProductName() + " , currently amount - " + product.getProductQuantity());
					}
				}

				private eProductType askFromeUserProductType()
				{
					eProductType productType;


					productType =  eProductType.valueOf(enumAskAndUserSelection(eProductType.class, "What is the product type?") );
					return productType;
				}

				private void insertNewProduct()
				{
					//eProductType enumProductType;
					int productCatalogNum;
					eProductType productType;
					Product product;
					try
					{
						//need to do check for user input
						productCatalogNum = (int)askFromeUserCatalogNum();
						if (productsCollection.IsProductExist(productCatalogNum))
						{
							System.out.println("this product is already exist");
						}
						else
						{
							//get from user type of product to insert
							productType = eProductType.valueOf( enumAskAndUserSelection(eProductType.class, "What kind of product ? "));  
									//eProductType.values()[ enumAskAndUserSelection(eProductType.class, "What kind of product ? ")];

							productsCollection.InsertNewProductModel(productType, productCatalogNum);
							product = productsCollection.GetProduct(productCatalogNum);
							askForUniqueProperties(product);
							System.out.println("Product has been added successfully");
						}
					}
					catch (RuntimeException ex)
					{
						System.out.println(ex.getMessage());
					}
				}

			
				private <E extends Enum<E>> int enumAskAndUserSelection (Class<E> enumType, String stringToPrint)
				{
			        int i;
			        System.out.println(stringToPrint);
			        i = 1;
			        for (E en : EnumSet.allOf(enumType))
			        {
			        	System.out.println(i + ". " + en);
			            i++;
			        }

			        return askFromUserToChooseAndCheckHim(1, enumType.getEnumConstants().length);
				}

				private void askForUniqueProperties(Product product)
				{
					boolean goodInput;
					int indexToSet = 1;
					String userInput;
					java.lang.Iterable<String> productProperties = product.GetProductProperties();

					for (String uniqueFeature : productProperties)
					{
						do
						{
							System.out.printf("%1$s" + "\r\n", uniqueFeature);
							userInput = new Scanner(System.in).nextLine();
							goodInput = product.SetProductProperties(userInput, indexToSet);
							if (goodInput)
							{
								indexToSet++;
							}
							else
							{
								System.out.println("Bad input, try again...");
							}
						} while (!goodInput);
					}
				}

				private int askFromeUserCatalogNum()
				 {
							System.out.println("Please enter product catalog number");
							// need to check user input(check if int - you can use tryParse)
							return Integer.parseInt(new Scanner(System.in).nextLine());
				 }

				private  String firstMenu()
					{
							String firstMenu = "Dear User, Which of the following operations would you like to make?" + "\r\n" + 
				"\r\n" + 
				"1. Insert a new product" + "\r\n" + 
				"\r\n" + 
				"2. Show list of products by type" + "\r\n" + 
				"\r\n" + 
				"3. Add the amount of a product" + "\r\n" + 
				"\r\n" + 
				"4. Show complete data of product" + "\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"8. Back to the main menu";
							return String.valueOf(askFromUserToChooseAndCheckHim(firstMenu, 1, 8));
						}

						private int askFromUserToChooseAndCheckHim(String i_StringToPrint, int i_From, int i_To)
						{
							boolean validTryParse;
							int intMenuChosen;
							String stringMenuChosen;
							boolean validInput = true;

							do
							{
								System.out.println(i_StringToPrint);
								if (validInput == false)
								{
									System.out.printf("wrong...please choose again (%1$s-%2$s)" + "\r\n", i_From, i_To);
								}

								stringMenuChosen = new Scanner(System.in).nextLine();
								tangible.OutObject<Integer> tempOut_intMenuChosen = new tangible.OutObject<Integer>();
								validTryParse = tangible.TryParseHelper.tryParseInt(stringMenuChosen, tempOut_intMenuChosen);
								intMenuChosen = tempOut_intMenuChosen.outArgValue;
								if (intMenuChosen <= i_To && intMenuChosen >= i_From && validTryParse)
								{
									validInput = true;
								}
								else
								{
									validInput = false;
								}
							} while (!validInput);

							return intMenuChosen;
						}

						private int askFromUserToChooseAndCheckHim(int i_From, int i_To)
						{
							boolean validTryParse;
							int intMenuChosen;
							String stringMenuChosen;
							boolean validInput = true;
							do
							{
								if (validInput == false)
								{
									System.out.printf("wrong...please choose again " + "\r\n", i_From, i_To);
								}

								stringMenuChosen = new Scanner(System.in).nextLine();
								//tangible.OutObject<Integer> tempOut_intMenuChosen = new tangible.OutObject<Integer>();
								//validTryParse = tangible.TryParseHelper.tryParseInt(stringMenuChosen, tempOut_intMenuChosen);
								try {
									intMenuChosen = Integer.parseInt(stringMenuChosen);
								}
								catch (NumberFormatException e)
								{
								   intMenuChosen = -1;
								}
								if (intMenuChosen != -1 && intMenuChosen <= i_To && intMenuChosen >= i_From)
								{
									validInput = true;
								}
								else
								{
									validInput = false;
								}
							} while (!validInput);

							return intMenuChosen;
						}
}

