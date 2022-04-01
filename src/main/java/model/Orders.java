package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private Double totalAmount;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "products_to_orders")
    private List<Product> products;
//
//    @ManyToMany(mappedBy = "orders")
//    @JoinColumn(name = "customerId")
//    private Set<Product> products;

//    @ManyToOne
//    private List<Customer> customers;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_customer_id")
//    private Customer customer;

}
