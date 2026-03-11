//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashMap;
import java.util.Map;

/*
=========================================================
Author : VEERANJANEYA REDDY
Use Case 4 : Room Search & Availability Check
=========================================================

Description:
This program demonstrates how guests can view
available rooms without modifying inventory data.

Room availability is stored in a centralized
HashMap inside the RoomInventory class.

The RoomSearchService reads data from inventory
and displays available rooms. It performs
read-only operations on the inventory.

Version : 4.0
*/


/*-----------------------------------------
CLASS : Room
-----------------------------------------*/
class Room {

    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails(int available) {
        System.out.println(type + " Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }

    public String getType() {
        return type;
    }
}


/*-----------------------------------------
CLASS : RoomInventory
-----------------------------------------*/
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}


/*-----------------------------------------
CLASS : RoomSearchService
-----------------------------------------*/
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Hotel Room Inventory Status\n");

        if (availability.get("Single") > 0) {
            singleRoom.displayDetails(availability.get("Single"));
        }

        if (availability.get("Double") > 0) {
            doubleRoom.displayDetails(availability.get("Double"));
        }

        if (availability.get("Suite") > 0) {
            suiteRoom.displayDetails(availability.get("Suite"));
        }
    }
}


/*-----------------------------------------
MAIN CLASS : UseCase4RoomSearch
-----------------------------------------*/
public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}