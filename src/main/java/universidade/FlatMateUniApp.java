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
            System.out.println("\n-- Menu Principal FlatMateUni --");
            System.out.println("1. Cadastrar proprietário");
            System.out.println("2. Cadastrar imóvel");
            System.out.println("3. Listar proprietários");
            System.out.println("4. Listar imóveis");
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
        System.out.println("\n-- Cadastro de Proprietário --");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Erro: O nome é obrigatório.");
            return;
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf.isEmpty()) {
            System.out.println("Erro: O CPF é obrigatório.");
            return;
        }

        System.out.print("Data de nascimento (dd/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Telefone: ");
        String phone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        System.out.println("Documentos do imóvel (separados por vírgula): ");
        String docsStr = scanner.nextLine();
        List<String> docs = List.of(docsStr.split(","));

        Owner owner = new Owner(name, cpf, birthDate, phone, email, password, docs);
        if (!owner.validateIdentity()) {
            System.out.println("Erro: É obrigatório fornecer documentos.");
            return;
        }

        owners.add(owner);
        System.out.println("Proprietário cadastrado!");
    }

    private void registerProperty() {
        System.out.println("\n-- Cadastro de Imóvel --");
        if (owners.isEmpty()) {
            System.out.println("Erro: Cadastre um proprietário antes.");
            return;
        }

        System.out.println("Selecione o proprietário pelo CPF:");
        listOwners();
        System.out.print("CPF do proprietário: ");
        String cpf = scanner.nextLine();
        
        Owner selectedOwner = null;
        for (Owner o : owners) {
            if (o.getCpf().equals(cpf)) {
                selectedOwner = o;
                break;
            }
        }
        if (selectedOwner == null) {
            System.out.println("Erro: Proprietário não encontrado.");
            return;
        }

        System.out.print("Código do imóvel (ex: PROP001): ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            System.out.println("Erro: O código é obrigatório.");
            return;
        }

        System.out.print("Endereço completo: ");
        String location = scanner.nextLine();

        System.out.print("Tipo de acomodação (ex: Quarto, Apartamento): ");
        String type = scanner.nextLine();

        System.out.print("Descrição do imóvel: ");
        String desc = scanner.nextLine();

        System.out.print("Valor da diária base: ");
        double daily = Double.parseDouble(scanner.nextLine());

        System.out.print("Valor do condomínio: ");
        double cond = Double.parseDouble(scanner.nextLine());

        System.out.print("Valor do IPTU: ");
        double iptu = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade de quartos: ");
        int bedrooms = Integer.parseInt(scanner.nextLine());

        System.out.print("Categoria (A-G): ");
        String catInput = scanner.nextLine().toUpperCase();
        if (catInput.isEmpty()) {
            System.out.println("Erro: Categoria inválida.");
            return;
        }
        char cat = catInput.charAt(0);
        if (!Property.FCATS.containsKey(cat)) {
            System.out.println("Erro: Categoria inválida.");
            return;
        }

        Property property = selectedOwner.createPropertyListing(id, location, new ArrayList<>(), 
            desc, type, daily, cond, iptu, cat, bedrooms);

        properties.add(property);
        System.out.println("Imóvel cadastrado com sucesso!");
    }

    private void listOwners() {
        if (owners.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado.");
            return;
        }
        owners.forEach(o -> System.out.println("- " + o.getFullName() + " (CPF: " + o.getCpf() + ")"));
    }

    private void listProperties() {
        if (properties.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        properties.forEach(p -> System.out.println("- Código: " + p.getId() + " | Endereço: " + p.getDetailedLocation() + " | Proprietário: " + p.getOwner().getFullName()));
    }
}
