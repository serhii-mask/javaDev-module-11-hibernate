package org.example.repository.ticket;

import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class TicketCrudService implements TicketDao {

    private static final String GET_ALL_TICKET_QUERY = "FROM Ticket";

    @Override
    public boolean createTicket(Ticket ticket) {
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
    public boolean updateTicket(Ticket ticket) {
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
    public Ticket getTicketById(Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Ticket.class, ticketId);
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_TICKET_QUERY, Ticket.class).list();
        }
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, ticketId);
            session.remove(existing);
            transaction.commit();
        }
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        }
    }
}
