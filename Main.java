import java.util.*;

class Product {
    private String productID;
    private String productName;
    private int quantity;
    private double price;
    
    public Product(String productID, String productName, int quantity, double price){
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    //setters
    public void setProductID(String productID){
        this.productID = productID;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setPrice (double price){
        this.price = price;
    }
    //getters
    public String getProductID(){
        return this.productID;
    }

    public String getProductName(){
        return this.productName;
    }

    public int getQuantity (){
        return this.quantity;
    }

    public double getPrice(){
        return this.price;
    }
}

class PerishableProduct extends Product{
    private String expirationDate;

    public PerishableProduct(String productID, String productName, int quantity, double price, String expirationDate){
        super(productID,productName,quantity,price);
        this.expirationDate = expirationDate;
    }

    public void updateStock(int amount) throws InvalidQuantityException{
        if(getQuantity() + amount > 100){
            System.out.println("Maximum limit exceeded.");
        }else if(amount<0){
            throw new InvalidQuantityException("Cannot enter negative number.");
        }
        else{
            setQuantity(getQuantity() + amount);
            System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
        }
    }
    public void removeStack(int amount)throws InvalidQuantityException{
      if(amount > getQuantity()){
         System.out.println("Impossible to remove product");      
      }else if(amount < 0){
         throw new InvalidQuantityException("Cannot enter negative number.");
      }else{
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
    }
}

class NonPerishableProduct extends Product{
    private int shelfLife;

    public NonPerishableProduct(String productID, String productName, int quantity, double price, int shelfLife){
        super(productID,productName,quantity,price);
        this.shelfLife = shelfLife;
    }

    public void updateStock(int amount) throws InvalidQuantityException{
        if(amount < 0){
            throw new InvalidQuantityException("Cannot enter negative number. ");
        }else{
            setQuantity(amount + getQuantity());
            System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
        }
    }
    public void removeStack(int amount)throws InvalidQuantityException{
      if(amount > getQuantity()){
         System.out.println("Impossible to remove product");      
      }else if(amount < 0){
         throw new InvalidQuantityException("Cannot enter negative number.");
      }else{
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
    }
}

 abstract class InventoryOperation{
    public abstract void addProduct(Product product);
    public abstract void removeProduct(String product) throws InsufficientStockException;
}

class Inventory extends InventoryOperation{
    ArrayList<Product> prod = new ArrayList<>();
    
    public void addProduct(Product product){
        prod.add(product);
        System.out.println("Product added " + product.getProductName() + " with quantity " + product.getQuantity());
    }

    public void removeProduct(String productID) throws InsufficientStockException{
        boolean notFound = true;
        for(int i = 0; i < prod.size(); i++){
            if(prod.get(i).getProductID().equals(productID)){
                prod.remove(i);
                System.out.println("Product " + productID + " removed successfully.");
                notFound = false;
                break;
            }
        }

        if(notFound){
            throw new InsufficientStockException("Cannot find the ID");
        }
    }
}

class InsufficientStockException extends Exception{
    public InsufficientStockException(String message){
        super(message);
    }
}


class InvalidQuantityException extends Exception{
    public InvalidQuantityException(String message){
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Case 1: Adding a Perishable Product
        PerishableProduct apple = new PerishableProduct("P001", "Apple", 50, 0.5, "2024-12-31");
        inventory.addProduct(apple);

        // Case 2: Adding a Non-Perishable Product
        NonPerishableProduct rice = new NonPerishableProduct("NP001", "Rice", 200, 1.0, 365);
        inventory.addProduct(rice);

        // Case 3: Updating Stock of a Perishable Product (Within Limit)
        try {
            apple.updateStock(30);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Case 4: Updating Stock of a Perishable Product (Exceeds Limit)
        try {
            apple.updateStock(30); // Total would be 110, exceeds limit
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Case 5: Removing Products Successfully
        try {
            inventory.removeProduct("P001");
        } catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }

        // Case 6: Removing More than Available Stock
        try {
            apple.removeStack(1000); // Attempting to remove more than available
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Case 7: Invalid Quantity Update
        try {
            apple.updateStock(-10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
