package repository;

import model.Account;
import model.Customer;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class Repository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void save(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
    }
    public void save(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
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

    public Customer findById(Integer customerId) {
        return session.find(Customer.class, customerId);
    }
    public Customer findByName (String firstName) {
        return session.find(Customer.class, firstName);
    }

    public Account findByPassword(Integer password) {
        return session.find(Account.class, password);
    }


}
