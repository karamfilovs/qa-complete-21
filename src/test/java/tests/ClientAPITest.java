package tests;

import api.ClientAPI;
import org.junit.jupiter.api.Test;

public class ClientAPITest extends BaseTest {

    @Test
    public void createClient() {
        ClientAPI clientAPI = new ClientAPI();
        clientAPI.createNewClientWithValidCredentials("Test Firm", "Sofia", "Sofia", "Pesho", "0");
    }

    @Test
    public void canCreateClientWithMissingCredentials() {
        ClientAPI clientAPI = new ClientAPI();
        clientAPI.createNewClientWithInvalidCredentials("Test firm", "Sofia", "Sofia", "Pesho");
    }
}

