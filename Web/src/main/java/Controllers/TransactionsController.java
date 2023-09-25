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
public class TransactionsController {
    TransactionsService transactionsService = new TransactionsService();
    AccountsService accountsService = new AccountsService();
    ContractsService contractsService = new ContractsService();
    ClientsService clientsService = new ClientsService();

    @GetMapping("/addTransaction")
    public String addTransactionPage() {
        return "transactionAdd";
    }

    @PostMapping("/addTransaction")
    public String addTransactionPagePost() {
        return "transactionAdd";
    }

    @PostMapping("/addTransactionPlus")
    public String addTransactionPagePlus(@RequestParam(name = "id", required = false) int prep_account_id, Model model) {
        model.addAttribute("prep_account_id", prep_account_id);
        return "transactionAdd";
    }

    @PostMapping("/transactionCreation")
    public String checkTransaction (
                                         @RequestParam(name = "accid") int accid,
                                         @RequestParam(name = "conid") int conid,
                                         @RequestParam(name = "value") float value,
                                         Model model) {
        Accounts acc = accountsService.readAccountByID(accid);
        if(acc == null) {
            model.addAttribute("error", "Account doesn't exist");
            return "pageERROR";
        }
        Contracts con = contractsService.readContractByID(conid);
        if(con == null) {
            model.addAttribute("error", "Contract doesn't exist");
            return "pageERROR";
        }
        if(acc.getBalance() + acc.getMax_debt() < value){
            model.addAttribute("error", "Not enough money on the account");
            return "pageERROR";
        }
        acc.setBalance(acc.getBalance() - value);
        accountsService.updateAccount(acc);
        Transactions transaction = new Transactions(acc, con, value, new Timestamp(System.currentTimeMillis()));
        transactionsService.createTransaction(transaction);
        List<Transactions> transactions = transactionsService.readAllTransactions();
        model.addAttribute("transactions", transactions);
        return "index";
        //model.addAttribute("error", "Invalid operation");
        //return "pageERROR";


    }
    @GetMapping("/transactionsHistory")
    public String TranHisPage(@RequestParam(name = "account_id", required = true) int account_id, Model model) {
        Accounts account = accountsService.readAccountByID(account_id);
        Clients client = account.getClient_id();
        List<Transactions> transactions = transactionsService.readTransactionsListByAcc(account);
        model.addAttribute("client", client);
        model.addAttribute("transactions", transactions);
        model.addAttribute("prep_account_id", account_id);
        return "transactionsHistory";
    }
}
