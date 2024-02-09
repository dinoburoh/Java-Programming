import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private static int taxiCount = 0;
    public int taxiID;
    int priceGenerated;
    int totalPriceGenerated;
    boolean isAvailable;
    int pickupTime;
    int dropTime;
    int nextAvailableTime;
    char pickupSpot;
    char dropSpot;
    char nextAvailableSpot;
    List<String> trips;

    public Taxi() {
        taxiCount += 1;
        this.taxiID = taxiCount;
        this.priceGenerated = 0;
        this.isAvailable = true;
        this.nextAvailableTime = 8;
        this.nextAvailableSpot = 'A';
        this.pickupSpot = '-';
        this.dropSpot = '-';
        this.trips = new ArrayList<>();
    }

    public void taxiDetails(Taxi t, Taxi selectedTaxi) {
        if(t == selectedTaxi)
            t.totalPriceGenerated += t.priceGenerated;
        System.out.println("Taxi - " + t.taxiID + "  Total Earnings: Rs. " + t.totalPriceGenerated);
        System.out.println("");
        System.out.println("Taxi-ID   From    To     PickupTime    DropTime   Amount");
        for(String trip: this.trips){
            System.out.println(trip);
        }
        System.out.println("");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
    }

}
