package universidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlatMateUniApp {
    private List<Owner> owners;
    private List<Property> properties;
    private Scanner scanner;

    public FlatMateUniApp() {
        this.owners = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== FlatMateUni.com.br - Menu Principal ===");
            System.out.println("1. Cadastrar Proprietário");
            System.out.println("2. Cadastrar Imóvel");
            System.out.println("3. Listar Proprietários");
            System.out.println("4. Listar Imóveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    registerOwner();
                    break;
                case "2":
                    registerProperty();
                    break;
                case "3":
                    listOwners();
                    break;
                case "4":
                    listProperties();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void registerOwner() {
        System.out.println("\n--- Cadastro de Proprietário ---");
        try {
            System.out.print("Nome Completo: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            if (cpf.isEmpty()) throw new IllegalArgumentException("CPF é obrigatório.");

            System.out.print("Data de Nascimento (dd/mm/aaaa): ");
            String dateStr = scanner.nextLine();
            LocalDate birthDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.print("Telefone: ");
            String phone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            User user = new User(name, cpf, birthDate, phone, email, password);
            
            System.out.println("Documentos do imóvel (separe por vírgula): ");
            String docsStr = scanner.nextLine();
            List<String> docs = List.of(docsStr.split(","));

            Owner owner = new Owner(user, docs);
            if (!owner.validateIdentity()) {
                throw new IllegalArgumentException("Proprietário deve fornecer documentos.");
            }

            owners.add(owner);
            System.out.println("Proprietário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar proprietário: " + e.getMessage());
        }
    }

    private void registerProperty() {
        System.out.println("\n--- Cadastro de Imóvel ---");
        if (owners.isEmpty()) {
            System.out.println("Erro: Nenhum proprietário cadastrado. Cadastre um proprietário primeiro.");
            return;
        }

        try {
            System.out.println("Selecione o proprietário pelo CPF:");
            listOwners();
            System.out.print("CPF do Proprietário: ");
            String cpf = scanner.nextLine();
            
            Owner selectedOwner = owners.stream()
                .filter(o -> o.getUser().getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado."));

            System.out.print("ID do Imóvel (ex: PROP001): ");
            String id = scanner.nextLine();
            if (id.isEmpty()) throw new IllegalArgumentException("ID é obrigatório.");

            System.out.print("Localização Detalhada: ");
            String location = scanner.nextLine();

            System.out.print("Tipo de Acomodação (ex: Apartamento, Quarto): ");
            String type = scanner.nextLine();

            System.out.print("Descrição Completa: ");
            String desc = scanner.nextLine();

            System.out.print("Valor Máximo Diária: ");
            double daily = Double.parseDouble(scanner.nextLine());

            System.out.print("Valor Condomínio: ");
            double cond = Double.parseDouble(scanner.nextLine());

            System.out.print("Valor IPTU: ");
            double iptu = Double.parseDouble(scanner.nextLine());

            System.out.print("Categoria (A-G): ");
            char cat = scanner.nextLine().toUpperCase().charAt(0);
            if (!Property.FCATS.containsKey(cat)) throw new IllegalArgumentException("Categoria inválida.");

            System.out.print("Quantidade de Quartos: ");
            int bedrooms = Integer.parseInt(scanner.nextLine());

            Property property = selectedOwner.createPropertyListing(id, location, new ArrayList<>(), 
                desc, type, daily, cond, iptu, cat, bedrooms);

            properties.add(property);
            System.out.println("Imóvel cadastrado com sucesso e vinculado a " + selectedOwner.getUser().getFullName());

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar imóvel: " + e.getMessage());
        }
    }

    private void listOwners() {
        if (owners.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado.");
            return;
        }
        owners.forEach(o -> System.out.println("- " + o.getUser().getFullName() + " (CPF: " + o.getUser().getCpf() + ")"));
    }

    private void listProperties() {
        if (properties.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        properties.forEach(p -> System.out.println("- ID: " + p.getId() + " | Local: " + p.getDetailedLocation() + " | Proprietário: " + p.getOwner().getUser().getFullName()));
    }
}
