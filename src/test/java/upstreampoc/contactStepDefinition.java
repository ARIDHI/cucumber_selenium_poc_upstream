package upstreampoc;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.contactPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class contactStepDefinition {

    private WebDriver driver;
    private contactPage contact;


    @Before
    public void setup(){
        driver = new ChromeDriver();
    }
    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }




    @Given("I am on the contact page")
    public void i_am_on_the_contact_page() {
        driver.get("https://usptestqa.pages.dev/fake-contact");
        contact = new contactPage(driver);
    }

    @When("I add the forgotten user data")
    public void i_add_forgotten_user_data(){
        contact.errorBtn();
        contact.usernameInput(get_data_from_end_point().get(1));
    }

    @Given("I send a request to end point")
    public List<String> get_data_from_end_point() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user";

        Response response = RestAssured.given()
                .header("app-id", "624c9429450430b574dcf17c")
                .header("Content-Type", "application/json")
                .when()
                .get(RestAssured.baseURI)
                .then()
                .extract()
                .response();
        String title = response.jsonPath().getString("data[0].title");
        String firstName = response.jsonPath().getString("data[0].firstName");
        String lastName = response.jsonPath().getString("data[0].lastName");
        List<String> data = new ArrayList<>();
        data.add(title);
        data.add(firstName);
        data.add(lastName);
        return data ;
    }
    @When("I get response")
    public Response send_request_to_end_point() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1/user";
        Response response = RestAssured.given()
                .header("app-id", "624c9429450430b574dcf17c")
                .header("Content-Type", "application/json")
                .when()
                .get(RestAssured.baseURI)
                .then()
                .extract()
                .response();
        return response ;
    }
    @Then("I should have response status 200")
    public void get_response_status_code() {

        int statusCode = send_request_to_end_point().getStatusCode();
        assertEquals(200, statusCode);
    }
    @Then("I should have response body")
    public String get_response_body_as_string(){
        String responseBody = send_request_to_end_point().getBody().asString();
        Assert.assertNotNull(responseBody , "Response body is null.");
        return responseBody;
    }



    @Given("I have entered all valid data")
    public void i_have_entered_all_valid_data() {
        if(send_request_to_end_point().getStatusCode() == 200) {
        contact.selectGenre(get_data_from_end_point().get(0));
        contact.usernameInput(get_data_from_end_point().get(1));
        contact.lastnameInput(get_data_from_end_point().get(2));
        contact.mobileInput("0611111111");
        contact.titleInput(get_data_from_end_point().get(0));
        contact.companyInput("upStream pay");
        contact.MessageInput("employee");
        } else {
            throw new RuntimeException("Response body is null : no data .");
        }
    }
    @Then("I should see a validation message indicating {string}")
    public void i_should_see_a_validation_message_indicating(String success_message) {
        String text = driver.findElement(By.xpath("//*[@id='popin-message']")).getText();
        assertEquals( text , success_message);
    }



    @Given("I have forget the user genre data")
    public void i_have_entered_data_exclude_genre(){
        if(send_request_to_end_point().getStatusCode() == 200) {
            contact.usernameInput(get_data_from_end_point().get(1));
            contact.usernameInput(get_data_from_end_point().get(2));
            contact.mobileInput("0688558855");
            contact.selectGenre(get_data_from_end_point().get(0));
            contact.companyInput("upStream");
            contact.MessageInput("employee");
        } else {
            throw new RuntimeException("Response body is null : no data .");
        }
    }
    @Then("I should see an alert message indicating {string}")
    public void i_should_see_an_alert_message_indicating(String errorMessage) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals( alertText ,errorMessage);
    }

    @Given("I have forget required user data")
    public void i_have_entered_data_exclude_userName(){
        if(send_request_to_end_point().getStatusCode() == 200) {
            contact.selectGenre(get_data_from_end_point().get(0));
            contact.lastnameInput(get_data_from_end_point().get(1));
            contact.mobileInput("0655555522");
            contact.selectGenre(get_data_from_end_point().get(0));
            contact.companyInput("Upstream");
            contact.MessageInput("employee");
        } else {
            throw new RuntimeException("Response body is null : no data .");
        }
    }
    @Then("I should see an error message indicating {string}")
    public void i_should_see_an_error_message_indicating(String errorMessage) {
        String text = driver.findElement(By.xpath("//*[@id='popin-message']")).getText();
        assertEquals( text , errorMessage);
    }



    @Given("I have select invalid data")
    public void i_have_entered_data_exclude_message_text(){
        if(send_request_to_end_point().getStatusCode() == 200) {
            contact.titleInput(get_data_from_end_point().get(0));
        contact.usernameInput(get_data_from_end_point().get(1));
        contact.lastnameInput(get_data_from_end_point().get(2));
        contact.mobileInput("0611225522");
        contact.selectGenre(get_data_from_end_point().get(0));
        contact.companyInput("Upstream");
        } else {
            throw new RuntimeException("Response body is null : no data .");
        }
    }
    @Then("I should see an alert worning indicating {string}")
    public void i_should_see_an_worning_message_indicating(String Alert) {
        assertEquals( driver.findElement(By.xpath("//*[@id='popin-message']")).isDisplayed(), true);
    }

    
    @Given("I have entered data exclude company, mobile and title")
    public void i_have_entered_data_exclude_company_mobile_and_title(){
        if(send_request_to_end_point().getStatusCode() == 200) {
            contact.selectGenre(get_data_from_end_point().get(0));
            contact.usernameInput(get_data_from_end_point().get(1));
            contact.lastnameInput(get_data_from_end_point().get(2));
            contact.MessageInput("employee");
        } else {
            throw new RuntimeException("Response body is null : no data .");
        }
    }
    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        contact.sendBtn();
    }






}

