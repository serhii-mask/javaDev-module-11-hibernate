package org.example.repository.ticket;

import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.repository.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class TicketDaoImpl implements GenericDao<Ticket, Long> {

    private static final String GET_ALL_TICKET_QUERY = "FROM Ticket";

    @Override
    public boolean create(Ticket ticket) {
        boolean result = false;

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                ticket.setId(null);
                session.persist(ticket);
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
    public boolean update(Ticket ticket) {
        boolean result = false;

        if (Objects.isNull(ticket.getId())) {
            return false;
        }

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.merge(ticket);
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
    public Ticket getById(Long id) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_TICKET_QUERY, Ticket.class).list();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, id);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        }
    }
}
