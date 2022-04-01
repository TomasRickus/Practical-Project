package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;
import repository.ProductType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private Double price;
    private String color;
    private Integer size;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "orders_to_products") // sukuriam papildoma lentele suristi ManyToMany
//    private Set<Orders> orders;//vienoje bibliotekoje gali lankytis daug klientu (owneriu)

}
