package com.gorest.testSuite;

import com.gorest.testBase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

//1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
//2. Extract the total number of record
    @Test
    public void test002() {
        int number = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + number);
        System.out.println("------------------End of Test---------------------------");
    }
//3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("findAll{it.id}.body[5]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
//4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> allUserid = response.extract().path("findAll.user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + allUserid);
        System.out.println("------------------End of Test---------------------------");
    }
//5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> alltitles = response.extract().path("findAll.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + alltitles);
        System.out.println("------------------End of Test---------------------------");
    }
//6. Extract the title of all records whose user_id = 5914184
    @Test
    public void test006(){
        List<String> alltitles = response.extract().path("findAll{it.user_id == 5914184}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + alltitles);
        System.out.println("------------------End of Test---------------------------");
    }

//7. Extract the body of all records whose id = 93942
@Test
public void test007(){
    List<String> body = response.extract().path("findAll{it.id == 93942}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value is : " + body);
    System.out.println("------------------End of Test---------------------------");
}

}
