package Services;

import Dao.Impl.AccountsDaoImpl;
import Dao.AccountsDao;
import Models.Accounts;
import Models.Clients;
import Models.Transactions;

import java.util.List;

public class AccountsService {
    private AccountsDao accountsDao = new AccountsDaoImpl();
    public void createAccount(Accounts acc) {
        accountsDao.create(acc);
    }

    public void deleteAccount(Accounts acc) {
        accountsDao.delete(acc);
    }

    public void updateAccount(Accounts acc) {
        accountsDao.update(acc);
    }

    public Accounts readAccountByID(int id) {
        return accountsDao.readByID(id);
    }

    public List<Accounts> readAccountsListByClientId(Clients id) {
        return accountsDao.readListByClientId(id);
    }
}
