package hit.co.il;
import java.io.IOException;

public class SingletonReception
{

	static SingletonReception mySingleton;
	//static Model myModel;
	static boolean restart = false;
	
	private SingletonReception() {}
	
    /*public void Invoke(Model invoker) {
		getMySingleton();
	}*/
	
	public static SingletonReception getMySingleton() {
		if (mySingleton == null)
			mySingleton = new SingletonReception();
		return mySingleton;
	}
	Model myModel = new Model();
	
	public void Controller(SingletonReception control) throws InterruptedException, IOException  {
		myModel.ClearScreen();
		myModel.getVars(); //get last checked in room-number from Variables file to continue check-in operations from the last room number 

		restart = false;
		
		while(true)
    	{
			int action = 0;
    		
			System.out.println("Debug info:  * Controller took control of the system * ");
			System.out.println();
			System.out.println();
		
        	while(action <= 0 || action > 5) {
        	System.out.println("\nEnter your choice :\n1.Check-In \n2.Check-Out \n3.Order to room\n4.Exit\n");
        	action = Model.input.nextInt();
        	
        	// associate user actions to the right flow when logged in to the system
        	 switch (action) // associate user actions to the right flow when logged in to the system
        	    {
        	     case 1:
        	      myModel.CheckIn(control);
        	      break;
        	     case 2:
        	      myModel.CheckOut(control);
        	      break;
        	     case 3:
        	      myModel.ProductReservation(control);
        	      break;
        	     case 4:
        	      myModel.Restart(control);
        	      break;

        	    }
        	    if (restart) break;
        	   }
        	   if (restart) break;

        	  }

        	 }
        	}