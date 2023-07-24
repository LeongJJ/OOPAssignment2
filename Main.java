//Leong Jun Jie B210151B

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Pricing overseasNormalSeasonPricing = new Pricing("Overseas", "Normal", 4297, 4097, 3897, 500);
        Pricing overseasPeakSeasonPricing = new Pricing("Overseas", "Peak", 5097, 4897, 4697, 500);

        Pricing domesticNormalSeasonPricing = new Pricing("Domestic", "Normal", 1647, 1447, 1247, 500);
        Pricing domesticPeakSeasonPricing = new Pricing("Domestic", "Peak", 2447, 2247, 2047, 500);

        OverseasTour koreaTour = new OverseasTour("Korea", "Seoul", true, true, 6,
                "Joined and Private", overseasNormalSeasonPricing, overseasPeakSeasonPricing);

        DomesticTour sabahTour = new DomesticTour("Sabah", "Sabah", false, true, 3,
                "Private", domesticNormalSeasonPricing, domesticPeakSeasonPricing);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Travel Agency System!");
        System.out.println("Please choose your travel option: ");
        System.out.println("1. Overseas Tour");
        System.out.println("2. Domestic Tour");
        int travelChoice = scanner.nextInt();

        TravelTour selectedTour = null;
        if (travelChoice == 1) {
            selectedTour = koreaTour;
        } else if (travelChoice == 2) {
            selectedTour = sabahTour;
        } else {
            System.out.println("Invalid travel choice. Please select 1 or 2.");
            scanner.close();
            return;
        }

        System.out.println("\nPlease enter the number of people for each age group: ");
        System.out.print("Number of Adults: ");
        int numOfAdults = scanner.nextInt();

        System.out.print("Number of Children with Extra Bed: ");
        int numOfChildrenWithExtraBed = scanner.nextInt();

        System.out.print("Number of Children without Extra Bed: ");
        int numOfChildrenWithoutExtraBed = scanner.nextInt();

        System.out.print("Number of Infants: ");
        int numOfInfants = scanner.nextInt();

        double normalPrice = calculateTotalPrice(selectedTour, "Normal", numOfAdults, numOfChildrenWithExtraBed,
                numOfChildrenWithoutExtraBed, numOfInfants);

        double peakPrice = calculateTotalPrice(selectedTour, "Peak", numOfAdults, numOfChildrenWithExtraBed,
                numOfChildrenWithoutExtraBed, numOfInfants);

        
        displayTourDetails(selectedTour);

        System.out.println("\nTotal Price for the trip during Normal Season:" + normalPrice);
        displayPricePerAgeGroup(selectedTour, "Normal", numOfAdults, numOfChildrenWithExtraBed,
                numOfChildrenWithoutExtraBed, numOfInfants);

        System.out.println("\nTotal Price for the trip during Peak Season:" + peakPrice);
        displayPricePerAgeGroup(selectedTour, "Peak", numOfAdults, numOfChildrenWithExtraBed,
                numOfChildrenWithoutExtraBed, numOfInfants);

        scanner.close();
    }

    public static void displayTourDetails(TravelTour tour) {
        System.out.println("\nTour Details:");
        System.out.println("Country: " + tour.getCountry());
        System.out.println("State: " + tour.getState());
        System.out.println("Duration: " + tour.getDuration() + " days");
        System.out.println("Has Joined Tour: " + (tour.isHasJoinedTour() ? "Yes" : "No"));
        System.out.println("Has Private Tour: " + (tour.isHasPrivateTour() ? "Yes" : "No"));
        System.out.println("Tour Type: " + ((tour instanceof OverseasTour) ? ((OverseasTour) tour).getTourType() : ((DomesticTour) tour).getTourType()));
    }

    public static double calculateTotalPrice(TravelTour tour, String season, int numOfAdults,
            int numOfChildrenWithExtraBed, int numOfChildrenWithoutExtraBed,
            int numOfInfants) {
        Pricing pricing = season.equalsIgnoreCase("Peak") ? tour.getPeakSeasonPricing() : tour.getNormalSeasonPricing();

        double adultPrice = pricing.getAdultPrice();
        double childWithExtraBedPrice = pricing.getChildWithExtraBedPrice();
        double childWithoutExtraBedPrice = pricing.getChildWithoutExtraBedPrice();
        double infantPrice = pricing.getInfantPrice();

        double totalAdultPrice = numOfAdults * adultPrice;
        double totalChildWithExtraBedPrice = numOfChildrenWithExtraBed * childWithExtraBedPrice;
        double totalChildWithoutExtraBedPrice = numOfChildrenWithoutExtraBed * childWithoutExtraBedPrice;
        double totalInfantPrice = numOfInfants * infantPrice;

        return totalAdultPrice + totalChildWithExtraBedPrice + totalChildWithoutExtraBedPrice + totalInfantPrice;
    }

    public static void displayPricePerAgeGroup(TravelTour tour, String season, int numOfAdults,
            int numOfChildrenWithExtraBed, int numOfChildrenWithoutExtraBed,
            int numOfInfants) {
        Pricing pricing = season.equalsIgnoreCase("Peak") ? tour.getPeakSeasonPricing() : tour.getNormalSeasonPricing();

        double adultPrice = pricing.getAdultPrice();
        double childWithExtraBedPrice = pricing.getChildWithExtraBedPrice();
        double childWithoutExtraBedPrice = pricing.getChildWithoutExtraBedPrice();
        double infantPrice = pricing.getInfantPrice();

        // Calculate the total price for each age group and season
        double totalAdultPrice = numOfAdults * adultPrice;
        double totalChildWithExtraBedPrice = numOfChildrenWithExtraBed * childWithExtraBedPrice;
        double totalChildWithoutExtraBedPrice = numOfChildrenWithoutExtraBed * childWithoutExtraBedPrice;
        double totalInfantPrice = numOfInfants * infantPrice;

        System.out.println("Total Price for Adults: RM" + totalAdultPrice);
        System.out.println("Total Price for Children with Extra Bed: RM" + totalChildWithExtraBedPrice);
        System.out.println("Total Price for Children without Extra Bed: RM" + totalChildWithoutExtraBedPrice);
        System.out.println("Total Price for Infants: RM" + totalInfantPrice);
    }
}

