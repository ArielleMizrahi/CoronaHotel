package hit.co.il;
import java.io.IOException;

public class Room
{
	      public Guest roomGuests[];
		  private boolean available;
		  //private UserGuest occupant;
		  public static int roomNumber;
		  public String roomtype = null;
		  private int roomCapacity;
		  static String absolutePath;
          //private FloorNum FloorN;

  		 //C`tor
		  public Room(int guestsNumber) throws IOException {
			  Room.roomNumber++;
		    this.roomCapacity = guestsNumber;
		    
		  }

		  public boolean isAvailable() {
		    return this.available;
		  }

		  public boolean getAvailable() {
		    return available;
		  }

		  public void setAvailable(boolean available) {
		    this.available = available;
		  }
		  
		  public int getRoomNumber() {
			    return roomNumber;
		  }
		  public void setRoomNumber(int roomNum) {
			  this.roomNumber = roomNum;
	      }
//		  public Guest getGuest() {
//			  return occupant;
//		  }
//		  public void setGuest(Guest occupant) {
//			  this.occupant = occupant;
//		  }		  

}