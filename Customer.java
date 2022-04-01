package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.CustomerType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
//    @JoinColumn(name = "orderId")
//    private Set<Orders> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private Account account;

    public boolean isNotPhoneNumber (String phone){
        Pattern p = Pattern.compile("(86|\\+3706)\\d{7}");
        Matcher m = p.matcher(phone);
        return !m.matches();
    }
    public boolean isNotEmailAddress (String email){
        Pattern p = Pattern.compile("(.*)(@)(.*)(.[a-z]{2,3})");
        Matcher m = p.matcher(email);
        return !m.matches();
    }

}