package Dao.Impl;

import Dao.AccountsDao;
import Models.Accounts;
import Models.Clients;
import Models.Transactions;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccountsDaoImpl implements AccountsDao {
    public void create(Accounts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AccountsCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Accounts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AccountsUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Accounts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("AccountsDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Accounts readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Accounts order = session.get(Accounts.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Accounts> readListByClientId(Clients id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Accounts> query = session.createQuery("FROM Accounts WHERE client_id = :param", Accounts.class)
                .setParameter("param", id);
        return query.getResultList();
    }

}
