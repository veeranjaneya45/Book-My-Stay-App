/**
 * Author: Veeranjaneya
 * Use Case 11: Concurrent Booking Simulation
 */

import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Queue
class BookingRequestQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
    }

    public synchronized BookingRequest getRequest() {
        return queue.poll();
    }
}

// Inventory
class RoomInventory {
    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 4);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public synchronized boolean allocateRoom(String roomType) {
        if (rooms.get(roomType) > 0) {
            rooms.put(roomType, rooms.get(roomType) - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

// Allocation Service
class RoomAllocationService {
    private Map<String, Integer> counters = new HashMap<>();

    public RoomAllocationService() {
        counters.put("Single", 1);
        counters.put("Double", 1);
        counters.put("Suite", 1);
    }

    public synchronized String generateRoomId(String roomType) {
        int num = counters.get(roomType);
        counters.put(roomType, num + 1);
        return roomType + "-" + num;
    }
}

// Thread Processor
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(BookingRequestQueue queue,
                                      RoomInventory inventory,
                                      RoomAllocationService allocationService) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                request = queue.getRequest();
            }

            if (request == null) break;

            synchronized (inventory) {
                if (inventory.allocateRoom(request.roomType)) {
                    String roomId = allocationService.generateRoomId(request.roomType);
                    System.out.println("Booking confirmed for Guest: " +
                            request.guestName + ", Room ID: " + roomId);
                } else {
                    System.out.println("Booking failed for Guest: " +
                            request.guestName + " (No rooms available)");
                }
            }
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        queue.addRequest(new BookingRequest("Abhi", "Single"));
        queue.addRequest(new BookingRequest("Vanmathi", "Double"));
        queue.addRequest(new BookingRequest("Kural", "Suite"));
        queue.addRequest(new BookingRequest("Subha", "Single"));

        // Create threads
        Thread t1 = new Thread(new ConcurrentBookingProcessor(queue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(queue, inventory, allocationService));

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Display final inventory
        inventory.displayInventory();
    }
}