package service;

import model.Product;
import repository.Repository;

import java.util.Scanner;

public class ProductService {

    Product product = new Product();
    Scanner scanner = new Scanner(System.in);
    Repository repository = new Repository();

    public Product insertProduct() {
        System.out.println();
        System.out.println("Prasome ivesti prekes duomenis: ");
        System.out.println("Iveskite pavadinima: ");
        product.setProductName(scanner.next());
        System.out.println("Nurodykite kaina");
        product.setPrice(scanner.nextDouble());
        System.out.println("Nurodykite spalva");
        product.setColor(scanner.next());
        System.out.println("Nurodykite dydi");
        product.setSize(scanner.nextInt());
        System.out.println("Nurodykite prekes tipa");
        repository.save(product);

        return product;
    }
    public Product findProductByName() {
        System.out.println("Iveskite ieskomos prekes pavadinima:");
        scanner.next().contains(product.getProductName());
        return product;
    }
}
