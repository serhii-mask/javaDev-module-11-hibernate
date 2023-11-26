package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.repository.client.ClientCrudService;
import org.example.repository.planet.PlanetCrudService;
import org.example.repository.ticket.TicketCrudService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
        TicketCrudService ticketService = new TicketCrudService();

        Client clientOne = clientService.getClientById(1L);
        Client clientFive = clientService.getClientById(5L);
        Planet planetAlderaan = planetService.getPlanetById("ALDERAAN");
        Planet planetNaboo = planetService.getPlanetById("NAB007");
        Planet planetEndor = planetService.getPlanetById("ENDOR");

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

        List<Ticket> ticketsBeforeDelete = ticketService.getAllTickets();
        System.out.println("tickets = " + ticketsBeforeDelete.toString());

        ticketService.deleteTicketById(7L);
        clientService.deleteClient(clientOne);

        List<Ticket> ticketsAfterDelete = ticketService.getAllTickets();
        System.out.println("tickets = " + ticketsAfterDelete.toString());
    }
}