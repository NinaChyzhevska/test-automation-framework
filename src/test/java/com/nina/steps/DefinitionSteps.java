package com.nina.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.nina.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @Steps
    EndUserSteps endUser;

    @Given("there is some pet in the store")
    public void thereIsSomePetInTheStore() {
        endUser.create_dog();
    }

    @When("the user wants to buy pet so he makes purchase order")
    public void theUserWantsToBuyPetSoHeMakesPurchaseOrder() {
        endUser.create_purchase_order();
    }

    @Then("purchase order is created")
    public void purchaseOrderIsCreated() {
        endUser.verify_purchase_order_created();
    }

    @Given("the user has purchase order")
    public void theUserHasPurchaseOrder() {
        endUser.create_purchase_order();
    }

    @When("the user deletes purchase order")
    public void theUserDeletesPurchaseOrder() {
        endUser.delete_purchase_order();
    }

    @Then("purchase order was deleted")
    public void purchaseOrderWasDeleted() {
        endUser.verify_purchase_order_not_exist();
    }

    @When("the user finds pet inventories")
    public void theUserFindsPetInventories() {
        endUser.get_list_of_pet_inventories();
    }

    @Then("pet status exists in inventory")
    public void petStatusExistsInInventory() {
        endUser.verify_that_pet_status_exists_in_inventory();
    }
}
