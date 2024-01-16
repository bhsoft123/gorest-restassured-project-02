package com.gorest.testSuite;

import com.gorest.testBase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;
    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
        response.log().all();
    }

        //1. Verify the if the total record is 20
        @Test
        public void test001() {
            response.body("size()", equalTo(10));
        }

        //2. Verify the if the name of id = 5914154 is equal to ”Dandapaani Agarwal”
        @Test
        public void test002(){
            response.body("[1].name", equalTo("Avantika Kaur"));
        }

        //3. Check the single ‘Name’ in the Array list (Pres. Shresth Kakkar)
        @Test
        public void test003(){
            response.body("[2].name", equalTo("Dinesh Mehrotra"));
        }

        //4. Check the multiple ‘Names’ in the ArrayList (Anshula Joshi, Somu Pillai, Avantika Kaur)
        @Test
        public void test004(){
            response.body("name", hasItems("Asha Pandey","Vidya Nayar","Charuvrat Reddy"));   }

        //5. Verify the emai of userid = 5914153 is equal “pres_kakkar_shresth@kunze.example”
        @Test
        public void test005(){
            response.body("[2].email", equalTo("dinesh_mehrotra@oconner.example"));   }

        //6. Verify the status is “Active” of user name is “Somu Pillai”
        @Test
        public void test006(){
            response.body("[5].status", equalTo("inactive"));   }

        //7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
        @Test
        public void test007(){
            response.body("[6].gender",equalTo("male"));
        }

    }

