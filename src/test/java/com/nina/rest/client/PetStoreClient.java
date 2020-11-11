package com.nina.rest.client;

import com.nina.config.Config;
import com.nina.models.Pet;
import com.nina.models.PurchaseOrder;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PetStoreClient {

    public PurchaseOrder createPurchaseOrder(long petId) {
        PurchaseOrder order = new PurchaseOrder();
        order.setPetId(petId);
        order.setQuantity(1);
        order.setStatus("placed");

        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .body(order)
                .contentType(JSON)
                .log().body().log().method().log().parameters()
                .when()
                .post(Config.CREATE_PURCHASE_ORDER)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(PurchaseOrder.class);
    }

    public PurchaseOrder findPurchaseOrder(long orderId) {
        return findPurchaseOrder(orderId, HttpStatus.SC_OK).as(PurchaseOrder.class);
    }

    public Response findPurchaseOrder(long orderId, int statusCode) {
        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .pathParam("orderId", orderId)
                .contentType(JSON)
                .when()
                .get(Config.PURCHASE_ORDER_BY_ID)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
    }

    public void deletePurchaseOrder(long orderID) {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .pathParam("orderId", orderID)
                .contentType(JSON)
                .when()
                .delete(Config.PURCHASE_ORDER_BY_ID)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    public Pet createPet(Pet pet) {
        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .body(pet)
                .contentType(JSON)
                .when()
                .post(Config.CREATE_PET)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(Pet.class);
    }

    public Map<String, String> findInventory() {
        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .contentType(JSON)
                .when()
                .get(Config.GET_STORE_INVENTORY)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(HashMap.class);
    }
}
