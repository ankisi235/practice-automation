package com.automationpractice.pages;

import static com.automationpractice.utilities.PageUtils.*;
import org.openqa.selenium.By;

/**
 * Page Object encapsulates the Contact Us page
 */
public class ContactUsPage {

    /**
     * locators
     */
    By ddlSubjectHeading = By.id("id_contact");
    By txtEmail = By.id("email");
    By txtOrderReference = By.id("id_order");
    By upload = By.id("fileUpload");
    By txtMessage = By.id("message");
    By btnSend = By.id("submitMessage");
    By successMessage = By.className("alert-success");


    /**
     * Selects subject heading from the dropdown
     *
     * @param heading: subject to be selected
     * @return page objects
     */
    public ContactUsPage selectSubjectHeading(String heading) {
        selectFromDropdown(find(ddlSubjectHeading), heading);
        return this;
    }

    /**
     * Enters email in the textbox
     *
     * @param email email to be entered
     * @return page objects
     */
    public ContactUsPage enterEmail(String email) {
        input(find(txtEmail), (email));
        return this;
    }

    /**
     * Enters order reference in the textbox
     *
     * @param ordRef order reference to be entered
     * @return page objects
     */
    public ContactUsPage enterOrderReference(String ordRef) {
        input(find(txtOrderReference), ordRef);
        return this;
    }

    /**
     * uploads attachment
     *
     * @param path path of the file which has to be uploaded
     * @return page objects
     */
    public ContactUsPage uploadFile(String path) {
        input(find(upload), (path));
        return this;
    }

    /**
     * Enters message in the text area
     *
     * @param text : message to be entered
     * @return page objects
     */
    public ContactUsPage enterMessage(String text) {
        input(find(txtMessage), text);
        return this;
    }

    /**
     * clicks send button
     *
     * @return page objects
     */
    public ContactUsPage clickSend() {
        click(find(btnSend));
        return this;
    }

    /**
     * gets text from success message after sending the message
     *
     * @return  text of the success message
     */
    public String getSuccessMessage(){
       return getText(successMessage);
    }
}
