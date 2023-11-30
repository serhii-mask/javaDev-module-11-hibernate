package org.example.repository.ticket;

import org.example.entities.Ticket;
import org.example.repository.GenericDao;

import java.util.List;

public class TicketCrudService {

    private final GenericDao<Ticket, Long> ticketDao;

    public TicketCrudService(GenericDao<Ticket, Long> ticketDao) {
        this.ticketDao = ticketDao;
    }

    public boolean createTicket(Ticket ticket) {
        return ticketDao.create(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return ticketDao.update(ticket);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketDao.getById(ticketId);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.getAll();
    }

    public void deleteTicketById(Long ticketId) {
        ticketDao.deleteById(ticketId);
    }

    public void deleteTicket(Ticket ticket) {
        ticketDao.delete(ticket);
    }
}
