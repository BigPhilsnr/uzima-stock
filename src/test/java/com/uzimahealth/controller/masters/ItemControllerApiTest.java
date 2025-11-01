package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.ItemGroup;
import com.uzimahealth.stock.UOM;
import com.uzimahealth.TestSecurityConfig;
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
public class ItemControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testGetAllItems() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/items")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testCreateItem() {
        Item item = new Item();
        item.setItemCode("TEST001");
        item.setItemName("Test Item");
        item.setDescription("Test Description");

        given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .body("itemCode", equalTo("TEST001"))
            .body("itemName", equalTo("Test Item"));
    }

    @Test
    public void testGetItemByCode() {
        // First create an item
        Item item = new Item();
        item.setItemCode("TEST002");
        item.setItemName("Test Item 2");

        given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200);

        // Then get it
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/items/TEST002")
        .then()
            .statusCode(200)
            .body("itemCode", equalTo("TEST002"));
    }

    @Test
    public void testGetAllItemGroups() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/items/groups")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testCreateItemGroup() {
        ItemGroup itemGroup = new ItemGroup();
        itemGroup.setItemGroupName("TESTGRP");
        itemGroup.setDescription("Test Group");

        given()
            .contentType(ContentType.JSON)
            .body(itemGroup)
        .when()
            .post("/api/items/groups")
        .then()
            .statusCode(200)
            .body("itemGroupName", equalTo("TESTGRP"));
    }

    @Test
    public void testGetAllUOMs() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/items/uoms")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testCreateUOM() {
        UOM uom = new UOM();
        uom.setUomName("TESTUOM");
        uom.setMustBeWholeNumber(false);

        given()
            .contentType(ContentType.JSON)
            .body(uom)
        .when()
            .post("/api/items/uoms")
        .then()
            .statusCode(200)
            .body("uomName", equalTo("TESTUOM"));
    }
}