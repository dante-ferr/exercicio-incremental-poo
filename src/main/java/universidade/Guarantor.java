package universidade;

public class Guarantor {
    private User user;
    private Student linkedStudent;
    private String proofOfResidence;

    public Guarantor(User user, String proofOfResidence) {
        this.user = user;
        this.proofOfResidence = proofOfResidence;
    }

    public void registerAndLinkStudent(Student student) {
        this.linkedStudent = student;
    }

    public void signContract(Contract contract) {
        contract.registerSignature(this.user);
    }

    public User getUser() {
        return user;
    }

    public Student getLinkedStudent() {
        return linkedStudent;
    }

    public void setLinkedStudent(Student linkedStudent) {
        this.linkedStudent = linkedStudent;
    }

    public String getProofOfResidence() {
        return proofOfResidence;
    }

    public void setProofOfResidence(String proofOfResidence) {
        this.proofOfResidence = proofOfResidence;
    }
}
