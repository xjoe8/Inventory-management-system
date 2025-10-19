package lab4;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");

        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();

    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, double price) {
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        this.productsDatabase.insertRecord(product);
        this.ProductDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
        return this.productsDatabase.returnAllRecords().toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return this.customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        boolean found = this.productsDatabase.contains(productID);
        if (!found) {
            System.out.println("Error : product with ID " + productID + " is not found.");
            return false;
        }
        else {
            Product pp = this.productsDatabase.getRecord(productID);
            if ( pp.getQuantity() <= 0 ) {
                System.out.println("Error : product with ID " + productID + " is sold out.");
                return false;
            }
            else {
                pp.setQuantity(pp.getQuantity()-1);
                CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
                this.customerProductDatabase.insertRecord(cp);
                this.productsDatabase.saveToFile();
                this.customerProductDatabase.saveToFile();
                return true;
            }
        }
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println("Error : Return date is earlier than the purchase date.");
            return -1;
        }
        boolean found = this.productsDatabase.contains(productID);
        if (!found) {
            System.out.println("Error : product with ID " + productID + " is not found.");
            return -1;
        }
        String key = customerSSN + "," + productID + "," + purchaseDate; // me7taga asta5dem line representation
        found = this.customerProductDatabase.contains(key);
        if (!found) {
            System.out.println("Error : purchase with key : " + key + " is not found.");
            return -1;
        }
        int daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if ( daysBetween > 14 ) {
            System.out.println("Error : More than 14 days have passed since the purchase date.");
            retrun -1;
        }
        Product returnedProduct = this.productsDatabase.getRecord(productID);
        returnedProduct.setQuantity(returnedProduct.getQuantity()+1);
        this.customerProductDatabase.deleteRecord(key);
        this.productsDatabase.saveToFile();
        this.customerProductDatabase.saveToFile();
        return returnedProduct.price;
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        CustomerProduct[] purchases = getListOfPurchasingOperations();
        boolean found = false;
        for ( CustomerProduct element : purchases ) {
            if ( element.customerSSN.equals(customerSSN) && element.purchaseDate.equals(purchaseDate) ) {
                found = true;
                if(element.isPaid()) {
                    break;
                }
                else {
                    element.setPaid(true);
                    this.customerProductDatabase.saveToFile();
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Error : Purchase not found.");
            return false;
        }
        return true;
    }

    public void logout() {
        this.productsDatabase.saveToFile();
        this.customerProductDatabase.saveToFile();
    }
}
