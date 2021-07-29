package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Defaults;

public class HTTPClient {

    static {
        RestAssured.baseURI = Defaults.BASE_URL;
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic(Defaults.EMAIL, Defaults.PASSWORD);
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

    protected Response post(String body, String url) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all().body(body)
                .when()
                .post(url);
        response.prettyPrint();
        return response;
    }

}
