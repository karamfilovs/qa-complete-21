package tests;

import beans.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientPageTest extends BaseTest{

    @Test
    @DisplayName("Creating new client")

    public void canCreateNewClient(){

        Client client = Client.builder()
                .firm_name("Ява и Суматра-3 EАД")
                .firm_mol("Асен Колев")
                .firm_town("София")
                .firm_addr("Калабалък 16")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG122256789")
                .build();
        api.clientAPI().createClient(client);
        Assertions.assertTrue(api.clientAPI().getAllClientsNamesAsList().contains(client.getFirm_name()));
        api.clientAPI().deleteAllClients();
    }

    @Test
    @DisplayName("Deleting last created client")
    public void canDeleteLastCreatedClient(){
        Client client = Client.builder()
                .firm_name("Коста Рика-4 EАД")
                .firm_mol("Колю Будев")
                .firm_town("София")
                .firm_addr("Мур 6")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG123456789")
                .build();
        api.clientAPI().createClient(client);
        api.clientAPI().getClientsAndDeleteLastCreatedClient();
        Assertions.assertFalse(api.clientAPI().getAllClientsNamesAsList().contains(client.getFirm_name()));
    }

    @Test
    @DisplayName("Search for client by full matching name")
    public void searchForClientByNameFullMatch(){
        Client client = Client.builder()
                .firm_name("Мордовия АД")
                .firm_mol("Калин Минчев")
                .firm_town("София")
                .firm_addr("Фантазия 6")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG901234567")
                .build();
        api.clientAPI().createClient(client);
        webApp.loginPage().defaultLogin();
        webApp.clientPage().navigate();
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeader());
        webApp.clientPage().clickExpandSearchMenuButton();
        webApp.clientPage().enterFirmNameToSearchFor(client.getFirm_name());
        webApp.clientPage().clickSearchButton();
        Assertions.assertTrue(webApp.clientPage().getTextFromSearchResultsTable().contains(client.getFirm_name()),
                "No clients matching the search criteria");
        api.clientAPI().deleteAllClients();


    }

    @Test
    @DisplayName("Search for client by partial matching name")
    public void searchForClientByPartialNameMatch(){
        String textToSearchFor = "Гердан";
        Client client1 = Client.builder()
                .firm_name("Гердани и прежди-4 ООД")
                .firm_mol("Атанас Паргов")
                .firm_town("София")
                .firm_addr("Кокиче 6")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG901234567")
                .build();
        api.clientAPI().createClient(client1);
        Client client2 = Client.builder()
                .firm_name("Гердания-4 АД")
                .firm_mol("Момчил Яшев")
                .firm_town("Перник")
                .firm_addr("Резбарска 26")
                .firm_is_reg_vat(true)
                .firm_vat_number("BG901234455")
                .build();
        api.clientAPI().createClient(client2);
        webApp.loginPage().defaultLogin();
        webApp.clientPage().navigate();
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeader());
        webApp.clientPage().clickExpandSearchMenuButton();
        webApp.clientPage().enterFirmNameToSearchFor(textToSearchFor);
        webApp.clientPage().clickSearchButton();
        Assertions.assertTrue(webApp.clientPage().getTextFromSearchResultsTable().contains(textToSearchFor),
                "No clients matching the search criteria");
        api.clientAPI().deleteAllClients();
    }
}
