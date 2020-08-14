package hit.co.il;
import java.util.Scanner;

public class Guest{

	//Variables
    protected static int guestCount=0;
    protected int numOfGuestsToCheckIn;
    protected String Name;
    protected int Sex;
    protected int Age;
    protected int roomNumber;
    protected double UID;
    protected int Religious;
	public static Scanner input = new Scanner(System.in);	


    //Methods
    //Ctor
    public Guest()
    {
    	guestCount++;
    }    

    public static int guestCounter() {
        return guestCount;
    }  

    public void printCheckInDetails() {
    	System.out.println();
    	System.out.println();
    	System.out.println("Check in details:");
    	System.out.println();
    	System.out.println(Guest.guestCounter()+ " Guests checked-in successfuly:");
    	System.out.println();
    	for(int i = 1  ;  i <= numOfGuestsToCheckIn  ;  i++) {
        	System.out.println("Guest_"+ i + " info:");
        	System.out.println("Name: " + Name);
        	System.out.println("Sex: " + Sex);
        	System.out.println("Age " + Age);
        	System.out.println("ID: " + UID);
        	System.out.println("Religoius: " + Religious);
        	System.out.println();
    	}
    }
 }