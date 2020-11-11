Scenario: Create new purchase order
Given there is some pet in the store
When the user wants to buy pet so he makes purchase order
Then purchase order is created

Scenario: Delete purchase order
Given there is some pet in the store
And the user has purchase order
When the user deletes purchase order
Then purchase order was deleted

Scenario: Verify that pet status exists in inventory
Given there is some pet in the store
When the user finds pet inventories
Then pet status exists in inventory