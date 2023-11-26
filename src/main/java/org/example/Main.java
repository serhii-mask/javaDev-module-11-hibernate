package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.repository.client.ClientCrudGeneric;
import org.example.repository.planet.PlanetCrudService;
import org.example.repository.ticket.TicketCrudService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = HibernateUtils.getInstance();
        hibernateUtils.migrateDatabase();
        hibernateUtils.closeSessionFactory();

        ClientCrudGeneric clientService = new ClientCrudGeneric();
        PlanetCrudService planetService = new PlanetCrudService();
        TicketCrudService ticketService = new TicketCrudService();

        Client clientOne = clientService.getById(1L);
        Client clientFive = clientService.getById(5L);
        Planet planetAlderaan = planetService.getById("ALDERAAN");
        Planet planetNaboo = planetService.getById("NAB007");
        Planet planetEndor = planetService.getById("ENDOR");

        Ticket ticketOne = new Ticket();
        ticketOne.setCreatedAt(LocalDate.now());
        ticketOne.setClient(clientOne);
        ticketOne.setFromPlanet(planetAlderaan);
        ticketOne.setToPlanet(planetNaboo);
        clientOne.getTickets().add(ticketOne);

        Ticket ticketTwo = new Ticket();
        ticketTwo.setCreatedAt(LocalDate.now());
        ticketTwo.setClient(clientOne);
        ticketTwo.setFromPlanet(planetNaboo);
        ticketTwo.setToPlanet(planetEndor);
        clientOne.getTickets().add(ticketTwo);

        Ticket ticketThree = new Ticket();
        ticketThree.setCreatedAt(LocalDate.now());
        ticketThree.setClient(clientFive);
        ticketThree.setFromPlanet(planetEndor);
        ticketThree.setToPlanet(planetAlderaan);
        clientFive.getTickets().add(ticketThree);

        List<Ticket> ticketsBeforeDelete = ticketService.getAll();
        System.out.println("tickets = " + ticketsBeforeDelete.toString());

        ticketService.deleteById(7L);
        clientService.delete(clientOne);

        List<Ticket> ticketsAfterDelete = ticketService.getAll();
        System.out.println("tickets = " + ticketsAfterDelete.toString());
    }
}