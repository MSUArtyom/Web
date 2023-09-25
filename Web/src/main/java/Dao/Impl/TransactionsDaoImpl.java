package Dao.Impl;

import Dao.TransactionsDao;
import Models.Accounts;
import Models.Contracts;
import Models.Transactions;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TransactionsDaoImpl implements TransactionsDao {
    public void create(Transactions acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TransactionsCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Transactions acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TransactionsUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Transactions acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("TransactionsDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Transactions readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transactions order = session.get(Transactions.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Transactions> readListByAcc(Accounts id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Transactions> query = session.createQuery("FROM Transactions WHERE account_id = :param", Transactions.class)
                .setParameter("param", id);
        return query.getResultList();
    }

    @Override
    public List<Transactions> readListByCon(Contracts id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Transactions> query = session.createQuery("FROM Transactions WHERE contract_id = :param", Transactions.class)
                .setParameter("param", id);
        return query.getResultList();
    }
    @Override
    public List<Transactions> AllTransactions() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Transactions> query = session.createQuery("FROM Transactions", Transactions.class);
        return query.getResultList();
    }
}
