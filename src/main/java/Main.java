import model.Account;
import model.Customer;
import repository.CustomerType;
import repository.Repository;

import java.time.LocalDate;
import service.CustomerService;

public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();




//        Customer tomas = Customer.builder()
//                .firstName("Admin")
//                .lastName("Admin")
//                .email("admin@gmail.com")
//                .customerType(CustomerType.SELLER)
//                .phone("861234567")
//                .build();
//        repository.save(tomas);

        CustomerService customerService = new CustomerService();
        customerService.showMenu();

        Customer admin = repository.findById(2);
        System.out.println("Rasta o gal ne " + admin.getFirstName());

//        Account byPassword = repository.findByIdAcc(2);
//        System.out.println("Ar ras pgal passworda?? " + byPassword.getCustomer());

//        Account account = Account.builder()
//                .customer(tomas)
//                .build();



//
//        Account byPassword = repository.findById(1);
//        System.out.println("rastas klientas " + byPassword.getCustomer());





    }
}
