package Parallel;

import com.Pages.ContactUsPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ContactUsSteps {

    private ContactUsPage contactUsPage = new ContactUsPage(DriverFactory.getDriver());

    @Given("user navigate to contact us page")
    public void user_navigate_to_contact_us_page() {
        DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=contact");
    }

    @When("user fills the form from given sheetName {string} and rowNumber {int}")
    public void user_fills_the_form_from_given_sheetName_and_rowNumber(String sheetName, Integer rowNumber) {
        ExcelReader reader = new ExcelReader();

        List<Map<String, String>> testData;
        try {
            testData = reader.getData("C:\\Users\\arnob\\OneDrive\\Documents\\Excel Files\\For Cucumber.xlsx", sheetName);
        } catch (InvalidFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        String heading = testData.get(rowNumber).get("subjectheading");
        String email = testData.get(rowNumber).get("email");
        String message = testData.get(rowNumber).get("message");

        contactUsPage.fillContactUsFrom(heading,email,message);
    }

    @When("user clicks on send button")
    public void user_clicks_on_send_button() {
        contactUsPage.clickSend();
    }

    @Then("it shows a successful message {string}")
    public void it_shows_a_successful_message(String expectedSuccessMsg) {
        String actualSuccessMsg = contactUsPage.getSuccessMsg();
        Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg);
    }

}
