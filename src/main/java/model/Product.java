package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.ProductType;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private ProductType productType;
    private String price;
    private String color;
    private String size;
    private String productName;
    boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne(mappedBy = "product")
    private Orders orders;

    public Product isProductAvailable(Product product) {
        if (!product.isAvailable)
            System.out.println("Preke parduota/nepasiekiama ");
        return product;
    }

    @Override
    public String toString() {
        return "Preke: " +
                "Prekes ID: " + productId +
                ", Prekes tipas: " + productType +
                ", Kaina: " + price +
                ", Spava: '" + color + '\'' +
                ", Dydis: " + size +
                ", Prekes pavadinimas: '" + productName + '\'';
    }
}
