package Parallel;

import com.Pages.AccountPage;
import com.Pages.LoginPage;
import com.qa.factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class AccountPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private AccountPage accountPage;

    @Given("user has already logged in to application")
    public void user_has_already_logged_in_to_application(DataTable credTable) {
        List<Map<String, String>> credList = credTable.asMaps();

        String userName = credList.get(0).get("username");
        String passWord = credList.get(0).get("password");

        DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        accountPage = loginPage.doLogin(userName, passWord);
    }

    @Given("user is on Account page")
    public void user_is_on_account_page() {
        String title = accountPage.getAccountPageTitle();
        System.out.println("Account page title is: " + title);
    }

    @Then("user gets accounts section")
    public void user_gets_accounts_section(DataTable sectionsTable) {
        List<String> expAccountSecList = sectionsTable.asList();
        System.out.println("Expected account section list: " + expAccountSecList);

        List<String> actualAccountSecList = accountPage.getAccountSectionList();
        System.out.println("Actual account section list: " + actualAccountSecList);

        Assert.assertTrue(expAccountSecList.containsAll(actualAccountSecList));
    }

    @Then("accounts section count should be {int}")
    public void accounts_section_count_should_be(Integer expectedSectionCount) {
       Assert.assertTrue(accountPage.getAccountSectionCount() == expectedSectionCount);
    }
}