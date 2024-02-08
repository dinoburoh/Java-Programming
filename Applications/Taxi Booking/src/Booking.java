import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {

    public static void allocateTaxiIfAvailable(Taxi[] taxis, char pickupPoint, char dropPoint, int pickupTime) {

        List<Taxi> availableTaxis = new ArrayList<>();

        for (int i = 0; i < taxis.length; i++) {
            if (taxis[i].isAvailable) {
                if ((pickupTime > taxis[i].timeAtAvailableSpot) && (pickupTime - taxis[i].timeAtAvailableSpot) >= Math
                        .abs(pickupPoint - taxis[i].nextAvailableSpot)) {
                    availableTaxis.add(taxis[i]);
                }
            }
        }

        if (availableTaxis.size() == 0) {
            System.out.println("Booking rejected. No taxi can be allocated at the desired time and location!!");
        }

        else {
            System.out.println("Taxi can be allocated");
        }

        if (availableTaxis.size() > 0) {
            Taxi nearestTaxi = availableTaxis.get(0);

            for (int i = 1; i < availableTaxis.size(); i++) {
                if (Math.abs((pickupPoint - '0') - (availableTaxis.get(i).nextAvailableSpot - '0')) < Math
                        .abs(pickupPoint - nearestTaxi.nextAvailableSpot)) {
                    nearestTaxi = availableTaxis.get(i);
                }

                else if (Math.abs((pickupPoint - '0') - (availableTaxis.get(i).nextAvailableSpot - '0')) == Math
                        .abs(pickupPoint - nearestTaxi.nextAvailableSpot)) {
                    if (availableTaxis.get(i).priceGenerated < nearestTaxi.priceGenerated)
                        nearestTaxi = availableTaxis.get(i);
                }
            }

            Taxi selectedTaxi = nearestTaxi;
            System.out.println("Taxi - " + selectedTaxi.taxiID + " is alloted");

            int totalDistance = Math.abs((pickupPoint - '0') - (dropPoint - '0')) * 15;

            selectedTaxi.pickupSpot = pickupPoint;
            selectedTaxi.dropSpot = dropPoint;
            selectedTaxi.pickupTime = pickupTime;
            selectedTaxi.isAvailable = false;
            selectedTaxi.priceGenerated = 100 + (totalDistance - 5) * 10;
            selectedTaxi.timeAtAvailableSpot = pickupTime + Math.abs((pickupPoint - '0') - (dropPoint - '0'));
            selectedTaxi.nextAvailableSpot = dropPoint;

            bookingDetails(taxis, selectedTaxi);
        }

    }

    public static void bookingDetails(Taxi[] taxis, Taxi selectedTaxi) {

        Taxi.taxiDetails();

        for (Taxi t : taxis) {
            if (t == selectedTaxi) {
                System.out.println("Taxi - " + t.taxiID + "  Total Earnings: Rs. " + t.priceGenerated);
                System.out.println("");
                System.out.println("  " + t.taxiID + "       " + t.pickupSpot + "       " + t.dropSpot
                        + "       " + t.pickupTime
                        + "       " + t.timeAtAvailableSpot + "       " + t.priceGenerated);
                System.out.println("");
                break;
            }
        }
    }

    public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Taxi count: ");
            int taxiCount = input.nextInt();
            System.out.println("Enter the pickup point (A,B,C,D,E,F) ");
            char pickupPoint = input.next().charAt(0);
            System.out.println("Enter the drop point (A,B,C,D) ");
            char dropPoint = input.next().charAt(0);
            System.out.println("Enter the pickup time after 8 in 24 hours");
            int pickupTime = input.nextInt();
            System.out.println("Press 1 for booking");
            int key = input.nextInt();
    
            Taxi[] taxis = new Taxi[taxiCount];
    
            for (int i = 0; i < taxiCount; i++) {
                taxis[i] = new Taxi();
            }
    
            if (key == 1) {
                allocateTaxiIfAvailable(taxis, pickupPoint, dropPoint, pickupTime);
            }
    
        }
    }
}
