package com.uzimahealth.controller;

import com.uzimahealth.stock.Item;
import com.uzimahealth.TestSecurityConfig;
import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.ItemAlternative;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class ItemAlternativeControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    
    public void testGetAllItemAlternatives() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/item-alternatives")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    
    public void testGetAlternativesByItem() {
        // First create an item
        Item item = new Item();
        item.setItemCode("ITEM003");
        item.setItemName("Test Item 3");

        Item savedItem = given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/item-alternatives/item/" + savedItem.getId())
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    
    public void testCreateItemAlternative() {
        // First create items
        Item item1 = new Item();
        item1.setItemCode("ITEM001");
        item1.setItemName("Test Item 1");

        Item item2 = new Item();
        item2.setItemCode("ALT001");
        item2.setItemName("Alternative Item");

        Item savedItem1 = given()
            .contentType(ContentType.JSON)
            .body(item1)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);

        Item savedItem2 = given()
            .contentType(ContentType.JSON)
            .body(item2)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);

        ItemAlternative alternative = new ItemAlternative();
        alternative.setItem(savedItem1);
        alternative.setAlternativeItem(savedItem2);
        alternative.setIsDefault(true);

        given()
            .contentType(ContentType.JSON)
            .body(alternative)
        .when()
            .post("/api/item-alternatives")
        .then()
            .statusCode(200)
            .body("item.itemCode", equalTo("ITEM001"))
            .body("alternativeItem.itemCode", equalTo("ALT001"));
    }

    @Test
    
    public void testDeleteItemAlternative() {
        // First create items
        Item item1 = new Item();
        item1.setItemCode("ITEM002");
        item1.setItemName("Test Item 2");

        Item item2 = new Item();
        item2.setItemCode("ALT002");
        item2.setItemName("Alternative Item 2");

        Item savedItem1 = given()
            .contentType(ContentType.JSON)
            .body(item1)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);

        Item savedItem2 = given()
            .contentType(ContentType.JSON)
            .body(item2)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);

        ItemAlternative alternative = new ItemAlternative();
        alternative.setItem(savedItem1);
        alternative.setAlternativeItem(savedItem2);
        alternative.setIsDefault(false);

        ItemAlternative savedAlternative = given()
            .contentType(ContentType.JSON)
            .body(alternative)
        .when()
            .post("/api/item-alternatives")
        .then()
            .statusCode(200)
            .extract().as(ItemAlternative.class);

        // Then delete it
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete("/api/item-alternatives/" + savedAlternative.getId())
        .then()
            .statusCode(204);
    }
}