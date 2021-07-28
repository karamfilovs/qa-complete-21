package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Constants;

public class HTTPClient {

    static {
        RestAssured.baseURI = Constants.BASE_URL;
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic(Constants.EMAIL, Constants.PASSWORD);
    }

    protected Response get(String url){
       Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(url);
       response.prettyPrint();
       return response;
    }

    protected Response delete(String url){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(url);
        response.prettyPrint();
        return response;
    }

}
