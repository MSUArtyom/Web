package Services;

import Dao.ServicesDao;
import Dao.Impl.ServicesDaoImpl;
import Models.Services;
import Models.Transactions;

import java.util.List;

public class ServicesService {
    private ServicesDao servicesDao = new ServicesDaoImpl();
    public void createService(Services service) {
        servicesDao.create(service);
    }

    public void deleteServiceForever(Services service) {
        servicesDao.delete(service);
    }

    public void updateService(Services service) {
        servicesDao.update(service);
    }

    public Services readServiceByID(int id) {
        return servicesDao.readByID(id);
    }

    public Services readServiceByName(String name) {
        return servicesDao.readByName(name);
    }

    public List<Services> readServiceListByType(String type) {
        return servicesDao.readListByType(type);
    }

    public List<Services> readAllServices() {
        return servicesDao.AllServices();
    }

    public List<Services> readAllServicesByName() {
        return servicesDao.AllServicesByName();
    }

    public List<Services> readAllServicesByType() {
        return servicesDao.AllServicesByType();
    }

}