package com.automationpractice.pages;

import static com.automationpractice.utilities.PageUtils.*;

import org.openqa.selenium.By;

/**
 * Page Object encapsulates the Home page
 */
public class HomePage {

    public final String homeUrl = "http://automationpractice.com/index.php";

    /**
     * locators
     */
    By btnContactUs = By.id("contact-link");
    By btnSignIn = By.cssSelector("a.login");
    By txtSearchBox = By.id("search_query_top");
    By btnSearchSubmit = By.name("submit_search");
    By imgProduct = By.className("product_img_link");
    By btnAddToCart = By.className("ajax_add_to_cart_button");
    //By cartWindow = By.id("layer_cart");
    By btnProceedToCheckout = By.cssSelector("[title*='Proceed to checkout']");

    /**
     * launches home page and maximizes the browser
     *
     * @return page object
     */
    public HomePage goToHomePage() {
        launchURL(homeUrl, "HomePage");
        return this;
    }

    /**
     * clicks on contact us link
     */
    public void selectContactUsLink() {
        click(find(btnContactUs));
    }

    /**
     * clicks on sign in link
     */
    public void selectSignIn() {
        click(find(btnSignIn));
    }

    /**
     * Searches an item using the searchbox
     *
     * @param item String to be searched
     * @return page objects
     */
    public HomePage searchAnItem(String item) {
        input(find(txtSearchBox), item);
        click(find(btnSearchSubmit));
        return this;
    }

    public HomePage hoverOverTheProduct(){
        mouseHover(find(imgProduct));
        return this;
    }

    /**
     * clicks on the first add to cart button
     *
     * @return page objects
     */
    public HomePage addItemtoCart() {
        click(find(btnAddToCart));
        return this;
    }

    /**
     * clicks on proceed to checkout button
     */
    public void selectProceedTOCheckout() {
        click(find(btnProceedToCheckout));
    }
}
