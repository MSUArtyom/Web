package Controllers;

import Models.Accounts;
import Models.Clients;
import Models.Contracts;
import Models.Services;
import Models.Transactions;
import Services.AccountsService;
import Services.ClientsService;
import Services.ContractsService;
import Services.ServicesService;
import Services.TransactionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ServicesController {
    ServicesService servicesService = new ServicesService();
    AccountsService accountsService = new AccountsService();
    ContractsService contractsService = new ContractsService();

    @GetMapping("/listServices")
    public String listServicesPage(Model model) {
        List<Services> services = servicesService.readAllServices();
        model.addAttribute("services", services);
        return "servicesList";
    }
    @PostMapping("/servicesort1")
    public String listServicesPageByName(Model model) {
        List<Services> services = servicesService.readAllServicesByName();
        model.addAttribute("services", services);
        return "servicesList";
    }
    @PostMapping("/servicesort2")
    public String listServicesPageByType(Model model) {
        List<Services> services = servicesService.readAllServicesByType();
        model.addAttribute("services", services);
        return "servicesList";
    }

    @PostMapping("/serviceCreation")
    public String checkService (
            @RequestParam(name = "name") String name,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "desc") String desc,
            @RequestParam(name = "plan") String plan,
            Model model) {

        Services service = new Services(name, type, desc, plan);
        servicesService.createService(service);
        List<Services> services = servicesService.readAllServices();
        model.addAttribute("services", services);
        return "servicesList";
        //model.addAttribute("error", "Invalid operation");
        //return "pageERROR";


    }

    @GetMapping("/service")
    public String servicePage(@RequestParam(name = "service_id", required = true) int service_id, Model model) {
        Services service = servicesService.readServiceByID(service_id);
        model.addAttribute("service", service);
        model.addAttribute("prep_service_id", service.getService_id());
        return "service";
    }

    @PostMapping("/saveservice")
    public String saveBookEditPage(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "type") String type,
                                   @RequestParam(name = "desc") String desc,
                                   @RequestParam(name = "plan") String plan,
                                   @RequestParam(name = "service_id", required = true) int service_id, Model model) {
        Services service = servicesService.readServiceByID(service_id);

        service.setName(name);
        service.setType(type);
        service.setDescription(desc);
        service.setTariff_plan(plan);


        servicesService.updateService(service);

        Services check_service = servicesService.readServiceByID(service.getService_id());
        if(check_service == null) {
            model.addAttribute("error", "Service update error");
            return "pageERROR";
        }
        model.addAttribute("service", service);
        model.addAttribute("prep_service_id", service.getService_id());
        return "service";
    }

    @PostMapping("/findService")
    public String findPage(@RequestParam(name = "serid") int id, Model model) {
        Services service = servicesService.readServiceByID(id);
        if(service == null) {
            model.addAttribute("error", "Service doesn't exist");
            return "pageERROR";
        }
        model.addAttribute("service", service);
        model.addAttribute("prep_service_id", service.getService_id());
        return "service";
    }

}
