package org.example.repository.ticket;

import org.example.entities.Ticket;

import java.util.List;

public interface TicketDao {
    boolean createTicket(Ticket ticket);

    boolean updateTicket(Ticket ticket);

    Ticket getTicketById(Long ticketId);

    List<Ticket> getAllTickets();

    void deleteTicketById(Long ticketId);

    void deleteTicket(Ticket ticket);
}
