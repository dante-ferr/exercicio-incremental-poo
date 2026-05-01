package universidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contract {
    private Property property;
    private Owner owner;
    private List<Student> students;
    private List<Guarantor> guarantors;
    private String digitalDocument;
    private String status;
    private Set<User> signedBy;

    public Contract(Property property, Owner owner, List<Student> students,
            List<Guarantor> guarantors) {
        if (property.getStatus() != PropertyStatus.AVAILABLE) {
            throw new IllegalStateException("O imóvel não está disponível para locação.");
        }
        this.property = property;
        this.owner = owner;
        this.students = new ArrayList<>(students);
        this.guarantors = new ArrayList<>(guarantors);
        this.status = "Pendente de Assinaturas";
        this.signedBy = new HashSet<>();
    }

    public void generateBaseDocument() {
        this.digitalDocument = "Contrato de Locação - Imóvel: " + property.getDetailedLocation();
        System.out.println("Documento base gerado para o imóvel: " + property.getDetailedLocation());
    }

    public void registerSignature(User user) {
        if (!isValidParty(user)) {
            throw new IllegalArgumentException("Usuário não faz parte deste contrato.");
        }
        signedBy.add(user);
        System.out.println("Assinatura registrada: " + user.getFullName());
        checkAndActivateStatus();
    }

    private boolean isValidParty(User user) {
        if (user.equals(owner.getUser())) return true;
        for (Student s : students) {
            if (s.getUser().equals(user)) return true;
        }
        for (Guarantor g : guarantors) {
            if (g.getUser().equals(user)) return true;
        }
        return false;
    }

    private void checkAndActivateStatus() {
        int requiredSignatures = 1 + students.size() + guarantors.size();
        if (signedBy.size() >= requiredSignatures) {
            this.status = "Vigente";
            this.property.setStatus(PropertyStatus.RENTED);
            for (Student student : students) {
                student.addContract(this);
            }
            System.out.println("Contrato agora está VIGENTE. Status do imóvel alterado para ALUGADO.");
        }
    }

    public void terminateContract() {
        this.status = "Encerrado";
        this.property.setStatus(PropertyStatus.AVAILABLE);
        System.out.println("Contrato ENCERRADO. Status do imóvel alterado para DISPONÍVEL.");
    }

    public String getStatus() { return status; }
    public Property getProperty() { return property; }
    public Owner getOwner() { return owner; }
    public List<Student> getStudents() { return students; }
    public List<Guarantor> getGuarantors() { return guarantors; }
}
