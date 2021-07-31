package api;

import beans.Client;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ClientAPI extends HTTPClient {
    private static final String CLIENTS_URL = "/clients";
    private static final String CLIENT_URL = "/client";

    /**
     * Get request
     * @return all clients
     */
    public Response getALlClients() {
        return get(CLIENTS_URL);
    }

    /**
     * Get request
     * @param id client id
     * @return response
     */
    public Response getClient(String id) {
        return get(CLIENT_URL + "/" + id);
    }

    /**
     * Delete single client request
     * @param id client id
     * @return response
     */
    public Response deleteClient(String id) {
        return delete(CLIENT_URL + "/" + id);
    }

    /**
     * Delete all clients
     */
    public void deleteAllClients() {
        //Get all clients
        Response response = getALlClients();
        //List with ids of the clients
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        //Delete clients one by one from the list
        clientsToBeDeleted.stream().distinct().forEach(id -> deleteClient(String.valueOf(id)));
    }

    /**
     * Create a client as a firm
     * @param body of the client as a firm
     * @return response
     */
    public Response createClientAsFirm(Client body) {
        return post(CLIENT_URL, GSON.toJson(body));
    }

}
