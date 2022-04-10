package service;

import model.Customer;
import model.Orders;
import model.Product;
import repository.ProductType;
import repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    Product product = new Product();
    Scanner scanner = new Scanner(System.in);
    Repository repository = new Repository();
    Orders orders = new Orders();
    Customer customer = new Customer();

    private static String test(Orders orders1) {
        return orders1.getProduct().getProductName();
    }

    public Product insertProduct() {
        System.out.println();
        System.out.println("Prasome ivesti prekes duomenis: ");
        System.out.println("Iveskite pavadinima: ");
        String productName = scanner.next();
        product.setProductName(productName);
        System.out.println("Nurodykite kaina");
        Double price = scanner.nextDouble();
        product.setPrice(price);
        System.out.println("Nurodykite spalva");
        String color = scanner.next();
        product.setColor(color);
        System.out.println("Nurodykite dydi");
        int size = scanner.nextInt();
        product.setSize(size);
        System.out.println("Nurodykite prekes tipa");
        product.setProductType(choiceProductType());
        System.out.println("Nurodykite savo ID");
        int customerId = scanner.nextInt();
        product.setCustomer(repository.findCustomerById(customerId));
        repository.saveCustomer(product);
        System.out.println("Jus idejote " + product);

        return product;
    }

    public ProductType choiceProductType() {
        System.out.println("Pasirinkite tipa \n 1 Drabuziai \n 2 Batai \n 3 Aksesuarai ");
        int productTypeSelection = scanner.nextInt();

        if (productTypeSelection == 1) {
            return ProductType.CLOTHING;
        } else if (productTypeSelection == 2) {
            return ProductType.SHOES;
        } else if (productTypeSelection == 3) {
            return ProductType.ACCESSORIES;
        } else {
            System.out.println("Nepasirinktas prekes tipas!");
            return null;
        }
    }

    public void showProductByName() {
        System.out.println("Iveskite ieskomos prekes pavadinima");
        String searchProductByName = scanner.next();
        List<Product> products = repository.findProduct();
        products.stream().filter(product -> product.getProductName().equalsIgnoreCase(searchProductByName))
                .forEach(product -> System.out.println("Rasta preke: " + product.getProductName() + " Prekes tipas: "
                        + product.getProductType() +
                        " Prekes ID: " + product.getProductId() + " Kaina: " + product.getPrice() +
                        " Spalva: " + product.getColor() + " Dydis: " + product.getSize()));
    }

    public void showProductsByType() {
        System.out.println("Pasirinkite ieskomos prekes tipa \n 1 Drabuziai \n 2 Batai \n 3 Aksesuarai \n 4 Rodyti visas prekes " +
                "\n Arba spauskite 5 ir iveskite ieskomos prekes pavadinima");
        String productTypeSelection = scanner.next();
        List<Product> products = repository.findProduct();

        switch (productTypeSelection) {
            case "1" -> {
                products.stream().filter(product1 -> ProductType.CLOTHING.equals(product1.getProductType()))
                        .forEach(product -> System.out.println("Rasta preke: " + product.getProductName() + " Prekes tipas: "
                                + product.getProductType() +
                                " Prekes ID: " + product.getProductId() + " Kaina: " + product.getPrice() +
                                " Spalva: " + product.getColor() + " Dydis: " + product.getSize()));
            }
            case "2" -> {
                products.stream().filter(product1 -> ProductType.SHOES.equals(product1.getProductType()))
                        .forEach(product -> System.out.println("Rasta preke: " + product.getProductName() + " Prekes tipas: "
                                + product.getProductType() +
                                " Prekes ID: " + product.getProductId() + " Kaina: " + product.getPrice() +
                                " Spalva: " + product.getColor() + " Dydis: " + product.getSize()));
            }
            case "3" -> {
                products.stream().filter(product1 -> ProductType.ACCESSORIES.equals(product1.getProductType()))
                        .forEach(product -> System.out.println("Rasta preke: " + product.getProductName() + " Prekes tipas: "
                                + product.getProductType() +
                                " Prekes ID: " + product.getProductId() + " Kaina: " + product.getPrice() +
                                " Spalva: " + product.getColor() + " Dydis: " + product.getSize()));
            }
            case "4" -> {
                System.out.println("Nepasirinktas prekes tipas!");
                showAllProducts();
            }
            case "5" -> showProductByName();
        }

    }

    public void showAllProducts() {
        List<Product> products = repository.findProduct();
        products
                .forEach(product -> System.out.println("Rasta preke: " + product.getProductName() + " Prekes tipas: "
                        + product.getProductType() +
                        " Prekes ID: " + product.getProductId() + " Kaina: " + product.getPrice() +
                        " Spalva: " + product.getColor() + " Dydis: " + product.getSize()));
    }

    public Orders createOrder() {
        System.out.println("Iveskite prekes ID: ");
        Orders newOrder = new Orders();
        int productId = scanner.nextInt();
        newOrder.setProduct(repository.findProductById(productId));
        System.out.println("Preke ideta i krepseli " + newOrder.getProduct().getProductName());
        newOrder.setOrderDate(LocalDate.now());

        System.out.println("Patvirtinkite savo Id");
        int userIdInput = scanner.nextInt();
        newOrder.setCustomer(repository.findCustomerById(userIdInput));

        System.out.println("Patvirtinkite prekes ID");
        int userInputProductID = scanner.nextInt();
        newOrder.setTotalAmount(repository.findProductById(userInputProductID).getPrice());

        repository.saveOrder(newOrder);
        System.out.println("Jusu krepselio ID yra " + orders.getOrderId());
        System.out.println("Jusu doumenys: " + orders.getCustomer().getFirstName() + " " + orders.getCustomer().getLastName()
                + " suma " + orders.getTotalAmount());
        return orders;
    }

    public void showOrder() {
        System.out.println("Iveskite krepselio Id: ");
        int orderId = scanner.nextInt();
        List<Orders> ordersById = repository.findOrdersById();
        ordersById.stream().filter(orders1 -> orders1.getOrderId().equals(orderId))
                .forEach(orders1 -> System.out.println("Jusu preke: " + orders1.getProduct().getProductName() +
                        "\nKaina: " + orders1.getProduct().getPrice()));
    }
}

