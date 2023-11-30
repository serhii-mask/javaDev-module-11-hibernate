package org.example.service.client;

import org.example.entities.Client;
import org.example.repository.GenericDao;

import java.util.List;

public class ClientCrudService {

    private final GenericDao<Client, Long> clientDao;

    public ClientCrudService(GenericDao<Client, Long> clientDao) {
        this.clientDao = clientDao;
    }

    public boolean createClient(Client client) {
        return clientDao.create(client);
    }

    public boolean updateClient(Client client) {
        return clientDao.update(client);
    }

    public Client getClientById(Long clientId) {
        return clientDao.getById(clientId);
    }

    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    public void deleteClientById(Long clientId) {
        clientDao.deleteById(clientId);
    }

    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}
