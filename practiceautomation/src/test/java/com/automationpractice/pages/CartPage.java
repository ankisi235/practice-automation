package com.automationpractice.pages;

import org.openqa.selenium.By;

import static com.automationpractice.utilities.PageUtils.clickElementInTable;
import static com.automationpractice.utilities.PageUtils.getText;

public class CartPage {

    /**
     * locators
     */
    By tblCartRow = By.xpath("//*[@id='cart_summary']/tbody/tr");
    By btnBin = By.className("cart_quantity_delete");
    By emptyCartAlert = By.cssSelector("p.alert.alert-warning");
    // By lblProductQuantity = By.id("summary_products_quantity");


    /**
     * Clicks on the bin icon in the cart against the passed item
     *
     * @param item name of the item
     * @return page objects
     */
    public CartPage deleteItemIncart(String item) {
        clickElementInTable(tblCartRow, item, btnBin);
        return this;
    }

    /**
     * get alert text when cart is empty
     *
     * @return text
     */
    public String getEmptyCartText() {
        return getText(emptyCartAlert);
    }

    /**
     * gets the count of the products in cart.
     *
     * @return count of product in the cart
     *
    public int getProductCount(){
    return Integer.parseInt(getText(find(lblProductQuantity)).substring(0,1));
    }*/
}
