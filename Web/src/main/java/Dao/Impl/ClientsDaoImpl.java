package Dao.Impl;

import Dao.ClientsDao;
import Models.Clients;
import Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClientsDaoImpl implements ClientsDao {
    public void create(Clients client) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ClientsCreate Exception thrown: " + e.getMessage());
        }
    }

    public void update(Clients client) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(client);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ClientsUpdate Exception thrown: " + e.getMessage());
        }
    }

    public void delete(Clients client) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(client);
            tx1.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("ClientsDelete Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public Clients readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Clients client = session.get(Clients.class, id);
        session.close();
        return client;
    }

    @Override
    public Clients readByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Clients> query = session.createQuery("FROM Clients WHERE name = :param", Clients.class)
                .setParameter("param", name);
        return query.getResultList().get(0);
    }
}
