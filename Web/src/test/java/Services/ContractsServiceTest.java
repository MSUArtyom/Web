package Services;

import Models.Accounts;
import Models.Clients;
import Models.Contracts;
import Models.Services;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class ContractsServiceTest {

    @Test
    public void testCreateContract() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);

        Contracts check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertEquals(new_contract, check_service);

        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testDeleteContractForever() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);

        Contracts check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertEquals(new_contract, check_service);

        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
        check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertNull(check_service);
    }

    @Test
    public void testUpdateContract() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);

        Contracts check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertEquals(new_contract, check_service);

        new_contract.setTime_period("new_time");
        contractsService.updateContract(new_contract);
        check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertEquals(new_contract, check_service);

        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadContractByID() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract);

        Contracts check_service = contractsService.readContractByID(new_contract.getContract_id());
        Assert.assertEquals(new_contract.getContract_id(), check_service.getContract_id());
        contractsService.deleteContract(new_contract);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadContractsListByAcc() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract1 = new Contracts(acc, new_service, "period");
        Contracts new_contract2 = new Contracts(acc, new_service, "period");
        Contracts new_contract3 = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract1);
        contractsService.createContract(new_contract2);
        contractsService.createContract(new_contract3);

        List<Contracts> list_of_c = contractsService.readContractsListByAcc(acc);
        Assert.assertEquals(list_of_c.size(), 3);
        Assert.assertTrue(list_of_c.contains(new_contract1));
        Assert.assertTrue(list_of_c.contains(new_contract2));
        Assert.assertTrue(list_of_c.contains(new_contract3));

        contractsService.deleteContract(new_contract1);
        contractsService.deleteContract(new_contract2);
        contractsService.deleteContract(new_contract3);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadContractsListByServ() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);
        Contracts new_contract1 = new Contracts(acc, new_service, "period");
        Contracts new_contract2 = new Contracts(acc, new_service, "period");
        Contracts new_contract3 = new Contracts(acc, new_service, "period");
        contractsService.createContract(new_contract1);
        contractsService.createContract(new_contract2);
        contractsService.createContract(new_contract3);

        List<Contracts> list_of_c = contractsService.readContractsListByServ(new_service);
        Assert.assertEquals(list_of_c.size(), 3);
        Assert.assertTrue(list_of_c.contains(new_contract1));
        Assert.assertTrue(list_of_c.contains(new_contract2));
        Assert.assertTrue(list_of_c.contains(new_contract3));

        contractsService.deleteContract(new_contract1);
        contractsService.deleteContract(new_contract2);
        contractsService.deleteContract(new_contract3);
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void readAllContracts() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);
        Services new_service1 = new Services("Тестовая услуга", "ТВ", "описание", "план");
        Services new_service2 = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        Contracts new_contract1 = new Contracts(acc, new_service1, "period");
        Contracts new_contract2 = new Contracts(acc, new_service2, "period");
        Contracts new_contract3 = new Contracts(acc, new_service1, "period");
        contractsService.createContract(new_contract1);
        contractsService.createContract(new_contract2);
        contractsService.createContract(new_contract3);

        List<Contracts> list_of_c = contractsService.readAllContracts();
        Assert.assertTrue(list_of_c.contains(new_contract1));
        Assert.assertTrue(list_of_c.contains(new_contract2));
        Assert.assertTrue(list_of_c.contains(new_contract3));

        contractsService.deleteContract(new_contract1);
        contractsService.deleteContract(new_contract2);
        contractsService.deleteContract(new_contract3);
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void readContractsListByAccList() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        ContractsService contractsService = new ContractsService();
        ServicesService servicesService = new ServicesService();
        Clients new_client1 = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        Clients new_client2 = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client1);
        clientsService.createClient(new_client2);
        Accounts acc1 = new Accounts(new_client1, 0.0F, 0.0F, 0);
        Accounts acc2 = new Accounts(new_client2, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc1);
        accountsService.createAccount(acc2);
        Services new_service1 = new Services("Тестовая услуга", "ТВ", "описание", "план");
        Services new_service2 = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        Contracts new_contract1 = new Contracts(acc1, new_service1, "period");
        Contracts new_contract2 = new Contracts(acc1, new_service2, "period");
        Contracts new_contract3 = new Contracts(acc2, new_service1, "period");
        contractsService.createContract(new_contract1);
        contractsService.createContract(new_contract2);
        contractsService.createContract(new_contract3);

        List<Contracts> list_of_c = contractsService.readContractsListByAccList(accountsService.
                readAccountsListByClientId(new_client1));
        Assert.assertTrue(list_of_c.contains(new_contract1));
        Assert.assertTrue(list_of_c.contains(new_contract2));
        Assert.assertFalse(list_of_c.contains(new_contract3));

        contractsService.deleteContract(new_contract1);
        contractsService.deleteContract(new_contract2);
        contractsService.deleteContract(new_contract3);
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        accountsService.deleteAccount(acc1);
        accountsService.deleteAccount(acc2);
        clientsService.deleteClientForever(new_client1);
        clientsService.deleteClientForever(new_client2);
    }
}