package Dao;
import Models.Accounts;
import Models.Contracts;
import Models.Services;

import java.util.List;

public interface ContractsDao {
    void create(Contracts contract);
    void update(Contracts contract);
    void delete(Contracts contract);
    Contracts readByID(int id);
    List<Contracts> readListByAcc(Accounts id);
    List<Contracts> readListByServ(Services id);
}