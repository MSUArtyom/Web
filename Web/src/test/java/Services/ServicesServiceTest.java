package Services;

import Models.Services;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class ServicesServiceTest {

    @Test
    public void testCreateService() {
        ServicesService servicesService = new ServicesService();
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);

        Services check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service, check_service);

        servicesService.deleteServiceForever(new_service);
    }

    @Test
    public void testDeleteServiceForever() {
        ServicesService servicesService = new ServicesService();
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);

        Services check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service, check_service);

        servicesService.deleteServiceForever(new_service);
        check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertNull(check_service);
    }

    @Test
    public void testUpdateService() {
        ServicesService servicesService = new ServicesService();
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);

        Services check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service, check_service);

        new_service.setName("НЕтестовая услуга");
        servicesService.updateService(new_service);
        check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service, check_service);
        servicesService.deleteServiceForever(new_service);
    }

    @Test
    public void testReadServiceByID() {
        ServicesService servicesService = new ServicesService();
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);

        Services check_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service.getService_id(), check_service.getService_id());
        servicesService.deleteServiceForever(new_service);
    }

    @Test
    public void testReadServiceByName() {
        ServicesService servicesService = new ServicesService();
        Services new_service = new Services("Тестовая услуга", "ТВ", "описание", "план");
        servicesService.createService(new_service);

        Services check_service = servicesService.readServiceByName("Тестовая услуга");
        Assert.assertEquals("Тестовая услуга", check_service.getName());
        servicesService.deleteServiceForever(check_service);
    }

    @Test
    public void testReadServicesListByType() {
        ServicesService servicesService = new ServicesService();
        Services new_service1 = new Services("Тестовая услуга1", "ТВ3", "описание", "план");
        Services new_service2 = new Services("Тестовая услуга1", "ТВ3", "описание", "план");
        Services new_service3 = new Services("Тестовая услуга1", "ТВ3", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        servicesService.createService(new_service3);
        List<Services> list_of_s = servicesService.readServiceListByType("ТВ3");
        Assert.assertTrue(list_of_s.contains(new_service1));
        Assert.assertTrue(list_of_s.contains(new_service2));
        Assert.assertTrue(list_of_s.contains(new_service3));
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        servicesService.deleteServiceForever(new_service3);
    }

    @Test
    public void readAllServices() {
        ServicesService servicesService = new ServicesService();
        Services new_service1 = new Services("Тестовая услуга1", "ТВ", "описание", "план");
        Services new_service2 = new Services("Тестовая услуга1", "ТВ3", "описание", "план");
        Services new_service3 = new Services("Тестовая услуга1", "Т3", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        servicesService.createService(new_service3);
        List<Services> list_of_s = servicesService.readAllServices();
        Assert.assertTrue(list_of_s.contains(new_service1));
        Assert.assertTrue(list_of_s.contains(new_service2));
        Assert.assertTrue(list_of_s.contains(new_service3));
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        servicesService.deleteServiceForever(new_service3);
    }

    @Test
    public void readAllServicesByName() {
        ServicesService servicesService = new ServicesService();
        Services new_service1 = new Services("000000AТестовая услуга1", "ТВ", "описание", "план");
        Services new_service2 = new Services("000000BТестовая услуга1", "ТВ3", "описание", "план");
        Services new_service3 = new Services("000000CТестовая услуга1", "Т3", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        servicesService.createService(new_service3);
        List<Services> list_of_s = servicesService.readAllServicesByName();
        Assert.assertTrue(list_of_s.contains(new_service1));
        Assert.assertTrue(list_of_s.contains(new_service2));
        Assert.assertTrue(list_of_s.contains(new_service3));
        Assert.assertEquals(list_of_s.get(0), new_service1);
        Assert.assertEquals(list_of_s.get(1), new_service2);
        Assert.assertEquals(list_of_s.get(2), new_service3);
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        servicesService.deleteServiceForever(new_service3);
    }

    @Test
    public void readAllServicesByType() {
        ServicesService servicesService = new ServicesService();
        Services new_service1 = new Services("000000AТестовая услуга1", "001ТВ", "описание", "план");
        Services new_service2 = new Services("000000BТестовая услуга1", "002ТВ3", "описание", "план");
        Services new_service3 = new Services("000000CТестовая услуга1", "003Т3", "описание", "план");
        servicesService.createService(new_service1);
        servicesService.createService(new_service2);
        servicesService.createService(new_service3);
        List<Services> list_of_s = servicesService.readAllServicesByType();
        Assert.assertTrue(list_of_s.contains(new_service1));
        Assert.assertTrue(list_of_s.contains(new_service2));
        Assert.assertTrue(list_of_s.contains(new_service3));
        Assert.assertEquals(list_of_s.get(0), new_service1);
        Assert.assertEquals(list_of_s.get(1), new_service2);
        Assert.assertEquals(list_of_s.get(2), new_service3);
        servicesService.deleteServiceForever(new_service1);
        servicesService.deleteServiceForever(new_service2);
        servicesService.deleteServiceForever(new_service3);
    }
}