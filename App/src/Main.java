//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Queue;
import java.util.LinkedList;

/*
=========================================================
Author : VEERANJANEYA REDDY
Use Case 5 : Booking Request (First-Come-First-Served)
=========================================================

Description:
This program demonstrates how booking requests
are accepted and stored in a queue using FIFO
(First-Come-First-Served) order.

No room allocation or inventory update is done here.
Requests are simply collected and processed later.

Version : 5.0
*/


/*-----------------------------------------
CLASS : Reservation
-----------------------------------------*/
class Reservation {

    // Name of the guest
    private String guestName;

    // Requested room type
    private String roomType;

    // Constructor
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/*-----------------------------------------
CLASS : BookingRequestQueue
-----------------------------------------*/
class BookingRequestQueue {

    // Queue storing booking requests
    private Queue<Reservation> requestQueue;

    // Constructor
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    // Get next request
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    // Check if queue has pending requests
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/*-----------------------------------------
MAIN CLASS
-----------------------------------------*/
public class Main{

    public static void main(String[] args) {

        System.out.println("Booking Request Queue\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {

            Reservation r = bookingQueue.getNextRequest();

            System.out.println("Guest Name: " + r.getGuestName());
            System.out.println("Requested Room: " + r.getRoomType());
            System.out.println();
        }
    }
}
