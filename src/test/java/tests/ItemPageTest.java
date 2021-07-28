package tests;

import api.ItemAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemPageTest extends BaseTest {

    @Test
    @DisplayName("Can create item")
    public void canGetAllItems() {
        ItemAPI itemAPI = new ItemAPI();
        //Login in the system
        loginPage.defaultLogin();
        //Navigate to items page
        itemPage.navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Артикули", itemPage.getHeadline());
        //Create item
        itemPage.createItem("My dummy item 34", "5");
        //Verify item creation is successful
        Assertions.assertEquals("Артикулът е добавен успешно.", itemPage.getSuccessMessage());
        //Delete all items
        itemAPI.deleteAllItems();
    }

}
