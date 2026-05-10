package universidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Property {
    private String id;
    private Owner owner;
    private String detailedLocation;
    private List<String> photos;
    private String fullDescription;
    private String accommodationType;
    private List<PropertyInterest> interests;
    private List<Review> reviews;

    // Pricing attributes
    private double maxDailyCharge;
    private double condominiumFee;
    private double iptuFee;
    private char category;
    private int amountOfBedrooms;

    private PropertyStatus status;

    public static final Map<Character, Double> FCATS =
            Map.of('A', 0.2, 'B', 0.4, 'C', 0.5, 'D', 0.7, 'E', 0.8, 'F', 0.9, 'G', 1.0);

    public static final Map<Integer, Double> FQTOS = Map.of(1, 1.0, 2, 1.5, 3, 2.0, 4, 2.5, 5, 3.0);

    public Property(String id, Owner owner, String detailedLocation, List<String> photos,
            String fullDescription, String accommodationType, double maxDailyCharge,
            double condominiumFee, double iptuFee, char category, int amountOfBedrooms) {
        this.id = id;
        this.owner = owner;
        this.detailedLocation = detailedLocation;
        this.photos = new ArrayList<>(photos != null ? photos : new ArrayList<>());
        this.fullDescription = fullDescription;
        this.accommodationType = accommodationType;
        this.interests = new ArrayList<>();
        this.reviews = new ArrayList<>();
        
        this.maxDailyCharge = maxDailyCharge;
        this.condominiumFee = condominiumFee;
        this.iptuFee = iptuFee;
        this.category = category;
        this.amountOfBedrooms = amountOfBedrooms;
        this.status = PropertyStatus.AVAILABLE;
    }

    public void addPhoto(String photoUrl) {
        this.photos.add(photoUrl);
    }

    public void addInterest(PropertyInterest interest) {
        if (!this.interests.contains(interest)) {
            this.interests.add(interest);
        }
    }

    public double calculateAverageRating() {
        if (reviews.isEmpty())
            return 0.0;
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

    // Getters and Setters
    public String getId() { return id; }
    public Owner getOwner() { return owner; }
    public String getDetailedLocation() { return detailedLocation; }
    public String getAccommodationType() { return accommodationType; }
    public List<PropertyInterest> getInterests() { return interests; }
    public List<Review> getReviews() { return reviews; }
    public double getMaxDailyCharge() { return maxDailyCharge; }
    public double getCondominiumFee() { return condominiumFee; }
    public double getIptuFee() { return iptuFee; }
    public char getCategory() { return category; }
    public int getAmountOfBedrooms() { return amountOfBedrooms; }
    public PropertyStatus getStatus() { return status; }
    public void setStatus(PropertyStatus status) { this.status = status; }

    public double getFcat() {
        return FCATS.getOrDefault(category, 1.0);
    }

    public double getFqto() {
        if (amountOfBedrooms > 5) {
            return FQTOS.get(5);
        }
        return FQTOS.getOrDefault(amountOfBedrooms, 1.0);
    }
}
