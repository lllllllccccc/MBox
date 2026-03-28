import java.time.LocalDateTime;
import java.util.ArrayList;

public class BikeDatabase {
    public static ArrayList<Bike> bikes;

    static {
        bikes = new ArrayList<>();
        bikes.add(new Bike("B001", true, 90, LocalDateTime.now().minusHours(2), "Central Park"));
        bikes.add(new Bike("B002", false, 65, LocalDateTime.now().minusHours(1), "Station Square"));
        bikes.add(new Bike("B003", true, 80, LocalDateTime.now().minusHours(3), "Central Park"));
        bikes.add(new Bike("B004", true, 75, LocalDateTime.now().minusHours(1), "City Mall"));
    }
}
