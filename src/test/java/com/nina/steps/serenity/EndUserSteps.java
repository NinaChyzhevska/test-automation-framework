package com.nina.steps.serenity;

import com.nina.EnvironmentPropertyLoader;
import com.nina.config.Config;
import com.nina.models.Category;
import com.nina.models.Pet;
import com.nina.models.PurchaseOrder;
import com.nina.rest.client.PetStoreClient;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EndUserSteps {

    private final PetStoreClient petStoreClient;

    private Pet savedDog;
    private PurchaseOrder order;
    private Map<String, String> inventories;

    public EndUserSteps() {
        this.petStoreClient = new PetStoreClient();
    }

    @Step
    public void create_dog() {
        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Pet dog = new Pet();
        dog.setCategory(category);
        dog.setName("Gav");
        dog.setPhotoUrls(Collections.singletonList("someUrl"));
        dog.setStatus(Config.PET_STATUS);

        savedDog = petStoreClient.createPet(dog);
    }

    @Step
    public void create_purchase_order() {
        order = petStoreClient.createPurchaseOrder(savedDog.getId());

    }

    @Step
    public void verify_purchase_order_created() {
        PurchaseOrder savedOrder = petStoreClient.findPurchaseOrder(order.getId());
        assertEquals(order.getPetId(),savedOrder.getPetId());
        assertEquals(order.getQuantity(),savedOrder.getQuantity());
        assertEquals(order.getStatus(),savedOrder.getStatus());
        assertEquals(order.isComplete(),savedOrder.isComplete());
    }

    @Step
    public void delete_purchase_order() {
        petStoreClient.deletePurchaseOrder(order.getId());
    }

    @Step
    public void verify_purchase_order_not_exist() {
        petStoreClient.findPurchaseOrder(order.getId(), HttpStatus.SC_NOT_FOUND);
    }

    @Step
    public void get_list_of_pet_inventories(){
        inventories = petStoreClient.findInventory();
    }

    @Step
    public void verify_that_pet_status_exists_in_inventory() {
        assertTrue(inventories.containsKey(Config.PET_STATUS));
    }

    public void getUserName() {
        String userName = EnvironmentPropertyLoader.getProperty("userName");
        System.out.println(userName);
    }
}