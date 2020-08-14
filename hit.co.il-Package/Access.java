
package hit.co.il;
import java.util.concurrent.TimeUnit;

public class Access {

	 //Variables
	 static String absolutePath;
	 static int successfullyRegistered;
	 static int userLoggedIn = 0;
	 static int tries;
	 static int existUsersAccessFile = 0;
	 static int accessDecision = 0;


	 //Ctor
	 public Access() {}




	 public static void main(String[] args) throws InterruptedException {

	  System.out.println("                         *** Welcome to Corona Hotel Management-System ***");
	  System.out.println();
	  TimeUnit.SECONDS.sleep(2);


	  try {

	   while (true) {
	    Model user = new Model();
	    existUsersAccessFile = user.CreateAccessFile(); //go to create file function and create file for users access if it doesn't exist yet, return 1 if file is created. if file already exist return 0.

	    accessDecision = 0;
	    //while user does not have access decision yet (didn't chose to login or register)    
	    while (accessDecision == 0) {
	     if (existUsersAccessFile == 1 && !user.AccessfileName.isEmpty()) // if access file exist and it is not empty - then ask the user if he want's to login or register
	      user.userDecision();

	     else // if file not exist or exist but empty - that means no users exist in access file - go to register scenario
	     {
	      for (tries = 0; successfullyRegistered != 1 && tries < 3; tries++) {
	       System.out.println("Please Register to Enter Reception");
	       successfullyRegistered = user.Register();
	       if (successfullyRegistered == 1) {
	        accessDecision = 1;
	        break;
	       }
	       System.out.println("Failed to Register Please try again");
	       System.out.println();
	      }
	      if (tries == 3) {
	       System.out.println("3 Failed password attempts detected, Starting registration again");
	       System.out.println();
	       TimeUnit.SECONDS.sleep(3);
	       accessDecision = 0;
	      }

	     }
	    }

	    while (userLoggedIn != 1) {


	     // associate user decision to the right flow (register / login) to be able to use the system
	     switch (accessDecision) {
	      case 1:
	       //go to login scenario
	       userLoggedIn = user.Login();
	       accessDecision = userLoggedIn;
	       if (userLoggedIn == 2) continue;
	       break;

	      case 2:
	       //go to register scenario
	       successfullyRegistered = user.Register();

	       if (successfullyRegistered == 1 || successfullyRegistered == 3) {
	        accessDecision = 3;
	       }
	       break;
	      case 3:
	       //go to decision scenario
	       accessDecision = user.userDecision();
	       break;
	     }

	    }

	    //taking control of the system using Singleton instance after getting Access.
	    SingletonReception.mySingleton = SingletonReception.getMySingleton();
	    SingletonReception.mySingleton.Controller(SingletonReception.mySingleton);

	   }


	  } catch (Exception exc) {
	   exc.printStackTrace();
	  }


	 }
	}
