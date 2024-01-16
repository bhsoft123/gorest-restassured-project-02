package com.gorest.testSuite;

import com.gorest.testBase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001(){
        response.body("size()", equalTo(25));
}

    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
    //cohaero libero.”
    @Test
    public void test002(){
        response.body("[2].title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003(){
        response.body("user_id",hasItem(5914249));
    }

    //4. Check the multiple ids in the ArrayList (5914243, 5914206, 5914206)
    @Test
    public void test004(){
        response.body("user_id", hasItems(5914243, 5914206, 5914206));
    }

    //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
//Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
//vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
//tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
//acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
    public void test005(){
        response.body("[5].body", equalTo("Totidem aegrus perspiciatis. Sperno tepidus ocer. Volup arbustum minus. Aveho est victoria. Abundans coniecto temperantia. Cruentus laudantium creber. Conturbo saepe aut. Carmen acsi dolorem. Sodalitas coruscus sonitus. Tendo tabula sophismata. Antiquus contabesco aro. Carbo una clibanus. Candidus accipio curvo. Comitatus deinde color. Sed voluptatibus molestias. Cotidie cilicium voluntarius. Stips unde patrocinor."));
    }
}