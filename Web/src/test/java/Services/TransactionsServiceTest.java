package Services;

import Models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class TransactionsServiceTest {

    @Test
    public void testCreateTransaction() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction);

        Transactions check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertEquals(new_transaction, check_service);

        transactionsService.deleteTransaction(new_transaction);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testDeleteTransactionForever() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction);

        Transactions check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertEquals(new_transaction, check_service);

        transactionsService.deleteTransaction(new_transaction);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
        check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertNull(check_service);
    }

    @Test
    public void testUpdateTransaction() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction);

        Transactions check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertEquals(new_transaction, check_service);

        new_transaction.setValue(3.0F);
        transactionsService.updateTransaction(new_transaction);
        check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertEquals(new_transaction, check_service);

        transactionsService.deleteTransaction(new_transaction);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadTransactionByID() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction);

        Transactions check_service = transactionsService.readTransactionByID(new_transaction.getTransaction_id());
        Assert.assertEquals(new_transaction.getTransaction_id(), check_service.getTransaction_id());

        transactionsService.deleteTransaction(new_transaction);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadTransactionsListByAcc() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction1 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        Transactions new_transaction2 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        Transactions new_transaction3 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction1);
        transactionsService.createTransaction(new_transaction2);
        transactionsService.createTransaction(new_transaction3);

        List<Transactions> list_of_t = transactionsService.readTransactionsListByAcc(acc);
        Assert.assertEquals(list_of_t.size(), 3);
        Assert.assertTrue(list_of_t.contains(new_transaction1));
        Assert.assertTrue(list_of_t.contains(new_transaction2));
        Assert.assertTrue(list_of_t.contains(new_transaction3));

        transactionsService.deleteTransaction(new_transaction1);
        transactionsService.deleteTransaction(new_transaction2);
        transactionsService.deleteTransaction(new_transaction3);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadTransactionsListByContracts() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        TransactionsService transactionsService = new TransactionsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);
        Transactions new_transaction1 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        Transactions new_transaction2 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        Transactions new_transaction3 = new Transactions(acc, new_contract, 0.0F, new Timestamp(2023));
        transactionsService.createTransaction(new_transaction1);
        transactionsService.createTransaction(new_transaction2);
        transactionsService.createTransaction(new_transaction3);

        List<Transactions> list_of_t = transactionsService.readTransactionsListByCon(new_contract);
        Assert.assertEquals(list_of_t.size(), 3);
        Assert.assertTrue(list_of_t.contains(new_transaction1));
        Assert.assertTrue(list_of_t.contains(new_transaction2));
        Assert.assertTrue(list_of_t.contains(new_transaction3));

        transactionsService.deleteTransaction(new_transaction1);
        transactionsService.deleteTransaction(new_transaction2);
        transactionsService.deleteTransaction(new_transaction3);
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }
}