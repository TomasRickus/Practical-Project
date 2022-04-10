package repository;

import model.Customer;
import model.Orders;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class Repository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
    }

    public void saveCustomer(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
    }

    public void saveOrder(Orders orders) {
        Transaction transaction = session.beginTransaction();
        session.persist(orders);
        transaction.commit();
    }

    public void delete(Object object) {
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }

    public Customer findCustomerById(Integer customerId) {
        return session.find(Customer.class, customerId);
    }

    public Product findProductById(Integer productId) {
        return session.find(Product.class, productId);
    }

    public List<Orders> findOrdersById() {
        return session.createQuery("FROM Orders", Orders.class).getResultList();
    }

    public List<Product> findProduct() {
        return session.createQuery("FROM Product ", Product.class).getResultList();
    }

}


