package Services;

import Dao.Impl.TransactionsDaoImpl;
import Dao.TransactionsDao;
import Models.Accounts;
import Models.Contracts;
import Models.Transactions;

import java.util.List;

public class TransactionsService {
    private TransactionsDao transactionsDao = new TransactionsDaoImpl();
    public void createTransaction(Transactions transaction) {
        transactionsDao.create(transaction);
    }

    public void deleteTransaction(Transactions transaction) {
        transactionsDao.delete(transaction);
    }

    public void updateTransaction(Transactions transactions) {
        transactionsDao.update(transactions);
    }

    public Transactions readTransactionByID(int id) {
        return transactionsDao.readByID(id);
    }

    public List<Transactions> readTransactionsListByAcc(Accounts id) {
        return transactionsDao.readListByAcc(id);
    }

    public List<Transactions> readTransactionsListByCon(Contracts id) {
        return transactionsDao.readListByCon(id);
    }
}
