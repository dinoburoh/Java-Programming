import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {

    public static void allocateTaxiIfAvailable(Taxi[] taxis, char pickupPoint, char dropPoint, int pickupTime) {

        List<Taxi> availableTaxis = new ArrayList<>();

        for (int i = 0; i < taxis.length; i++) {

            if (pickupTime >= taxis[i].nextAvailableTime) {
                taxis[i].isAvailable = true;
            }

            if (taxis[i].isAvailable && (pickupTime - taxis[i].nextAvailableTime) >= Math
                    .abs((pickupPoint - '0') - (taxis[i].nextAvailableSpot - '0'))) {
                availableTaxis.add(taxis[i]);
            }
        }

        if (availableTaxis.size() == 0) {
            System.out.println("Booking rejected. No taxi can be allocated at the desired time and location!!");
            return;
        }

        else {
            System.out.println("");
            System.out.println("TAXI CAN BE ALLOCATED");
        }

        if (availableTaxis.size() > 0) {

            Taxi nearestTaxi = availableTaxis.get(0);

            for (Taxi t : availableTaxis) {
                if (Math.abs((pickupPoint - '0') - (t.nextAvailableSpot - '0')) < Math
                        .abs((pickupPoint - '0') - (nearestTaxi.nextAvailableSpot - '0'))) {
                    nearestTaxi = t;
                }

                else if (Math.abs((pickupPoint - '0') - (t.nextAvailableSpot - '0')) == Math
                        .abs((pickupPoint - '0') - (nearestTaxi.nextAvailableSpot - '0'))) {
                    if (t.priceGenerated < nearestTaxi.priceGenerated)
                        nearestTaxi = t;
                }
            }

            Taxi selectedTaxi = nearestTaxi;
            System.out.println("");
            System.out.println("Taxi - " + selectedTaxi.taxiID + " is alloted");
            System.out.println("");

            int totalDistance = Math.abs((pickupPoint - '0') - (dropPoint - '0')) * 15;

            selectedTaxi.pickupSpot = pickupPoint;
            selectedTaxi.dropSpot = dropPoint;
            selectedTaxi.pickupTime = pickupTime;
            selectedTaxi.isAvailable = false;
            selectedTaxi.priceGenerated = 100 + (totalDistance - 5) * 10;
            selectedTaxi.nextAvailableTime = pickupTime + Math.abs((pickupPoint - '0') - (dropPoint - '0'));
            selectedTaxi.dropTime = selectedTaxi.nextAvailableTime;
            selectedTaxi.nextAvailableSpot = dropPoint;
            selectedTaxi.trips.add(
                    "  " + selectedTaxi.taxiID + "       " + selectedTaxi.pickupSpot + "       " + selectedTaxi.dropSpot
                            + "        " + selectedTaxi.pickupTime
                            + "             " + selectedTaxi.dropTime + "        " + selectedTaxi.priceGenerated);

            bookingDetails(taxis, selectedTaxi, selectedTaxi.trips);
        }

    }

    public static void bookingDetails(Taxi[] taxis, Taxi selectedTaxi, List<String> trips) {

        for (Taxi t : taxis) {
            t.taxiDetails(t, selectedTaxi);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Taxi count: ");
        int taxiCount = input.nextInt();
        Taxi[] taxis = new Taxi[taxiCount];
        for (int i = 0; i < taxiCount; i++) {
            taxis[i] = new Taxi();
        }

        while (true) {

            System.out.println("Press 1 for booking and 2 to print details");
            int key = input.nextInt();

            if (key == 1) {
                System.out.println("Enter the pickup point (A,B,C,D,E,F) ");
                char pickupPoint = input.next().charAt(0);
                System.out.println("Enter the drop point (A,B,C,D, E, F) ");
                char dropPoint = input.next().charAt(0);
                System.out.println("Enter the pickup time after 8 in 24 hours");
                int pickupTime = input.nextInt();
                allocateTaxiIfAvailable(taxis, pickupPoint, dropPoint, pickupTime);
                continue;
            }

            if (key == 2) {
                for (Taxi t : taxis) {
                    t.taxiDetails(t, t);
                }
                continue;
            }
        }

        // input.close();
    }
}
