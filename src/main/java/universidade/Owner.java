package universidade;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private User user;
    private List<String> propertyDocuments;

    public Owner(User user, List<String> propertyDocuments) {
        this.user = user;
        this.propertyDocuments = new ArrayList<>(propertyDocuments);
    }

    public void sendMessage(Chat chat, String messageContent) {
        user.sendMessage(chat, messageContent);
    }

    public User getUser() {
        return user;
    }

    public boolean validateIdentity() {
        return propertyDocuments != null && !propertyDocuments.isEmpty();
    }

    public Property createPropertyListing(String detailedLocation, List<String> photos,
            String description, String accommodationType, double maxDailyCharge,
            double condominiumFee, double iptuFee, char category, int amountOfBedrooms) {
        return new Property(this, detailedLocation, photos, description, accommodationType,
                maxDailyCharge, condominiumFee, iptuFee, category, amountOfBedrooms);
    }

    public List<String> getPropertyDocuments() {
        return propertyDocuments;
    }

    public void setPropertyDocuments(List<String> propertyDocuments) {
        this.propertyDocuments = propertyDocuments;
    }
}
