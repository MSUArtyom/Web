package Dao;

import Models.Clients;

public interface ClientsDao {
    void create(Clients client);
    void update(Clients client);
    void delete(Clients client);
    Clients readByID(int id);
    Clients readByName(String name);
}