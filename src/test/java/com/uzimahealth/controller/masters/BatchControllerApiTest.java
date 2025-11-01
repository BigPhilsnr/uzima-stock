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
        Batch batch = new Batch();
        batch.setBatchId("BATCH001");
        batch.setItemCode("ITEM001");
        batch.setSupplier("Test Supplier");

        given()
            .contentType(ContentType.JSON)
            .body(batch)
        .when()
            .post("/api/batches")
        .then()
            .statusCode(200)
            .body("batchId", equalTo("BATCH001"))
            .body("itemCode", equalTo("ITEM001"));
    }

    @Test
    
    public void testGetBatchById() {
        // First create a batch
        Batch batch = new Batch();
        batch.setBatchId("BATCH002");
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
            .get("/api/batches/BATCH002")
        .then()
            .statusCode(200)
            .body("batchId", equalTo("BATCH002"));
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
        SerialNo serialNo = new SerialNo();
        serialNo.setSerialNo("SERIAL001");
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
            .body("serialNo", equalTo("SERIAL001"));
    }

    @Test
    
    public void testGetSerialNumber() {
        // First create a serial number
        SerialNo serialNo = new SerialNo();
        serialNo.setSerialNo("SERIAL002");
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
            .get("/api/batches/serials/SERIAL002")
        .then()
            .statusCode(200)
            .body("serialNo", equalTo("SERIAL002"));
    }
}