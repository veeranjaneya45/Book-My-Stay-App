//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashMap;
import java.util.Map;

/*
 =========================================================
 Author : VEERANJANEYA REDDY
 Use Case 3 : Centralized Room Inventory Management
 =========================================================

 Description:
 This program demonstrates centralized room inventory
 management using a HashMap. The HashMap stores room
 type and available room count, ensuring a single
 source of truth for room availability.

 HashMap provides O(1) average lookup and update time,
 making it efficient for frequent availability checks.

 Version : 3.0
*/

/*-------------------------------------------------------
  CLASS : RoomInventory
-------------------------------------------------------*/
class RoomInventory {

    // HashMap to store room type and available rooms
    private Map<String, Integer> roomAvailability;

    // Constructor
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    // Initialize inventory data
    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    // Get room availability
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    // Update room availability
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/*-------------------------------------------------------
  CLASS : UseCase3InventorySetup (Main Class)
-------------------------------------------------------*/
public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Hotel Room Inventory Status\n");

        System.out.println("Single Room:");
        System.out.println("Beds: 1");
        System.out.println("Size: 250 sqft");
        System.out.println("Price per night: 1500.0");
        System.out.println("Available Rooms: " + availability.get("Single"));

        System.out.println();

        System.out.println("Double Room:");
        System.out.println("Beds: 2");
        System.out.println("Size: 400 sqft");
        System.out.println("Price per night: 2500.0");
        System.out.println("Available Rooms: " + availability.get("Double"));

        System.out.println();

        System.out.println("Suite Room:");
        System.out.println("Beds: 3");
        System.out.println("Size: 750 sqft");
        System.out.println("Price per night: 5000.0");
        System.out.println("Available Rooms: " + availability.get("Suite"));
    }
}
