public class ERyder {
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    private int totalUsageInMinutes;
    private double totalFare;

    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = "default_user";
        this.LINKED_PHONE_NUMBER = "111-111-1111";
    }

    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    public void ride() {
        if (this.batteryLevel > 0 && this.isAvailable) {
            System.out.println("Bike is available, can ride.");
        } else {
            System.out.println("Bike is unavailable, cannot ride.");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + this.bikeID);
        System.out.println("Battery Level: " + this.batteryLevel + "%");
        System.out.println("Is Available: " + (this.isAvailable ? "Yes" : "No"));
        System.out.println("Total Distance Driven: " + this.kmDriven + " km");
        System.out.println("------------------------------");
    }

    public void printRideDetails(int usageInMinutes) {
        this.totalUsageInMinutes = usageInMinutes;
        this.totalFare = calculateFare(usageInMinutes);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Usage in Minutes: " + totalUsageInMinutes);
        System.out.println("Total Fare: $" + totalFare);
        System.out.println("------------------------------");
    }

    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Error: Battery level must be between 0 and 100. Setting failed.");
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }

    public static void main(String[] args) {
        ERyder bike1 = new ERyder(1, 1, true, 1.0);
        ERyder bike2 = new ERyder(2, 2, true, 2.0, "user_1", "222-222-2222");

        bike1.printRideDetails(1);
        bike2.printRideDetails(2);
    }
}
