import service.CustomerService;
import service.SystemAdminService;

public class Main {

    public static void main(String[] args) {

        SystemAdminService service = new SystemAdminService();
        service.createAdmin();
        CustomerService customerService = new CustomerService();
        customerService.showMenu();
    }
}
