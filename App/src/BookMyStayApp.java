/**
 * Author: Veeranjaneya
 * Use Case 7: Add-On Service Selection
 */

import java.util.*;

/**
 * CLASS - Service
 * Represents an optional add-on service
 */
class Service {
    private String serviceName;
    private double cost;

    // Constructor
    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * CLASS - AddOnServiceManager
 * Manages services for reservations
 */
class AddOnServiceManager {
    private Map<String, List<Service>> servicesByReservation;

    // Constructor
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {
        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {
        double total = 0.0;

        List<Service> services = servicesByReservation.get(reservationId);
        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}

/**
 * MAIN CLASS
 */
public class Main{

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Adding services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Spa", 700));
        manager.addService(reservationId, new Service("Airport Pickup", 300));

        // Calculate total
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
