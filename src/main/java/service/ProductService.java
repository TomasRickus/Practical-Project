package service;

import model.Orders;
import model.Product;
import repository.ProductType;
import repository.Repository;
import validator.JsonReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    Product product = new Product();
    Scanner scanner = new Scanner(System.in);
    Repository repository = new Repository();
    Orders orders = new Orders();
    PrintingService printingService = new PrintingService();

    public Product insertProduct() {
        System.out.println();
        System.out.println("Prasome ivesti prekes duomenis: ");
        System.out.println("Iveskite pavadinima: ");
        String productName = scanner.next();
        product.setProductName(productName);
        System.out.println("Nurodykite kaina");
        String price = scanner.next();
        product.setPrice(price);
        System.out.println("Nurodykite spalva");
        String color = scanner.next();
        product.setColor(color);
        System.out.println("Nurodykite dydi");
        String size = scanner.next();
        product.setSize(size);
        System.out.println("Nurodykite prekes tipa");
        product.setProductType(choiceProductType());
        System.out.println("Nurodykite savo ID");
        int customerId = scanner.nextInt();
        product.setCustomer(repository.findCustomerById(customerId));
        product.setAvailable(true);
        repository.saveProduct(product);
        System.out.println("Jus idejote " + product);

        return product;
    }
    public Product insertProductFromFile() throws IOException {
        JsonReader jsonReader = new JsonReader();
        Product productListFromFile = (Product) jsonReader.getProductListFromFile("src/main/java/service/MOCK_DATA.json");

        repository.saveProduct(productListFromFile);
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

    public void searchProducts() {
        printingService.printFindProduct();
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
            case "4" -> showAllProducts();

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
        System.out.println("Jusu krepselio ID yra " + newOrder.getOrderId());
        System.out.println("Jusu doumenys: " + newOrder.getCustomer().getFirstName() + " " + newOrder.getCustomer().getLastName()
                + " suma " + newOrder.getTotalAmount());
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

    public void deleteProduct() {
        System.out.println("Susiraskite preke kuria norite istrinti: ");
        searchProducts();
        System.out.println("Iveskite id prekes kuria norite istrinti: ");
        int idToDelete = scanner.nextInt();
        Product productById = repository.findProductById(idToDelete);
        repository.delete(productById);
        System.out.println("Preke " + productById.getProductName() + " istrinta");

    }

    public void updateProduct() {
        System.out.println("Susiraskite preke kuria norite koreguoti: ");
        searchProducts();
        System.out.println("Iveskite id prekes kuria norite koreguoti: ");
        int idToDelete = scanner.nextInt();
        Product productById = repository.findProductById(idToDelete);
        productById.setAvailable(false);
        repository.update(productById);
        product.isProductAvailable(productById);
    }
}

