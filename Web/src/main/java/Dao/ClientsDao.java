package Dao;

import Models.Clients;
import Models.Transactions;

import java.util.List;

public interface ClientsDao {
    void create(Clients client);
    void update(Clients client);
    void delete(Clients client);
    Clients readByID(int id);
    Clients readByName(String name);
    List<Clients> AllClients();
}