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
        List<PropertyInterest> result = new ArrayList<>();
        for (PropertyInterest i : allInterests) {
            if (i.getStudent().getCpf().equals(cpf)) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<PropertyInterest> findByProperty(String propertyIdOrAddress) {
        List<PropertyInterest> result = new ArrayList<>();
        for (PropertyInterest i : allInterests) {
            if (i.getProperty().getId().equals(propertyIdOrAddress) || 
                i.getProperty().getDetailedLocation().equals(propertyIdOrAddress)) {
                result.add(i);
            }
        }
        return result;
    }

    public String getDisplayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Interesse registrado em: ").append(interestDate).append("\n");
        sb.append("Status do interesse: ").append(status).append("\n");
        
        sb.append("\nInformações do Estudante:\n");
        sb.append("- Nome: ").append(student.getFullName()).append("\n");
        sb.append("- CPF: ").append(student.getCpf()).append("\n");
        sb.append("- Telefone: ").append(student.getPhoneNumber()).append("\n");
        sb.append("- Idade: ").append(student.getAge()).append("\n");
        sb.append("- Universidade: ").append(student.getUniversity()).append("\n");
        sb.append("- Descrição pessoal: ").append(student.getPersonalDescription()).append("\n");

        sb.append("\nDetalhes do Imóvel:\n");
        sb.append("- Código: ").append(property.getId()).append("\n");
        sb.append("- Endereço: ").append(property.getDetailedLocation()).append("\n");
        sb.append("- Tipo: ").append(property.getAccommodationType()).append("\n");
        
        return sb.toString();
    }
}
