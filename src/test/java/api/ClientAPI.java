package api;

import beans.Client;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class ClientAPI extends HTTPClient{

    private static final String CLIENTS_URL = "/clients";
    private static final String CLIENT_URL = "/client";

    public Response deleteClient(String id){
        return delete(CLIENT_URL + "/" + id);
    }

    public Response createClient(Client body){
        return post(CLIENT_URL, GSON.toJson(body));
    }

    public Response getAllClients(){
        return get(CLIENTS_URL);
    }
    public ArrayList<String> getAllClientsNamesAsList(){
        Response response = get(CLIENTS_URL);
        return JsonPath.read(response.body().asString(), "$..firm_name");
    }

    public void deleteAllClients(){
        Response response = getAllClients();
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        clientsToBeDeleted.forEach(System.out::println);
        clientsToBeDeleted.stream().distinct().forEach(client -> deleteClient(String.valueOf(client)));
    }

    public void getClientsAndDeleteLastCreatedClient(){
        Response response = getAllClients();
        List<Integer> clientsIDList = JsonPath.read(response.body().asString(), "$..id");
        if (!clientsIDList.isEmpty()) {
            int lastClientID = clientsIDList.get(0);
            for (int client : clientsIDList) {
                if (client > lastClientID) {
                    lastClientID = client;
                }
            }
            deleteClient(String.valueOf(lastClientID));
        } else {
            System.out.println("No clients in the list");
        }
    }
}
