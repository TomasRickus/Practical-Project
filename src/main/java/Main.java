import com.itextpdf.text.DocumentException;
import model.Product;
import repository.ProductType;
import repository.Repository;
import service.CustomerService;
import service.ProductService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {

        Repository repository = new Repository();
//
//        List<Product> products = repository.findProduct();
//        products.stream().filter(product -> product.getProductType().equals(ProductType.CLOTHING))
//                .forEach(Product::getProductName);


        CustomerService customerService = new CustomerService();
        customerService.showMenu();

//        ProductService productService = new ProductService();
//        productService.showProductByName();
//        ToPdf pdf = new ToPdf();
//        pdf.orderToPdf();

    }
}
