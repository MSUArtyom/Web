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
public class ClientsController {
    ServicesService servicesService = new ServicesService();
    AccountsService accountsService = new AccountsService();
    ClientsService clientsService = new ClientsService();

    @GetMapping("/listClients")
    public String listClientsPage(Model model) {
        List<Clients> clients = clientsService.readAllClients();
        model.addAttribute("clients", clients);
        return "clientsList";
    }

    @PostMapping("/clientCreation")
    public String checkClient (
            @RequestParam(name = "name") String name,
            @RequestParam(name = "mail") String mail,
            @RequestParam(name = "phn") String phn,
            @RequestParam(name = "add") String add,
            Model model) {

        Clients client = new Clients(name, mail, phn, add);
        clientsService.createClient(client);
        List<Clients> clients = clientsService.readAllClients();
        model.addAttribute("clients", clients);
        return "clientsList";
        //model.addAttribute("error", "Invalid operation");
        //return "pageERROR";


    }

    @PostMapping("/accountCreation")
    public String checkAccount (
            @RequestParam(name = "cliid") int cliid,
            @RequestParam(name = "bal") float bal,
            @RequestParam(name = "md") float md,
            @RequestParam(name = "mt") int mt,
            Model model) {
        Clients client = clientsService.readClientByID(cliid);
        Accounts account = new Accounts(client, bal, md, mt);
        accountsService.createAccount(account);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        model.addAttribute("client", client);
        model.addAttribute("accounts", accs);
        return "client";
        //model.addAttribute("error", "Invalid operation");
        //return "pageERROR";


    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "client_id", required = true) int client_id, Model model) {
        Clients client = clientsService.readClientByID(client_id);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        model.addAttribute("client", client);
        model.addAttribute("accounts", accs);
        return "client";
    }

    @PostMapping("/client")
    public String clientPagePost(@RequestParam(name = "client_id", required = true) int client_id, Model model) {
        Clients client = clientsService.readClientByID(client_id);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        model.addAttribute("client", client);
        model.addAttribute("accounts", accs);
        return "client";
    }

    @PostMapping("/saveclient")
    public String saveBookEditPage(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "mail") String mail,
                                   @RequestParam(name = "phn") String phn,
                                   @RequestParam(name = "add") String add,
                                   @RequestParam(name = "client_id", required = true) int client_id, Model model) {

        Clients client = clientsService.readClientByID(client_id);
        client.setName(name);
        client.setEmails(mail);
        client.setPhone_numbers(phn);
        client.setAddresses(add);


        clientsService.updateClient(client);

        Clients check_client = clientsService.readClientByID(client.getClient_id());
        if(check_client == null) {
            model.addAttribute("error", "Client update error");
            return "pageERROR";
        }
        model.addAttribute("client", client);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        model.addAttribute("accounts", accs);
        return "client";
    }

    @PostMapping("/findClient")
    public String findPage(@RequestParam(name = "cliid", required = true) int id, Model model) {

        Clients client = clientsService.readClientByID(id);

        if(client == null) {
            model.addAttribute("error", "Client doesn't exist");
            return "pageERROR";
        }
        model.addAttribute("client", client);
        List<Accounts> accs = accountsService.readAccountsListByClientId(client);
        model.addAttribute("accounts", accs);
        return "client";
    }

}
