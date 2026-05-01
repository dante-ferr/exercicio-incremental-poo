package universidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testando FlatMateUni ---");

        // 1. Setup Atores
        List<String> docs = new ArrayList<>();
        docs.add("doc_propriedade_1.pdf");
        User ownerUser = new User("João Silva", "123.456.789-00", LocalDate.of(1980, 5, 15), 
                "71988887777", "joao@email.com", "senha123");
        Owner owner = new Owner(ownerUser, docs);

        User studentUser = new User("Maria Santos", "987.654.321-11", LocalDate.of(2002, 10, 20), 
                "71999998888", "maria@email.com", "senha456");
        Student student = new Student(studentUser, "MAT123", "comprovante.pdf", 
                "Universidade Federal", 23, "Estudante focada e organizada");

        User guarantorUser = new User("Roberto Santos", "111.222.333-44", LocalDate.of(1975, 2, 10),
                "71977776666", "roberto@email.com", "senha789");
        Guarantor guarantor = new Guarantor(guarantorUser, "comprovante_residencia.pdf");
        guarantor.registerAndLinkStudent(student);

        // 2. Setup Imóvel (Incremental 2 - Precificação)
        List<String> photos = new ArrayList<>();
        photos.add("foto1.jpg");
        // Exemplo: Diária 100, Condomínio 860, IPTU 221, Categoria 'F', 2 quartos
        Property property = owner.createPropertyListing("Rua Principal, 123", photos,
                "Apartamento aconchegante perto do campus", "Apartamento", 
                100.0, 860.0, 221.0, 'F', 2);

        System.out.println("Status inicial do imóvel: " + property.getStatus());

        // 3. Simulação de Interesse e Chat
        student.expressInterest(property);
        Chat chat = student.openChat(owner, property);
        student.sendMessage(chat, "Olá, o imóvel está disponível?");
        owner.sendMessage(chat, "Sim, está disponível!");

        // 4. Locação e Contrato (Incremental 3)
        List<Student> contractStudents = new ArrayList<>();
        contractStudents.add(student);
        List<Guarantor> contractGuarantors = new ArrayList<>();
        contractGuarantors.add(guarantor);

        Rent rent = new Rent(property, 360, 2); // 360 dias (Anual), 2 moradores
        RentStatement statement = new RentStatement("Endereço Maria", "Endereço João", rent);
        
        System.out.println("\n--- Extrato de Aluguel (Incremental 2) ---");
        System.out.println(statement.getSummary());

        System.out.println("\n--- Formalização do Contrato (Incremental 3) ---");
        Contract contract = new Contract(property, owner, contractStudents, contractGuarantors);
        contract.generateBaseDocument();

        System.out.println("Status do contrato: " + contract.getStatus());

        // Assinaturas
        contract.registerSignature(owner.getUser());
        contract.registerSignature(student.getUser());
        contract.registerSignature(guarantor.getUser());

        System.out.println("Status do contrato após assinaturas: " + contract.getStatus());
        System.out.println("Status do imóvel após contrato vigente: " + property.getStatus());

        // Testando restrição de locação (não pode locar imóvel ALUGADO)
        try {
            new Contract(property, owner, contractStudents, contractGuarantors);
        } catch (IllegalStateException e) {
            System.out.println("Sucesso: " + e.getMessage());
        }

        // Finalização
        contract.terminateContract();
        System.out.println("Status do imóvel após término: " + property.getStatus());
        
        System.out.println("\nQuantidade de contratos da Maria: " + student.getContracts().size());
    }
}
