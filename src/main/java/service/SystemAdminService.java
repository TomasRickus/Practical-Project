package service;

import model.Account;
import model.Customer;
import repository.Repository;

import java.time.LocalDate;
import java.util.Scanner;

public class SystemAdminService {

    PrintingService printingService = new PrintingService();
    ProductService productService = new ProductService();
    Customer customer = new Customer();
    Repository repository = new Repository();
    Scanner scanner = new Scanner(System.in);


    public void createAdmin() {

        Repository repository = new Repository();

        Customer admin = Customer.builder()
                .firstName("Tomas")
                .lastName("Admin")
                .email("tomas@admin.lt")
                .phone("+37061234567")
                .account(Account.builder()
                        .singUpDate(LocalDate.now())
                        .pinCode(1111)
                        .build())
                .build();

        repository.saveCustomer(admin);
    }

    public void loginSystemAdmin(Customer customer) {

        boolean isLogin = adminLogin(customer);

        if (isLogin) {

            char selection = 'X';
            Scanner scanner = new Scanner(System.in);

            do {
                selection = printingService.printAdminMenu(scanner);
                switch (selection) {
                    case '1' -> productService.searchProducts();
                    case '2' -> productService.createOrder();
                    case '3' -> productService.insertProduct();
                    case '4' -> productService.showOrder();
                    case '5' -> productService.deleteProduct();
                    case '6' -> productService.updateProduct();
                    case 'E' -> System.out.println("Aciu viso gero!");
                }
            } while (selection != 'E');
        }

    }

    private boolean adminLogin(Customer customer) {
        Customer byId = repository.findCustomerById(1);
        System.out.println("Iveskite savo pin koda: ");
        int pinCode = scanner.nextInt();
        return pinCode == byId.getAccount().getPinCode();
    }
}
