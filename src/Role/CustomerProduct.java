package lab4;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Storable {
    
    String customerSSN;
    private String productID;
    LocalDate purchaseDate;
    private Boolean paid;
    
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String getCustomerSSN() {
        return customerSSN;
    }
    public String getProductID() {
        return productID;
    }
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public Boolean isPaid() {
        return paid;
    }
    
    
    @Override
    public String lineRepresentation(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter) + "," + this.paid;
    }
    @Override
    public String getSearchKey() {
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.toString();
    }
    
    
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
