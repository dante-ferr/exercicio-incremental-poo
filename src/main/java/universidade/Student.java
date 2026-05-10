package universidade;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private User user;
    private String enrollmentNumber;
    private String enrollmentProof;
    private String university;
    private int age;
    private String personalDescription;
    private List<Contract> contracts;
    private List<PropertyInterest> interests;

    public Student(User user, String enrollmentNumber, String enrollmentProof, String university,
            int age, String personalDescription) {
        this.user = user;
        this.enrollmentNumber = enrollmentNumber;
        this.enrollmentProof = enrollmentProof;
        this.university = university;
        this.age = age;
        this.personalDescription = personalDescription;
        this.contracts = new ArrayList<>();
        this.interests = new ArrayList<>();
    }

    public void sendMessage(Chat chat, String messageContent) {
        user.sendMessage(chat, messageContent);
    }

    public User getUser() {
        return user;
    }

    public boolean validateAccountCreation() {
        return enrollmentNumber != null && !enrollmentNumber.isEmpty() && enrollmentProof != null
                && !enrollmentProof.isEmpty();
    }

    public List<Property> searchProperties(String location, String accommodationType,
            double minRating) {
        // Mock search logic
        return new ArrayList<>();
    }

    public void expressInterest(Property property) {
        PropertyInterest interest = new PropertyInterest(this, property, java.time.LocalDate.now());
        this.interests.add(interest);
        property.addInterest(interest);
    }

    public List<PropertyInterest> getInterests() {
        return interests;
    }

    public Chat openChat(Owner owner, Property property) {
        return new Chat(this, owner, property);
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getEnrollmentProof() {
        return enrollmentProof;
    }

    public void setEnrollmentProof(String enrollmentProof) {
        this.enrollmentProof = enrollmentProof;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }
}
