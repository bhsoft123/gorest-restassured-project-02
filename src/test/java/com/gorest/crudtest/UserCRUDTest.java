package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.testBase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

public class UserCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "PrimeUser";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int id;

    @Test
    public void averifyUserCreatedSuccessfully() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(name);
        postsPojo.setEmail(email);
        postsPojo.setGender(gender);
        postsPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .when()
                .body(postsPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    //5967359
    @Test //read and assert
    public void bverifyUsercreateSuccessfully() {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response =  given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then().statusCode(200);

        HashMap<String, Object> studentMap = response.extract()
                .path(s1+name+s2);
        response.body(s1 +name+s2, hasValue(name));
        id = (int) studentMap.get("id");
        System.out.println(studentMap);
    }
    @Test
    public void cverifyUserUpdateSuccessfully() {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(name);
        postsPojo.setEmail(email);
        postsPojo.setGender(gender);
        postsPojo.setStatus("inactive");
        Response response = given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .body(postsPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response1 =  given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then().statusCode(200);

        HashMap<String, Object> studentMap = response1.extract()
                //    .path("findAll{it.firstName == 'firstName'}.get(0)"); // if write this and store in hasmap then will return complete list for
                //this firstname
                .path(s1+name+s2);
        response1.body(s1 +name+s2, hasValue(name));
        id = (int) studentMap.get("id");
        System.out.println(studentMap);
    }

    @Test
    public void dverifyUserDeleteSuccessfully() {
        given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer 8b34b9f18414d977f526f447951f03e48d2d8675cf9552d770b9ed590bd7620e")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(404);
    }

}