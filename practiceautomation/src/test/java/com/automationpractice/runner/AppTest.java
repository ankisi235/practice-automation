package com.automationpractice.runner;

import static com.automationpractice.utilities.ExtentReportNG.*;
import static com.automationpractice.utilities.PageUtils.*;

import com.automationpractice.testScripts.UseCases;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * this class is for test execution
 */
public class AppTest {

    // variables to validate test results
    String successMessage = "Your message has been successfully sent to our team.";
    String validationMessage = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    String searchKey = "Blouse";
    String emptyCartText = "Your shopping cart is empty.";


    UseCases useCases = new UseCases();

    /**
     * initializes driver and report
     */
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("Chrome") String browser) {
        initDriver(browser);
        initReport("Report");
    }

    /**
     * Sends a message to customer service and verifies the success response
     */
    @Test(priority = 1)
    public void sendMessageToCustomerService() {
        try {
            startTest("Send message to customer service", "To send message to Customer Service");
            Assert.assertEquals(useCases.sendMessageToCustomerService(),successMessage);
            logTest(LogStatus.PASS, "Message sent Successfully", "success_msg");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "Error in sending Message", "sending_error");
            endTest();
        }
    }

    /**
     * attempts to create an account using registered mail id and verifies validation error message
     */
    @Test (priority = 2)
    public void createAnAccountWithExistingEmail() {
        try {
            startTest("Create an account with a registered e-mail", "User should get a validation error on using registered email");
            Assert.assertEquals(useCases.createAnAccountWithExistingEmail(), validationMessage);
            logTest(LogStatus.PASS, "Validation message found", "validation_error");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "Validation error not found", "validation_failed");
            endTest();
        }
    }



    /**
     * Add an item to the cart and remove the same
     */
     @Test (priority = 3)
     public void addAndRemoveItemsFromCart() {
     try{
     startTest("Search,  Add and  Remove the  same item from the cart", "User should be able to add an item and remove the same item from the cart assuming that the cart is empty");
     Assert.assertEquals(useCases.addAndDeleteItemFromCart(searchKey), emptyCartText );
     logTest(LogStatus.PASS, "Item successfully searched, added and removed", "empty_cart");
     endTest();
     }
     catch (AssertionError e) {
     logTest(LogStatus.FAIL, "Failed to add and remove item", "add_remove_item_failed");
     endTest();
     }
     }

    /**
     * writes report, close driver, kills any instance of driver, opens report in browser
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        writeReport();
        closeDriver();
        killDriver();
        openReport();
    }
}
