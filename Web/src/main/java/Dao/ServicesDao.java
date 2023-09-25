package Dao;

import Models.Services;
import Models.Transactions;

import java.util.List;

public interface ServicesDao {
    void create(Services service);
    void update(Services service);
    void delete(Services service);
    Services readByID(int id);
    Services readByName(String name);
    List<Services> readListByType(String type);
    List<Services> AllServices();
    List<Services> AllServicesByType();
    List<Services> AllServicesByName();
}