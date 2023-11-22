package org.example.repository;

import org.example.entities.Client;

import java.util.List;

public interface ClientDao {

    boolean createClient(Client client);

    boolean updateClient(Client client);

    Client getClientById(Long clientId);

    List<Client> getAllClients();

    void deleteClientById(Long clientId);

    void deleteClient(Client client);
}
