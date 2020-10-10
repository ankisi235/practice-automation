package com.automationpractice.testScripts;

import com.automationpractice.pages.CartPage;
import com.automationpractice.pages.SignInPage;
import com.automationpractice.pages.ContactUsPage;
import com.automationpractice.pages.HomePage;

/**
 * This class contains the test use cases
 */
public class UseCases {

    /**
     * creating instance of the classes
     */
    HomePage homePage = new HomePage();
    ContactUsPage contactUsPage = new ContactUsPage();
    SignInPage signInPage = new SignInPage();
    CartPage cartPage = new CartPage();

    /**
     * declaring variables and assigning values for input
     */
    String subHeading = "Customer service";
    String OrdRef = "TEST123";
    String email = "test@test.com";
    String path = System.getProperty("user.dir")+"\\test.txt";
    String message = "This is the feedback";


    /**
     * Send a message to Customer Service in the ‘Contact us’ section
     * @return text of success message
     */
    public String sendMessageToCustomerService() {
        homePage.goToHomePage().selectContactUsLink();
        return contactUsPage.selectSubjectHeading(subHeading).enterEmail(email).enterOrderReference(OrdRef)
                .uploadFile(path)
                .enterMessage(message)
                .clickSend()
                .getSuccessMessage();
    }


    /**
     * Create an account using already registered email id test@test.com
     * @return text of validation error message
     */
    public String createAnAccountWithExistingEmail() {
        homePage.goToHomePage().selectSignIn();
        return signInPage.enterEmail(email).createAccount().getInvalidAccountMessage();
    }

    /**
     * searches, adds and deletes the same item from the cart
     *
     * @assumption cart does not have any item.
     * @param item name to be searched, added and deleted
     * @return text that cart is empty after adding and removing the item
     */
    public String addAndDeleteItemFromCart(String item){
        homePage.goToHomePage().searchAnItem(item).hoverOverTheProduct().addItemtoCart().selectProceedTOCheckout();
        return cartPage.deleteItemIncart(item).getEmptyCartText();
    }
}
