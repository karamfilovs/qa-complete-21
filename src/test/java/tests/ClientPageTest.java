package tests;
import beans.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Defaults;


public class ClientPageTest extends BaseTest {
    @Test
    @DisplayName("Can search for existing client by full match")
    public void searchForExistingClientByFullMatch(){
        String clientToSearch = Defaults.generateRandomString();
        //Precondition is to have client to search for
        Client client = Client.builder()
                .firm_name(clientToSearch)
                .firm_town("Lovech")
                .firm_addr("Sofiq")
                .firm_bulstat("")
                .firm_is_reg_vat(false)
                .firm_mol("Gosho Petkov")
                .build();
        api.clientAPI().createClient(client);
        webApp.loginPage().defaultLogin();
        //Navigate to client page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Create client
        webApp.clientPage()
                .expandSearchSection()
                .enterNameToSearchFor(clientToSearch)
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(clientToSearch), "Client name was not found in the result table");
       api.clientAPI().deleteAllClients();
    }

    @Test
    @DisplayName("Can search for existing clients by partial match")
    public void canSearchForExistingClientByPartialMatch(){
        //Precondition is to have clients to search for
        String clientName1 = Defaults.generateRandomString();
        String clientName2 = Defaults.generateRandomString();

        Client client1 = Client.builder()
                .firm_name(clientName1)
                .firm_town("Montana")
                .firm_addr("Svoge")
                .firm_bulstat("")
                .firm_is_reg_vat(false)
                .firm_mol("Mitko Petkov")
                .build();
        Client client2 = Client.builder()
                .firm_name(clientName2+clientName1)
                .firm_town("Pleven")
                .firm_addr("Dobrich")
                .firm_bulstat("")
                .firm_is_reg_vat(false)
                .firm_mol("Stefan Jekov")
                .build();
        //Create two clients with partial string match
        api.clientAPI().createClient(client1);
        api.clientAPI().createClient(client2);
        webApp.loginPage().defaultLogin();
        //Navigate to client page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Create client
        webApp.clientPage()
                .expandSearchSection()
                .enterNameToSearchFor(clientName1)
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(clientName1), "Client name was not found in the result table");
        Assertions.assertTrue(tableInfo.contains(clientName2), "Client name was not found in the result table");
        api.clientAPI().deleteAllClents();
        webApp.clientPage().navigate();
        String emptyListMessage = webApp.clientPage().getEmptyListMessage();
        Assertions.assertEquals("Все още нямате добавени клиенти.", emptyListMessage);

    }
    @Test
    @DisplayName("Can search for not-existing client")
    public void canSearchForNotExistingClient(){
        webApp.loginPage().defaultLogin();
        //Navigate to client page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Create client
        webApp.clientPage()
                .expandSearchSection()
                .enterNameToSearchFor("Random client name")
                .clickSearchButton();
        //Check empty table list text
        Assertions.assertEquals("Не са намерени клиенти, отговарящи на зададените критерии.", webApp.clientPage().getEmptyListMessage());
    }
}
