You are tasked with creating a simple Inventory Management System for a store. This system should manage products and their stock levels, supporting operations to add, remove, and update products.

1. Encapsulation:
   - Create a class Product with the following private attributes:
     - productId (String)
     - productName (String)
     - quantity (int)
     - price (double)
   - Provide appropriate getter and setter methods for these attributes.

2. Inheritance:
   - Create two subclasses of Product:
     - PerishableProduct (which has an additional attribute expirationDate).
     - NonPerishableProduct (which has an additional attribute shelfLife).

3. Polymorphism:
   - Implement a method updateStock(int amount) in both subclasses:
     - In PerishableProduct, ensure that the quantity does not exceed a certain maximum limit (e.g., 100).
     - In NonPerishableProduct, allow updating the stock without restrictions.

4. Abstraction:
   - Create an abstract class InventoryOperation with the following abstract methods:
     - addProduct(Product product)
     - removeProduct(String productId)
   - Implement these methods in an Inventory class that maintains a collection of Product objects.

5. Error Handling (try-catch):
   - In the updateStock() method, handle the following exceptions:
     - Create a custom exception InsufficientStockException when trying to remove more products than are available.
     - Create another custom exception InvalidQuantityException for when a negative quantity is provided.
   - Use try-catch blocks in the main method to demonstrate handling these exceptions when performing stock updates.

6. Main Class:
   - Include a main method where:
     - Create instances of PerishableProduct and NonPerishableProduct.
     - Perform add and remove operations, demonstrating both successful transactions and exception handling.
Case 1: Adding a Perishable product
PerishableProduct apple = new PerishableProduct("P001", "Apple", 50, 0.5, "2024-12-31"); inventory.addProduct(apple);

Expected Output
Product added: Apple with quantity 50

Case 2: Adding a Non-Perishable Product
NonPerishableProduct rice = new NonPerishableProduct("NP001", "Rice", 200, 1.0, 365); inventory.addProduct(rice);

Expected Output
Product added: Rice with quantity 200



Case 3: Updating Stock of a Perishable Product (Within Limit)
apple.updateStock(30);

Expected Output
Stock updated: Apple new quantity is 80

Case 4: Updating Stock of a Perishable Product (Exceeds Limit)
try {
    apple.updateStock(30); // Total would be 110
} catch (Exception e) {
    System.out.println(e.getMessage());
}


Expected Output
Cannot exceed maximum quantity of 100 for perishable products.

Case 5: Removing Products Successfully
try {
    inventory.removeProduct("P001");
} catch (InsufficientStockException e) {
    System.out.println(e.getMessage());
}

Expected Output
Product P001 removed successfully.



Case 6: Removing More than Available Stock
try {
    apple.updateStock(60); // Attempting to remove more than available
} catch (InsufficientStockException e) {
    System.out.println(e.getMessage());
}

Expected Output
Insufficient stock for product Apple.


Case 7: Invalid Quantity Update
try {
    apple.updateStock(-10);
} catch (InvalidQuantityException e) {
    System.out.println(e.getMessage());
}

Expected Output
Quantity cannot be negative.
nobuddy
Jerhon Clyde Gabutan
sorry ma kuyapan kog sayu kay naghunahuna naku sa "how to kill myself 101"
