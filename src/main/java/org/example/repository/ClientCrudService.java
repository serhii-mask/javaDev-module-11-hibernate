package org.example.repository;

import org.example.entities.Client;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class ClientCrudService implements ClientDao {

    private static final String GET_ALL_CLIENT_QUERY = "FROM Client";

    @Override
    public boolean createClient(Client client) {
        boolean result = false;

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                client.setId(null);
                session.persist(client);
                transaction.commit();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public boolean updateClient(Client client) {
        boolean result = false;

        if (Objects.isNull(client.getId())) {
            return false;
        }

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.merge(client);
                transaction.commit();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public Client getClientById(Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Client.class, clientId);
        }
    }

    @Override
    public List<Client> getAllClients() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_CLIENT_QUERY, Client.class).list();
        }
    }

    @Override
    public void deleteClientById(Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existing = session.get(Client.class, clientId);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void deleteClient(Client client) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }
}
