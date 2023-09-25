package Dao.Impl;

import Dao.ContractsDao;
import Models.Accounts;
import Models.Contracts;
import Models.Services;
import Models.Transactions;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContractsDaoImpl implements ContractsDao {
    public void create(Contracts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ContractsCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Contracts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ContractsUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Contracts acc) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(acc);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ContractsDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Contracts readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Contracts order = session.get(Contracts.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Contracts> readListByAcc(Accounts id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Contracts> query = session.createQuery("FROM Contracts WHERE account_id = :param", Contracts.class)
                .setParameter("param", id);
        return query.getResultList();
    }

    @Override
    public List<Contracts> readListByServ(Services id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Contracts> query = session.createQuery("FROM Contracts WHERE service_id = :param", Contracts.class)
                .setParameter("param", id);
        return query.getResultList();
    }
    @Override
    public List<Contracts> AllContracts() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Contracts> query = session.createQuery("FROM Contracts", Contracts.class);
        return query.getResultList();
    }
    @Override
    public List<Contracts> readListByAccList(List<Accounts> accs) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Contracts> query = session.createQuery("FROM Contracts WHERE account_id IN :param", Contracts.class)
                .setParameter("param", accs);
        return query.getResultList();
    }
}
