package repository;

import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class Repository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void save(Object object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
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

}
