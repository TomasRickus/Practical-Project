package service;

import model.Account;
import model.Customer;
import repository.CustomerType;
import repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {

    Customer customer = new Customer();
    Scanner scanner = new Scanner(System.in);
    ProductService productService = new ProductService();
    Repository repository = new Repository();

    private static int nextAccountNumber = 1;

    public int getNewAccountNumber() {
        int newNumber = nextAccountNumber;
        nextAccountNumber++;
        return newNumber;

    }

    public Customer registrationForm() {
        System.out.println();
        System.out.println("Prasome suvesti savo duomenis! ");
        System.out.println("Iveskite savo varda: ");
        customer.setFirstName(scanner.next());
        System.out.println("Iveskite savo pavarde: ");
        customer.setLastName(scanner.next());
        System.out.println("Iveskite savo el. pasta: ");
        String email = scanner.nextLine();
        customer.setEmail(email);
        while (customer.isNotEmailAddress(email)) {
            System.out.println("Neteisingas el. pastas, prasome ivesti teisingai");
            email = scanner.nextLine();
        }
        System.out.println("Iveskite savo tel numeri: ");
        String phone = scanner.nextLine();
        customer.setPhone(phone);
        while (customer.isNotPhoneNumber(phone)) {
            System.out.println("Neteisingai ivestas telefono numeris, prasome ivesti teisinga telefono numeri");
            phone = scanner.nextLine();
        }
        System.out.println("Pasirinkite busite PRIKEJAS AR PARDAVEJAS?");
        customer.setCustomerType(choiceCustomerType());
        customer.setAccount(Account.builder()
                .accountNumber(getNewAccountNumber())
                .singUpDate(LocalDate.now())
                .build());
        System.out.println();
        System.out.println("Aciu Jusu registracija sekmingai ivykdyta!");
        System.out.println("Jusu paskyros numeris yra " + customer.getAccount().getAccountNumber());
        repository.save(customer);

        return customer;
    }

    public CustomerType choiceCustomerType() {

        System.out.println("Pasirinkite kliento tipa: 1 Pirkejas / 2 Pardavejas");
        int userInput = scanner.nextInt();
            if (userInput == 1) {
                customer.setCustomerType(CustomerType.BUYER);
                System.out.println("Sveikiname, pasirinkote buti pirkejas!");
            }
            if (userInput == 2) {
                customer.setCustomerType(CustomerType.SELLER);
                System.out.println("Sveikiname pasirinkote buti pardavejas!");
            } else

                System.out.println("Neteisingas pasirinkimas bandykite dar karta!");

        return customer.getCustomerType();
    }

    public void showMenu() {
        char selection = 'X';

        do {
            selection = printMenuSelections(scanner);

            switch (selection) {
                case '1' -> registrationForm();
                case '2' -> productService.insertProduct();
                case 'E' -> System.out.println("Aciu viso gero!");
            }

        } while (selection != 'E');
    }

    private char printMenuSelections(Scanner scanner) {
        char selection;
        System.out.println();
        System.out.println("Sveiki atvyke! :)");
        System.out.println("Pasirinkite ka norite atlikti: ");
        System.out.println("1. Uzsiregistruoti ");
        System.out.println("2. Ideti preke pardavimui ");
        System.out.println("E. Iseiti/exit");

        System.out.println("____________________");
        selection = scanner.next().charAt(0);
        System.out.println();
        return selection;
    }
}

