package Dao;
import Models.Accounts;
import Models.Contracts;
import Models.Transactions;

import java.util.List;

public interface TransactionsDao {
    void create(Transactions transaction);
    void update(Transactions transaction);
    void delete(Transactions transaction);
    Transactions readByID(int id);
    List<Transactions> readListByAcc(Accounts id);
    List<Transactions> readListByCon(Contracts id);
}