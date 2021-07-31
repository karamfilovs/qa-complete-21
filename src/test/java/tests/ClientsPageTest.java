package tests;

import beans.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientsPageTest extends BaseTest{

    @Test
    @DisplayName("Can search for client by full match")
    public void canSearchForClientByFullMatch() {
        String clientToSearch = "Test client";
        Client client = Client.builder()
                .firm_name(clientToSearch)
                .firm_addr("Studentski grad")
                .firm_town("Sofia")
                .firm_is_reg_vat(false)
                .firm_mol("Test Mol")
                .build();
        api.clientAPI().createClientAsFirm(client);
        webApp.loginPage().defaultLogin();
        webApp.clientPage().navigate();
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        webApp.clientPage()
                .clickSearchExpandButton()
                .enterNameToSearch(clientToSearch)
                .clickSearchButton();
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(clientToSearch), "Client name was not found in the result table");
        api.clientAPI().deleteAllClients();
    }

    @Test
    @DisplayName("Can search for a client by partial match")
    public void canSearchForClientByPartialMatch() {
        Client client1 = Client.builder()
                .firm_name("Test firm")
                .firm_addr("Strudentski")
                .firm_town("Sofia")
                .firm_is_reg_vat(false)
                .firm_mol("Test Mol1")
                .build();
        Client client2 = Client.builder()
                .firm_name("Testova firma")
                .firm_addr("Geo Milev")
                .firm_town("Plovdiv")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG131313333")
                .firm_mol("Test Mol2")
                .build();
        api.clientAPI().createClientAsFirm(client1);
        api.clientAPI().createClientAsFirm(client2);
        webApp.loginPage().defaultLogin();
        webApp.clientPage().navigate();
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        webApp.clientPage()
                .clickSearchExpandButton()
                .enterNameToSearch("Te")
                .clickSearchButton();
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains("Test firm"), "Client name was not found in the result table");
        Assertions.assertTrue(tableInfo.contains("Testova firma"), "Client name was not found in the result table");
        api.clientAPI().deleteAllClients();
        webApp.clientPage().navigate();
        String emptyTableText = webApp.clientPage().getEmptyListMessage();
        Assertions.assertEquals("Все още нямате добавени клиенти.", emptyTableText);
    }

    @Test
    @DisplayName("Can search for not-existing clients")
    public void canSearchForNotExistingClients() {
        Client client = Client.builder()
                .firm_name("Test")
                .firm_town("Peshtera")
                .firm_addr("ul. Peshterska")
                .firm_is_reg_vat(false)
                .firm_mol("Pesho")
                .build();
        api.clientAPI().createClientAsFirm(client);
        webApp.loginPage().defaultLogin();
        webApp.clientPage().navigate();
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        webApp.clientPage()
                .clickSearchExpandButton()
                .enterNameToSearch("Pesho")
                .clickSearchButton();
        String tableResult = webApp.clientPage().getEmptyListMessage();
        Assertions.assertEquals("Не са намерени клиенти, отговарящи на зададените критерии.", tableResult);
        api.clientAPI().deleteAllClients();
    }

}
