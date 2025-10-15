package Role;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records = new ArrayList<CustomerProduct>();
    private String fileName;
    public CustomerProductDatabase(String fileName){
        this.fileName = fileName;
    }
    public void readFromFile(){
        try(BufferedReader rd = new BufferedReader(new FileReader(this.fileName))){
            String line;
            int numOfRecords = Integer.parseInt(rd.readLine());
            for (int i= 0; i<numOfRecords; i++){
                line = rd.readLine();
                String[] parts = line.split(",\\s*");
                String customerSSN = parts[0];
                String productID = parts[1];
                LocalDate purchaseDate = LocalDate.parse(parts[2]);
                CustomerProduct record = new CustomerProduct(customerSSN, productID, purchaseDate);
                record.setPaid(Boolean.parseBoolean(parts[3]));
                this.records.add(record);
            }
            rd.close();
        }catch (Exception e){
            System.out.println("Error in reading file.");
        }
    }
    public CustomerProduct getRecord(String key){
        for(CustomerProduct cp: this.records){
            if(cp.getCustomerSSN().equals(key)){
                return new CustomerProduct(cp.getCustomerSSN(), cp.getProductID(), cp.getPurchaseDate());
            }
        }
        return null;
    }
    public ArrayList<CustomerProduct> returnAllRecords(){

    }
    public CustomerProduct createRecordFrom(String line){
        String[] parts = line.split(",\\s*");
        return new CustomerProduct(parts[0], parts[1], LocalDate.parse(parts[2]));
    }
    public void deleteRecord(String key){
        CustomerProduct record = getRecord(key);
        this.records.remove(record);
    }
    public void insertRecord(CustomerProduct record){
        this.records.add(record);
    }
    public void saveToFile(){

    }
}
