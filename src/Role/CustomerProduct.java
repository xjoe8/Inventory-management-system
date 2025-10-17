package Role;
import java.time.LocalDate;
public class CustomerProduct {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
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
    public String lineRepresentation(){
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate + "," + this.paid;
    }
    public Boolean isPaid() {
        return paid;
    }
    public String getSearchKey(){

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
