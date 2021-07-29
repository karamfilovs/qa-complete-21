package tests;

import api.ClientAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientsPageTest extends BaseTest {

    @Test
    @DisplayName("Successful create and delete client")
    public void createAndDeleteNewCLient() {
        ClientAPI clientAPI = new ClientAPI();
        //Login into the system
        loginPage.defaultLogin();
        //Navigate to clients page
        clientPage.navigate();
        //Verify that you are on the correct page
        Assertions.assertEquals("Клиенти", clientPage.getHeadline());
        //Create test client
        clientPage.createFirmWithValidCredentials("Test Client2", "Test Address3", "Sofia");
        //Verify that your client is created successful
        Assertions.assertEquals("Клиентът е добавен успешно.", clientPage.getSuccessMessage());
        //Delete all created clients
        clientAPI.deleteAllClients();
    }

    @Test
    @DisplayName("Can create client with missing town")
    public void canCreateClientWithMissingTownField() {
        //Login into the system
        loginPage.defaultLogin();
        //Navigate to clients page
        clientPage.navigate();
        //Verify that you are at the correct page
        Assertions.assertEquals("Клиенти", clientPage.getHeadline());
        //Try to create client with empty town field
        clientPage.createFirmWithMissingTown("Test", "Test Address");
        //Verify that you get the correct message
        Assertions.assertEquals("Моля, въведете град на фирмата.", clientPage.getErrorMessage());
    }

    @Test
    @DisplayName("Can create person with valid credentials")
    public void canCreatePersonWithValidCredentials() {
        ClientAPI clientAPI = new ClientAPI();
        loginPage.defaultLogin();
        clientPage.navigate();
        Assertions.assertEquals("Клиенти", clientPage.getHeadline());
        clientPage.createPersonWithValidCredentials("Pesho", "Sofia", "Sofia");
        Assertions.assertEquals("Клиентът е добавен успешно.", clientPage.getSuccessMessage());
        clientAPI.deleteAllClients();
    }


}
