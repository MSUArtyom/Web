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
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class WebTest {
    String URL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;
    AccountsService accountsService = new AccountsService();
    ClientsService clientsService = new ClientsService();
    ContractsService contractsService = new ContractsService();
    ServicesService servicesService = new ServicesService();
    TransactionsService transactionsService = new TransactionsService();

    Clients new_client = new Clients("test", "test", "test", "test");
    Accounts new_account = new Accounts(new_client, (float) 1000, (float) 1000, 12);
    Services new_service = new Services("test", "test", "test", "test");
    Contracts new_contract = new Contracts(new_account, new_service, "test");



    @BeforeClass
    public void settings() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        driver = new ChromeDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        clientsService.createClient(new_client);
        accountsService.createAccount(new_account);
        servicesService.createService(new_service);
        contractsService.createContract(new_contract);

    }

    @Test(priority=1)
    public void indexRootPageButtonTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page");
        button.click();
        wait.until(stalenessOf(button));

        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page");

    }

    @Test(priority=2)
    public void TransactionCorrectTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("transaction_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        Assert.assertEquals(driver.getTitle(), "Add Transaction");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id()));
        driver.findElement(By.id("validationContractId")).sendKeys(String.valueOf(new_contract.getContract_id()));
        driver.findElement(By.id("validationTransactionValue")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page");
        Assert.assertFalse(transactionsService.readTransactionsListByAcc(new_account).isEmpty());
        transactionsService.deleteTransaction(transactionsService.readTransactionsListByAcc(new_account).get(0));
    }

    @Test(priority=3)
    public void TransactionFailTestNotEnoughMoney() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("transaction_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        Assert.assertEquals(driver.getTitle(), "Add Transaction");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id()));
        driver.findElement(By.id("validationContractId")).sendKeys(String.valueOf(new_contract.getContract_id()));
        driver.findElement(By.id("validationTransactionValue")).sendKeys(String.valueOf((float)99000));
        wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());
    }

    @Test(priority=4)
    public void TransactionFailTestNoSuchContract() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("transaction_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        Assert.assertEquals(driver.getTitle(), "Add Transaction");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id()));
        driver.findElement(By.id("validationContractId")).sendKeys(String.valueOf(new_contract.getContract_id() + 1));
        driver.findElement(By.id("validationTransactionValue")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());
    }

    @Test(priority=5)
    public void TransactionFailTestNoSuchAccount() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("transaction_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        Assert.assertEquals(driver.getTitle(), "Add Transaction");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id() + 1));
        driver.findElement(By.id("validationContractId")).sendKeys(String.valueOf(new_contract.getContract_id()));
        driver.findElement(By.id("validationTransactionValue")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_transaction_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
        Assert.assertTrue(transactionsService.readTransactionsListByAcc(new_account).isEmpty());
    }

    @Test(priority=6)
    public void ContractCorrectTest() {
        contractsService.deleteContract(new_contract);
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("contract_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        Assert.assertEquals(driver.getTitle(), "Add Contract");
        Assert.assertTrue(contractsService.readContractsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id()));
        driver.findElement(By.id("validationServiceId")).sendKeys(String.valueOf(new_service.getService_id()));
        driver.findElement(By.id("validationPeriod")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("index_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Main Page");
        Assert.assertFalse(contractsService.readContractsListByAcc(new_account).isEmpty());
        contractsService.deleteContract(contractsService.readContractsListByAcc(new_account).get(0));
    }

    @Test(priority=7)
    public void ContractFailTestNoSuchService() {
        contractsService.deleteContract(new_contract);
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("contract_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        Assert.assertEquals(driver.getTitle(), "Add Contract");
        Assert.assertTrue(contractsService.readContractsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id()));
        driver.findElement(By.id("validationServiceId")).sendKeys(String.valueOf(new_service.getService_id() + 1));
        driver.findElement(By.id("validationPeriod")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
        Assert.assertTrue(contractsService.readContractsListByAcc(new_account).isEmpty());

    }

    @Test(priority=7)
    public void ContractFailTestNoSuchAccount() {
        contractsService.deleteContract(new_contract);
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.id("contract_page_button")));
        button.click();
        wait.until(stalenessOf(button));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        Assert.assertEquals(driver.getTitle(), "Add Contract");
        Assert.assertTrue(contractsService.readContractsListByAcc(new_account).isEmpty());

        driver.findElement(By.id("validationAccountId")).sendKeys(String.valueOf(new_account.getAccount_id() + 1));
        driver.findElement(By.id("validationServiceId")).sendKeys(String.valueOf(new_service.getService_id()));
        driver.findElement(By.id("validationPeriod")).sendKeys(String.valueOf((float)0));
        wait.until(visibilityOfElementLocated(By.id("add_contract_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("page_error_root_page_button")));
        Assert.assertEquals(driver.getTitle(), "Page Error");
        Assert.assertTrue(contractsService.readContractsListByAcc(new_account).isEmpty());
    }

    @Test(priority=8)
    public void ClientAdjustCorrect() {
        driver.get(URL + "client?client_id=" + String.valueOf(new_client.getClient_id()));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("save_edit_client_button")));
        Assert.assertEquals(driver.getTitle(), "Client Page");
        Assert.assertEquals(new_client.getName(), "test");

        driver.findElement(By.id("inputTitle")).sendKeys("test");
        wait.until(visibilityOfElementLocated(By.id("save_edit_client_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("save_edit_client_button")));
        Assert.assertEquals(driver.getTitle(), "Client Page");
        new_client = clientsService.readClientByID(new_client.getClient_id());
        Assert.assertEquals(new_client.getName(), "testtest");
    }

    @Test(priority=9)
    public void ServiceAdjustCorrect() {
        driver.get(URL + "service?service_id=" + String.valueOf(new_service.getService_id()));

        WebElement button2 = wait.until(visibilityOfElementLocated(By.id("save_edit_service_button")));
        Assert.assertEquals(driver.getTitle(), "Service edit");
        Assert.assertEquals(new_service.getName(), "test");

        driver.findElement(By.id("inputTitle")).sendKeys("test");
        wait.until(visibilityOfElementLocated(By.id("save_edit_service_button")));
        button2.click();
        wait.until(visibilityOfElementLocated(By.id("save_edit_service_button")));
        Assert.assertEquals(driver.getTitle(), "Service edit");
        new_service = servicesService.readServiceByID(new_service.getService_id());
        Assert.assertEquals(new_service.getName(), "testtest");
    }

    @AfterClass
    public void end() {
        servicesService.deleteServiceForever(new_service);
        accountsService.deleteAccount(new_account);
        clientsService.deleteClientForever(new_client);
        driver.quit();
    }
}