class Pricing {

    private String tourType;
    private String season;
    private double adultPrice;
    private double childWithExtraBedPrice;
    private double childWithoutExtraBedPrice;
    private double infantPrice;

    public Pricing(String tourType, String season, double adultPrice,
            double childWithExtraBedPrice, double childWithoutExtraBedPrice,
            double infantPrice) {
        this.tourType = tourType;
        this.season = season;
        this.adultPrice = adultPrice;
        this.childWithExtraBedPrice = childWithExtraBedPrice;
        this.childWithoutExtraBedPrice = childWithoutExtraBedPrice;
        this.infantPrice = infantPrice;
    }

    public String getTourType() {
        return tourType;
    }

    public String getSeason() {
        return season;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildWithExtraBedPrice() {
        return childWithExtraBedPrice;
    }

    public double getChildWithoutExtraBedPrice() {
        return childWithoutExtraBedPrice;
    }

    public double getInfantPrice() {
        return infantPrice;
    }
}

class TravelTour {

    private String country;
    private String state;
    private boolean hasJoinedTour;
    private boolean hasPrivateTour;
    private int duration;
    private Pricing normalSeasonPricing;
    private Pricing peakSeasonPricing;

    public TravelTour(String country, String state, boolean hasJoinedTour, boolean hasPrivateTour, int duration,
            Pricing normalSeasonPricing, Pricing peakSeasonPricing) {
        this.country = country;
        this.state = state;
        this.hasJoinedTour = hasJoinedTour;
        this.hasPrivateTour = hasPrivateTour;
        this.duration = duration;
        this.normalSeasonPricing = normalSeasonPricing;
        this.peakSeasonPricing = peakSeasonPricing;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public boolean isHasJoinedTour() {
        return hasJoinedTour;
    }

    public boolean isHasPrivateTour() {
        return hasPrivateTour;
    }

    public int getDuration() {
        return duration;
    }

    public Pricing getNormalSeasonPricing() {
        return normalSeasonPricing;
    }

    public Pricing getPeakSeasonPricing() {
        return peakSeasonPricing;
    }
}

class OverseasTour extends TravelTour {

    private String tourType;

    public OverseasTour(String country, String state, boolean hasJoinedTour, boolean hasPrivateTour,
            int duration, String tourType, Pricing normalSeasonPricing, Pricing peakSeasonPricing) {
        super(country, state, hasJoinedTour, hasPrivateTour, duration, normalSeasonPricing, peakSeasonPricing);
        this.tourType = tourType;
    }

    public String getTourType() {
        return tourType;
    }

    public double calculatePrice(String season, String ageGroup) {
        Pricing pricing = season.equalsIgnoreCase("Peak") ? getPeakSeasonPricing() : getNormalSeasonPricing();

        switch (ageGroup) {
            case "Adult":
                return pricing.getAdultPrice();
            case "Child With Extra Bed":
                return pricing.getChildWithExtraBedPrice();
            case "Child With No Extra Bed":
                return pricing.getChildWithoutExtraBedPrice();
            case "Infant":
                return pricing.getInfantPrice();
            default:
                System.out.println("Invalid age group!");
                return 0.0;
        }
    }
}

class DomesticTour extends TravelTour {

    private String tourType;

    public DomesticTour(String country, String state, boolean hasJoinedTour, boolean hasPrivateTour,
            int duration, String tourType, Pricing normalSeasonPricing, Pricing peakSeasonPricing) {
        super(country, state, hasJoinedTour, hasPrivateTour, duration, normalSeasonPricing, peakSeasonPricing);
        this.tourType = tourType;
    }

    public String getTourType() {
        return tourType;
    }

    public double calculatePrice(String season, String ageGroup) {
        Pricing pricing = season.equalsIgnoreCase("Peak") ? getPeakSeasonPricing() : getNormalSeasonPricing();

        switch (ageGroup) {
            case "Adult":
                return pricing.getAdultPrice();
            case "Child With Extra Bed":
                return pricing.getChildWithExtraBedPrice();
            case "Child With No Extra Bed":
                return pricing.getChildWithoutExtraBedPrice();
            case "Infant":
                return pricing.getInfantPrice();
            default:
                System.out.println("Invalid age group!");
                return 0.0;
        }
    }
}
