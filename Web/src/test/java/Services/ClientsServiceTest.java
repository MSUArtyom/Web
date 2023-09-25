package Services;

import Models.Clients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ClientsServiceTest {

    @Test
    public void testCreateClient() {
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);

        Clients check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client, check_client);

        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testDeleteClientForever() {
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);

        Clients check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client, check_client);

        clientsService.deleteClientForever(new_client);
        check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertNull(check_client);
    }

    @Test
    public void testUpdateClient() {
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);

        Clients check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client, check_client);

        new_client.setName("Иван Иванович Крылов");
        clientsService.updateClient(new_client);
        check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client, check_client);
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadClientByID() {
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);

        Clients check_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client.getClient_id(), check_client.getClient_id());
        clientsService.deleteClientForever(new_client);
    }

    @Test
    public void testReadClientByName() {
        ClientsService clientsService = new ClientsService();
        Clients new_client = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client);

        Clients check_client = clientsService.readClientByName("Дмитрий Иванович Крылов");
        Assert.assertEquals("Дмитрий Иванович Крылов", check_client.getName());
        clientsService.deleteClientForever(check_client);
    }

    @Test
    public void readAllClients() {
        ClientsService clientsService = new ClientsService();
        Clients new_client1 = new Clients("Дмитрий Иванович Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        Clients new_client2 = new Clients("Дмитрий Крылов", "kryl@fmaiil.com", "+72344114444444", "г. Москва, Ленинский пр-кт, д.7");
        clientsService.createClient(new_client1);
        clientsService.createClient(new_client2);

        List<Clients> check_client = clientsService.readAllClients();
        Assert.assertTrue(check_client.contains(new_client1));
        Assert.assertTrue(check_client.contains(new_client2));
        clientsService.deleteClientForever(new_client1);
        clientsService.deleteClientForever(new_client2);
    }
}