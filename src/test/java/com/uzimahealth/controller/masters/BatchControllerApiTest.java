package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Batch;
import com.uzimahealth.stock.SerialNo;
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
public class BatchControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testGetAllBatches() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/batches")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testCreateBatch() {
        String uniqueBatchId = "BATCH" + System.currentTimeMillis();
        
        // First create an item
        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setItemName("Test Item");
        
        Item savedItem = given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);
        
        Batch batch = new Batch();
        batch.setBatchId(uniqueBatchId);
        batch.setItem(savedItem);
        batch.setItemCode("ITEM001");
        batch.setSupplier("Test Supplier");

        given()
            .contentType(ContentType.JSON)
            .body(batch)
        .when()
            .post("/api/batches")
        .then()
            .statusCode(200)
            .body("batchId", equalTo(uniqueBatchId))
            .body("itemCode", equalTo("ITEM001"));
    }

    @Test
    
    public void testGetBatchById() {
        String uniqueBatchId = "BATCH" + System.currentTimeMillis();
        
        // First create an item
        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setItemName("Test Item");
        
        Item savedItem = given()
            .contentType(ContentType.JSON)
            .body(item)
        .when()
            .post("/api/items")
        .then()
            .statusCode(200)
            .extract().as(Item.class);
        
        // First create a batch
        Batch batch = new Batch();
        batch.setBatchId(uniqueBatchId);
        batch.setItem(savedItem);
        batch.setItemCode("ITEM001");

        given()
            .contentType(ContentType.JSON)
            .body(batch)
        .when()
            .post("/api/batches")
        .then()
            .statusCode(200);

        // Then get it
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/batches/" + uniqueBatchId)
        .then()
            .statusCode(200)
            .body("batchId", equalTo(uniqueBatchId));
    }

    @Test
    
    public void testGetBatchesByItem() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/batches/item/ITEM001")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    
    public void testGetAllSerialNumbers() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/batches/serials")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    
    public void testCreateSerialNumber() {
        String uniqueSerialNo = "SERIAL" + System.currentTimeMillis();
        SerialNo serialNo = new SerialNo();
        serialNo.setSerialNo(uniqueSerialNo);
        serialNo.setItemCode("ITEM001");
        serialNo.setBatchNo("BATCH001");
        serialNo.setStatus("Active");

        given()
            .contentType(ContentType.JSON)
            .body(serialNo)
        .when()
            .post("/api/batches/serials")
        .then()
            .statusCode(200)
            .body("serialNo", equalTo(uniqueSerialNo));
    }

    @Test
    
    public void testGetSerialNumber() {
        String uniqueSerialNo = "SERIAL" + System.currentTimeMillis();
        // First create a serial number
        SerialNo serialNo = new SerialNo();
        serialNo.setSerialNo(uniqueSerialNo);
        serialNo.setItemCode("ITEM001");

        given()
            .contentType(ContentType.JSON)
            .body(serialNo)
        .when()
            .post("/api/batches/serials")
        .then()
            .statusCode(200);

        // Then get it
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/batches/serials/" + uniqueSerialNo)
        .then()
            .statusCode(200)
            .body("serialNo", equalTo(uniqueSerialNo));
    }
}