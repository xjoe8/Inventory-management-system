package Role;
import java.time.LocalDate;
public class Customer_Product {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private Boolean paid;
    public String getCustomerSSN() {
        return customerSSN;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public Boolean isPaid() {
        return paid;
    }
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
