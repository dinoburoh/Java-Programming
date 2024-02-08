
public class Taxi {
    private static int taxiCount = 0;
    public int taxiID;
    int priceGenerated;
    boolean isAvailable;
    int pickupTime;
    int timeAtAvailableSpot;
    char pickupSpot;
    char dropSpot;
    char nextAvailableSpot;

    public Taxi() {
        taxiCount += 1;
        this.taxiID = taxiCount;
        this.priceGenerated = 0;
        this.isAvailable = true;
        this.timeAtAvailableSpot = 8;
        this.nextAvailableSpot = 'A';
    }

    public static void taxiDetails() {
        System.out.println("Taxi No:          Total Earnings:");
        System.out.println("");
        System.out.println("Taxi-ID   From    To     PickupTime    DropTime   Amount");
        System.out.println("");
    }

}
