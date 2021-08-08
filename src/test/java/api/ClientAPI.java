package api;

import beans.Client;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ClientAPI extends HTTPClient {
    private static final String CLIENTS_URL = "/clients";
    private static final String CLIENT_URL = "/client";

    public Response getAllClients() {
        return get(CLIENTS_URL);
    }

    public Response getClient(String id) {
        return get(CLIENT_URL + "/" + id);
    }

    public Response deleteClient(String id) {
        return delete(CLIENT_URL + "/" + id);
    }

    public void deleteAllClents() {
        //Get all clients
        Response response = getAllClients();
        //List with the ids of the clients to be deleted
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        if (clientsToBeDeleted.isEmpty()) {
            System.out.println("Sorry dude, your list is empty");
        }
        clientsToBeDeleted.forEach(i -> System.out.println(i));
        //Delete clients one by one from the list
        clientsToBeDeleted.stream().distinct().forEach(id -> deleteClient(String.valueOf(id)));
    }
    /**
     * Creates new client via API
     * @param body of the client
     * @return response
     */
    public Response createClient(Client body) {
        return post(CLIENT_URL, GSON.toJson(body));
    }


    /**
     * Updates existing client
     * @param id of the client to be updated
     * @param body body for the new client
     * @return response
     */
    public Response updateClient(Client id, Client body){
        return put(CLIENT_URL + "/" + id, GSON.toJson(body));
    }
    public void deleteAllClients() {
        //Get all clients
        Response response = getAllClients();
        //List with the ids of the clients to be deleted
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        if (clientsToBeDeleted.isEmpty()) {
            System.out.println("Sorry dude, your list is empty");
        }
        clientsToBeDeleted.forEach(i -> System.out.println(i));
        //Delete clients one by one from the list
        clientsToBeDeleted.stream().distinct().forEach(id -> deleteClient(String.valueOf(id)));
    }


}
