package Dao.Impl;

import Dao.ServicesDao;
import Models.Services;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServicesDaoImpl implements ServicesDao {
    public void create(Services s) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(s);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ServicesCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Services s) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(s);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ServicesUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Services s) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(s);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ServicesDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Services readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Services s = session.get(Services.class, id);
        session.close();
        return s;
    }

    @Override
    public Services readByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Services> query = session.createQuery("FROM Services WHERE name = :param", Services.class)
                .setParameter("param", name);
        return query.getResultList().get(0);
    }

    @Override
    public List<Services> readListByType(String type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Services> query = session.createQuery("FROM Services WHERE type = :param", Services.class)
                .setParameter("param", type);
        return query.getResultList();
    }
}
