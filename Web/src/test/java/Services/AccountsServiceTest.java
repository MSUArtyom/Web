package Services;

import Models.Accounts;
import Models.Clients;
import Models.Contracts;
import Models.Services;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class AccountsServiceTest {

    @Test
    public void testCreateAccount() {
        ClientsService clientsService = new ClientsService();
        AccountsService accountsService = new AccountsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);

        Accounts check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertEquals(acc, check_service);

        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testDeleteAccountForever() {
        ClientsService clientsService = new ClientsService();
        AccountsService accountsService = new AccountsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);

        Accounts check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertEquals(acc, check_service);

        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
        check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertNull(check_service);
    }

    @Test
    public void testUpdateAccount() {
        ClientsService clientsService = new ClientsService();
        AccountsService accountsService = new AccountsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);

        Accounts check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertEquals(acc, check_service);

        acc.setBalance(1.0F);
        accountsService.updateAccount(acc);
        check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertEquals(acc, check_service);

        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadAccountByID() {
        ClientsService clientsService = new ClientsService();
        AccountsService accountsService = new AccountsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc);

        Accounts check_service = accountsService.readAccountByID(acc.getAccount_id());
        Assert.assertEquals(acc.getAccount_id(), check_service.getAccount_id());

        accountsService.deleteAccount(acc);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadAccountsListByClients() {
        AccountsService accountsService = new AccountsService();
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);
        Accounts acc1 = new Accounts(new_client, 0.0F, 0.0F, 0);
        Accounts acc2 = new Accounts(new_client, 0.0F, 0.0F, 0);
        Accounts acc3 = new Accounts(new_client, 0.0F, 0.0F, 0);
        accountsService.createAccount(acc1);
        accountsService.createAccount(acc2);
        accountsService.createAccount(acc3);
        List<Accounts> list_of_a = accountsService.readAccountsListByClientId(new_client);
        Assert.assertEquals(list_of_a.size(), 3);
        Assert.assertTrue(list_of_a.contains(acc1));
        Assert.assertTrue(list_of_a.contains(acc2));
        Assert.assertTrue(list_of_a.contains(acc3));
        accountsService.deleteAccount(acc1);
        accountsService.deleteAccount(acc2);
        accountsService.deleteAccount(acc3);
        clientsService.deleteClientForever(new_client);
    }
}