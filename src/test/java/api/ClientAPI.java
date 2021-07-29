package api;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ClientAPI extends HTTPClient {
    private static final String CLIENTS_URL = "/clients";
    private static final String CLIENT_URL = "/client";

    /**
     * Get request for all clients
     * @return Json body
     */
    public Response getAllClients() {
        return get(CLIENTS_URL);
    }

    /**
     * Delete single client
     * @param id id of the client
     * @return Successful message for the deletion
     */
    public Response deleteClient(String id) {
        return delete(CLIENT_URL + "/" + id);
    }

    /**
     * Delete all clients
     */
    public void deleteAllClients() {
        //Get all clients
        Response response = getAllClients();
        //List with ids of the clients
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        //Delete clients one by one from the list
        clientsToBeDeleted.stream().distinct().forEach(id -> deleteClient(String.valueOf(id)));
    }

    public void createNewClientWithValidCredentials(String clientName, String town, String address, String owner, String isVatRegistered) {
        //Create Json body
        JsonObject clientsToBeAdded = new JsonObject();
        clientsToBeAdded.addProperty("firm_name", clientName);
        clientsToBeAdded.addProperty("firm_town", town);
        clientsToBeAdded.addProperty("firm_addr", address);
        clientsToBeAdded.addProperty("firm_mol", owner);
        clientsToBeAdded.addProperty("firm_is_reg_vat", isVatRegistered);
        //POST request to server
        post(clientsToBeAdded.toString(), CLIENT_URL);
        Integer successStatus = post(clientsToBeAdded.toString(), CLIENT_URL).getStatusCode();
        Assertions.assertEquals(200, successStatus);
        //Delete client
        deleteAllClients();
    }

    public  void createNewClientWithInvalidCredentials(String clientName, String town, String address, String owner) {
        JsonObject clientsToBeAdded = new JsonObject();
        clientsToBeAdded.addProperty("firm_name", clientName);
        clientsToBeAdded.addProperty("firm_town", town);
        clientsToBeAdded.addProperty("firm_addr", address);
        clientsToBeAdded.addProperty("firm_mol", owner);
        post(clientsToBeAdded.toString(), CLIENT_URL);
        Integer errorStatus = post(clientsToBeAdded.toString(), CLIENT_URL).getStatusCode();
        Assertions.assertEquals(400, errorStatus);
    }

}