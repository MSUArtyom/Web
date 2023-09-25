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
public class ContractsController {
    ServicesService servicesService = new ServicesService();
    AccountsService accountsService = new AccountsService();
    ContractsService contractsService = new ContractsService();
    ClientsService clientsService = new ClientsService();

    @PostMapping("/addContract")
    public String addContractPage(@RequestParam(name = "id", required = false) int prep_service_id, Model model) {
        model.addAttribute("prep_service_id", prep_service_id);
        return "contractAdd";
    }

    @PostMapping("/addContractPlus")
    public String addContractPagePlus(@RequestParam(name = "id", required = false) int prep_account_id, Model model) {
        model.addAttribute("prep_account_id", prep_account_id);
        return "contractAdd";
    }

    @GetMapping("/addContract")
    public String addContractPageGet() {
        return "contractAdd";
    }

    @PostMapping("/contractCreation")
    public String checkTransaction (
            @RequestParam(name = "accid") int accid,
            @RequestParam(name = "serid") int serid,
            @RequestParam(name = "period") String period,
            Model model) {
        Accounts acc = accountsService.readAccountByID(accid);
        if(acc == null) {
            model.addAttribute("error", "Account doesn't exist");
            return "pageERROR";
        }
        Services ser = servicesService.readServiceByID(serid);
        if(ser == null) {
            model.addAttribute("error", "Service doesn't exist");
            return "pageERROR";
        }
        Contracts contract = new Contracts(acc, ser, period);
        contractsService.createContract(contract);
        List<Contracts> contracts = contractsService.readAllContracts();
        model.addAttribute("contracts", contracts);
        return "index";
        //model.addAttribute("error", "Invalid operation");
        //return "pageERROR";


    }
    @PostMapping("/contractsHistory")
    public String ConHisPage(@RequestParam(name = "client_id", required = true) int client_id, Model model) {
        Clients client = clientsService.readClientByID(client_id);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        List<Contracts> contracts = contractsService.readContractsListByAccList(accs);
        model.addAttribute("client", client);
        model.addAttribute("contracts", contracts);
        return "contractsHistory";
    }
}
