package universidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    private List<String> propertyDocuments;

    public Owner(String fullName, String cpf, LocalDate birthDate, String phoneNumber, String email,
            String password, List<String> propertyDocuments) {
        super(fullName, cpf, birthDate, phoneNumber, email, password);
        this.propertyDocuments = new ArrayList<>(propertyDocuments);
    }

    public User getUser() {
        return this;
    }

    @Override
    public boolean validateIdentity() {
        return propertyDocuments != null && !propertyDocuments.isEmpty();
    }

    public Property createPropertyListing(String id, String detailedLocation, List<String> photos,
            String description, String accommodationType, double maxDailyCharge,
            double condominiumFee, double iptuFee, char category, int amountOfBedrooms) {
        return new Property(id, this, detailedLocation, photos, description, accommodationType,
                maxDailyCharge, condominiumFee, iptuFee, category, amountOfBedrooms);
    }

    public List<String> getPropertyDocuments() {
        return propertyDocuments;
    }

    public void setPropertyDocuments(List<String> propertyDocuments) {
        this.propertyDocuments = propertyDocuments;
    }
}
