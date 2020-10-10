package com.automationpractice.pages;

import static com.automationpractice.utilities.PageUtils.*;
import org.openqa.selenium.By;

/**
 * Page Object encapsulates the Sign-in page
 */
public class SignInPage {

    /**
     * locators
     */
    By txtCreateEmail = By.id("email_create");
    By btnCreateAccount = By.id("SubmitCreate");
    By lblErrorMessage = By.id("create_account_error");


    /**
     * enters email to start registration
     *
     * @param email
     * @return page objects
     */
    public SignInPage enterEmail(String email) {
        input(find(txtCreateEmail), email);
        return this;
    }

    /**
     * clicks on create account button and wait
     *
     * @return page objects
     */
    public SignInPage createAccount() {
        click(find(btnCreateAccount));
        return this;
    }

    /**
     *  gets validation error message for email field
     *
     * @return Validation message text
     */
    public String getInvalidAccountMessage() {
        return getText(lblErrorMessage);
    }

}
