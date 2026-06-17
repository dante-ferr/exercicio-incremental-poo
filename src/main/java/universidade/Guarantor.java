package universidade;

public class Guarantor extends User {
    private Student linkedStudent;
    private String proofOfResidence;

    public Guarantor(String fullName, String cpf, java.time.LocalDate birthDate, String phoneNumber,
            String email, String password, String proofOfResidence) {
        super(fullName, cpf, birthDate, phoneNumber, email, password);
        this.proofOfResidence = proofOfResidence;
    }

    public void registerAndLinkStudent(Student student) {
        this.linkedStudent = student;
    }

    public void signContract(Contract contract) {
        contract.registerSignature(this);
    }

    public User getUser() {
        return this;
    }

    @Override
    public boolean validateIdentity() {
        return proofOfResidence != null && !proofOfResidence.isEmpty();
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
