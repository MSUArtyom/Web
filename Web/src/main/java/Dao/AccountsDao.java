package Dao;
import Models.Accounts;
import Models.Clients;

import java.util.List;

public interface AccountsDao {
    void create(Accounts account);
    void update(Accounts account);
    void delete(Accounts account);
    Accounts readByID(int id);
    List<Accounts> readListByClientId(Clients id);
}
