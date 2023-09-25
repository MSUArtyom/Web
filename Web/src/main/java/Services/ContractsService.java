package Services;

import Dao.Impl.ContractsDaoImpl;
import Dao.ContractsDao;
import Models.Accounts;
import Models.Contracts;
import Models.Services;
import Models.Transactions;

import java.util.List;

public class ContractsService {
    private ContractsDao contractsDao = new ContractsDaoImpl();
    public void createContract(Contracts contract) {
        contractsDao.create(contract);
    }

    public void deleteContract(Contracts contract) {
        contractsDao.delete(contract);
    }

    public void updateContract(Contracts contract) {
        contractsDao.update(contract);
    }

    public Contracts readContractByID(int id) {
        return contractsDao.readByID(id);
    }

    public List<Contracts> readContractsListByAcc(Accounts id) {
        return contractsDao.readListByAcc(id);
    }

    public List<Contracts> readContractsListByServ(Services id) {
        return contractsDao.readListByServ(id);
    }

    public List<Contracts> readAllContracts() {
        return contractsDao.AllContracts();
    }

    public List<Contracts> readContractsListByAccList(List<Accounts> accs) {
        return contractsDao.readListByAccList(accs);
    }

}