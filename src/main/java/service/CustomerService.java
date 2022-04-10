package service;

import model.Account;
import model.Customer;
import repository.CustomerType;
import repository.Repository;

import java.time.LocalDate;
import java.util.Random;
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
        String name = scanner.next();
        customer.setFirstName(name);
        System.out.println("Iveskite savo pavarde: ");
        String lastName = scanner.next();
        customer.setLastName(lastName);
        System.out.println("Iveskite teisinga savo el. pasta: ");
        String email = scanner.next();
        customer.setEmail(email);
        while (customer.isNotEmailAddress(email)) {
            System.out.println("Neteisingai ivestas el. pastas, prasome ivesti teisingai el. pasta");
            email = scanner.next();
        }
        System.out.println("Iveskite savo tel numeri: ");
        String phone = scanner.next();
        customer.setPhone(phone);
        while (customer.isNotPhoneNumber(phone)) {
            System.out.println("Neteisingai ivestas telefono numeris, prasome ivesti teisinga telefono numeri");
            phone = scanner.next();
        }
        customer.setAccount(Account.builder()
                .customerType(choiceCustomerType())
                .singUpDate(LocalDate.now())
                .pinCode(pinGenerator(4))
                .build());
        System.out.println();
        System.out.println("Aciu Jusu registracija sekmingai ivykdyta!");
        repository.saveCustomer(customer);
        System.out.println("Jusu ID yra " + customer.getCustomerID());
        System.out.println("Jusu pin kodas yra " + customer.getAccount().getPinCode());
        return customer;
    }

    public int pinGenerator(int passwordLength) {
        Random random = new Random();
        String[] myArray2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {

            randomNum.append(myArray2[random.nextInt(9)]);
        }
        return Integer.parseInt(randomNum.toString());
    }

    public CustomerType choiceCustomerType() {
        System.out.println("Pasirinkite kliento tipa: 1 Pirkejas / 2 Pardavejas");
        String userInput = scanner.next();
        if (userInput.equalsIgnoreCase("1")) {
            customer.setCustomerType(CustomerType.BUYER);
            System.out.println("Sveikiname, pasirinkote buti pirkejas!");
        } else if (userInput.equalsIgnoreCase("2")) {
            customer.setCustomerType(CustomerType.SELLER);
            System.out.println("Sveikiname pasirinkote buti pardavejas!");
        } else {
            System.out.println("Neteisingas pasirinkimas bandykite pasirinkti dar karta  ...");
            choiceCustomerType();
        }
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

        boolean isLogin = customerLogin(customer);

        if (isLogin) {
            char selection = 'X';
            Scanner scanner = new Scanner(System.in);

            do {
                selection = printingService.printLoginMenu(scanner);
                switch (selection) {
                    case '1' -> productService.insertProduct();
                    case '2' -> productService.showProductsByType();
                    case '3' -> productService.createOrder();
                    case '4' -> productService.showOrder();
                    case 'E' -> System.out.println("Aciu viso gero!");
                }
            } while (selection != 'E');
        }
    }

    public boolean customerLogin(Customer customer) {
        System.out.println("Iveskite savo Id: ");
        String customerId = scanner.next();
        Customer byId = repository.findCustomerById(Integer.valueOf(String.valueOf(customerId)));
        if (byId == null) {
            System.out.println("Nerastas toks ID bandykite dar karta...");
            customerLogin(customer);
        }
        assert byId != null;
        String firstName = byId.getFirstName();
        System.out.println("Sveiki atvyke " + firstName);
        System.out.println("Iveskite savo pin koda: ");

        String pinCode = scanner.next();
        Integer foundAccountByPin = byId.getAccount().getPinCode();

        if (!pinCode.equalsIgnoreCase(String.valueOf(foundAccountByPin))) {
            System.out.println("Neteisingas pin kodas, bandykite dar karta prisijungti...");
            customerLogin(customer);
        }
        return pinCode.equalsIgnoreCase(String.valueOf(foundAccountByPin));
    }
}



