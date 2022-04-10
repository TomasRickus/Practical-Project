package repository;

import model.Customer;
import model.Orders;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    public Product findProductByName(String productName) {
        return session.find(Product.class, productName);
    }

    public Customer findCustomerById(Integer customerId) {
        return session.find(Customer.class, customerId);
    }


        public List<Orders> findOrderById() {
            String hql = "FROM Orders";
            Query query = session.createQuery(hql);
            List results = query.list();
            return results;
    }
    public List <Customer> findCustomerById() {
        String hql = "FROM Cuctomer";
        Query query = session.createQuery(hql);
        List results = query.list();
        return results;
    }


    public Product findProductById(Integer productId) {
        return session.find(Product.class, productId);
    }
    public List<Orders> findOrdersById(){
        return session.createQuery("FROM Orders" , Orders.class).getResultList();
    }

    public Customer findCustomerByName(String firstName) {
        return session.find(Customer.class, firstName);
    }

    public List<Product> findProduct() {
        return session.createQuery("FROM Product ", Product.class).getResultList();

    }

}


