package Services;

import Dao.ClientsDao;
import Dao.Impl.ClientsDaoImpl;
import Models.Clients;

public class ClientsService {
    private ClientsDao clientsDao = new ClientsDaoImpl();
    public void createClient(Clients client) {
        clientsDao.create(client);
    }

    public void deleteClientForever(Clients client) {
        clientsDao.delete(client);
    }

    public void updateClient(Clients client) {
        clientsDao.update(client);
    }

    public Clients readClientByID(int id) {
        return clientsDao.readByID(id);
    }

    public Clients readClientByName(String name) {
        return clientsDao.readByName(name);
    }

}