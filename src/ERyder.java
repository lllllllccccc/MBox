
class ERyder{
    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    
    public ERyder() {
        this.bikeID = 0;
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
    }

   
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }


    public void ride(){
        if (this.batteryLevel > 0 && this.isAvailable) {
            System.out.println("自行车可用，可以骑行。");
        } else {
            System.out.println("自行车不可用，无法骑行。");
        }
    }

    public void printBikeDetails() {
        System.out.println("自行车ID: " + this.bikeID);
        System.out.println("电池电量: " + this.batteryLevel + "%");
        System.out.println("是否可用: " + (this.isAvailable ? "是" : "否"));
        System.out.println("总行驶距离: " + this.kmDriven + " 公里");
        System.out.println("------------------------");
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
            System.out.println("错误：电池电量必须在0到100之间。设置失败。");
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
}

public class Main {
    public static void main(String[] args) {
       
        ERyder bike1 = new ERyder();
        System.out.println("自行车1的初始信息：");
        bike1.printBikeDetails();
        ERyder bike2 = new ERyder(1001, 80, true, 150.5);
        System.out.println("尝试骑行车2：");
        bike2.ride();
        System.out.println("自行车2的详细信息：");
        bike2.printBikeDetails();
        System.out.println("尝试将自行车2的电量设置为150%：");
        bike2.setBatteryLevel(150);
        bike2.printBikeDetails();
    }
}
