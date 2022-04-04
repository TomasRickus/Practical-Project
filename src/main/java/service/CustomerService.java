package service;

import model.Account;
import model.Customer;
import repository.CustomerType;
import repository.Repository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomerService {

    Customer customer = new Customer();
    Scanner scanner = new Scanner(System.in);
    ProductService productService = new ProductService();
    Repository repository = new Repository();
    PrintingService printingService = new PrintingService();

    public Customer registrationForm() {
        System.out.println();
        System.out.println("Prasome suvesti savo duomenis! ");
        System.out.println("***************************");
        System.out.println("Iveskite savo varda: ");
        customer.setFirstName(scanner.next());
        System.out.println("Iveskite savo pavarde: ");
        customer.setLastName(scanner.next());
        System.out.println("Iveskite savo el. pasta: ");
        String email = scanner.nextLine();
        customer.setEmail(email);
        while (customer.isNotEmailAddress(email)) {
            email = scanner.nextLine();
        }
        System.out.println("Iveskite savo tel numeri: ");
        String phone = scanner.nextLine();
        customer.setPhone(phone);
        while (customer.isNotPhoneNumber(phone)) {
            System.out.println("Neteisingai ivestas telefono numeris, prasome ivesti teisinga telefono numeri");
            phone = scanner.nextLine();
        }
        customer.setAccount(Account.builder()
                .customerType(choiceCustomerType())
                .singUpDate(LocalDate.now())
                .password(pinGenerator(10))
                .build());
        System.out.println();
        System.out.println("Aciu Jusu registracija sekmingai ivykdyta!");
        repository.save(customer);
        System.out.println("Jusu slaptazodis yra " + customer.getAccount().getPassword());

        return customer;
    }

    public int pinGenerator(int passwordLength) {
        SecureRandom random = new SecureRandom();
        int[] myArray2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int randomNum = 0;
        for (int i = 0; i < passwordLength; i++) {
            randomNum = random.nextInt(myArray2.length);
        }
        return randomNum;
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
            selection = printingService.printMenuSelections(scanner);

            Customer customer = new Customer();
            switch (selection) {
                case '1' -> registrationForm();
                case '2' -> login(customer);
                case 'E' -> System.out.println("Aciu viso gero!");
            }

        } while (selection != 'E');
    }


    public void login(Customer customer) {
        boolean isLogin = enterPassword(customer);

        if (isLogin) {
            char selection = 'X';
            Scanner scanner = new Scanner(System.in);

            do {
                selection = printingService.printLoginMenu(scanner);
                switch (selection) {
                    case '1' -> productService.insertProduct();
                    case '2' -> productService.findProductByName();
                    case 'E' -> System.out.println("Aciu viso gero!");
                }
            } while (selection != 'E');
        }
    }

    public boolean enterPassword(Customer customerId) {
        System.out.println("Iveskite savo Id: ");
        Scanner scanner = new Scanner(System.in);
        Customer byId = repository.findById(scanner.nextInt());
        String firstName = byId.getFirstName();
        System.out.println("Sveiki prisijunge " + firstName);
        System.out.println("Iveskite savo pin koda: ");
        Scanner scanner1 = new Scanner(System.in);
        int pinCode = scanner1.nextInt();
        return pinCode == byId.getAccount().getPassword();
    }


}

