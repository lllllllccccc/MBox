import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class ERyderSystem {
    private final Deque<ERyderLog> logStack;
    private final Queue<BikeRequest> requestQueue;
    private int logId = 1;
    private int availableBikes = 2;

    public ERyderSystem() {
        this.logStack = new ArrayDeque<>();
        this.requestQueue = new ArrayDeque<>();
    }

    public void addLog(String event) {
        ERyderLog log = new ERyderLog(logId++, event, LocalDateTime.now());
        logStack.push(log);
    }

    public void viewSystemLogs() {
        System.out.println("\n===== System Log Stack =====");
        if (logStack.isEmpty()) {
            System.out.println("No log records");
            return;
        }
        for (ERyderLog log : logStack) {
            System.out.println(log);
        }
    }

    public void addBikeRequest(String email, String location) {
        BikeRequest request = new BikeRequest(email, location, LocalDateTime.now());
        requestQueue.offer(request);
        System.out.println("Request added to queue: " + request);
    }

    public void viewRequestQueue() {
        System.out.println("\n===== Pending Bike Request Queue =====");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (BikeRequest request : requestQueue) {
            System.out.println(request);
        }
    }

    public void updateQueue() {
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty, no removal needed");
            return;
        }
        BikeRequest removed = requestQueue.poll();
        System.out.println("Removed first request: " + removed);
    }

    public void rentBike(String email, String location) {
        if (availableBikes > 0) {
            availableBikes--;
            addLog("Bike rented successfully | User: " + email);
            System.out.println("Bike rented successfully!");
        } else {
            addLog("No bikes available, user " + email + " added to request queue");
            addBikeRequest(email, location);
        }
    }

    public void returnBike() {
        availableBikes++;
        addLog("Trip ended, bike returned");
        System.out.println("Bike returned successfully!");
    }

    public void showAdminPanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Admin Panel =====");
            System.out.println("1. View System Logs");
            System.out.println("2. Manage Pending Bike Requests");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewSystemLogs();
                    break;
                case 2:
                    showRequestSubPanel();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showRequestSubPanel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Manage Bike Requests =====");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue (Remove First Request)");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewRequestQueue();
                    break;
                case 2:
                    updateQueue();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        ERyderSystem system = new ERyderSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== ERyder Main Menu =====");
            System.out.println("1. Rent a Bike");
            System.out.println("2. Return a Bike");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter rental location: ");
                    String location = scanner.nextLine();
                    system.rentBike(email, location);
                    break;
                case 2:
                    system.returnBike();
                    break;
                case 3:
                    system.showAdminPanel();
                    break;
                case 4:
                    System.out.println("Exiting system");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
