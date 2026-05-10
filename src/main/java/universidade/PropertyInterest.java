package universidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyInterest {
    private Student student;
    private Property property;
    private LocalDate interestDate;
    private InterestStatus status;

    private static List<PropertyInterest> allInterests = new ArrayList<>();

    public PropertyInterest(Student student, Property property, LocalDate interestDate) {
        this.student = student;
        this.property = property;
        this.interestDate = interestDate;
        this.status = InterestStatus.INTERESTED;
        allInterests.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public Property getProperty() {
        return property;
    }

    public LocalDate getInterestDate() {
        return interestDate;
    }

    public InterestStatus getStatus() {
        return status;
    }

    public void setStatus(InterestStatus status) {
        this.status = status;
    }

    public static List<PropertyInterest> findByStudentCpf(String cpf) {
        return allInterests.stream()
                .filter(i -> i.getStudent().getUser().getCpf().equals(cpf))
                .collect(Collectors.toList());
    }

    public static List<PropertyInterest> findByProperty(String propertyIdOrAddress) {
        return allInterests.stream()
                .filter(i -> i.getProperty().getId().equals(propertyIdOrAddress) || 
                             i.getProperty().getDetailedLocation().equals(propertyIdOrAddress))
                .collect(Collectors.toList());
    }

    public String getDisplayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Dados do Interesse ---\n");
        sb.append("Data: ").append(interestDate).append("\n");
        sb.append("Status: ").append(status).append("\n");
        
        sb.append("\n--- Dados do Estudante ---\n");
        sb.append("Nome: ").append(student.getUser().getFullName()).append("\n");
        sb.append("CPF: ").append(student.getUser().getCpf()).append("\n");
        sb.append("Telefone: ").append(student.getUser().getPhoneNumber()).append("\n");
        sb.append("Idade: ").append(student.getAge()).append("\n");
        sb.append("Universidade: ").append(student.getUniversity()).append("\n");
        sb.append("Descrição: ").append(student.getPersonalDescription()).append("\n");

        sb.append("\n--- Dados do Imóvel ---\n");
        sb.append("ID: ").append(property.getId()).append("\n");
        sb.append("Endereço: ").append(property.getDetailedLocation()).append("\n");
        sb.append("Tipo: ").append(property.getAccommodationType()).append("\n");
        
        return sb.toString();
    }
}
