package Role;
import java.io.*;
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
        } catch (FileNotFoundException e){
            System.out.println("File not Found.");
        } catch (IOException e){
            System.out.println("Error in reading file.");
        }
    }
    public CustomerProduct createRecordFrom(String line){
        String[] parts = line.split(",\\s*");
        return new CustomerProduct(parts[0], parts[1], LocalDate.parse(parts[2]));
    }
    public ArrayList<CustomerProduct> returnAllRecords(){
        return this.records;
    }
    public boolean contains(String key){

    }
    public CustomerProduct getRecord(String key){
        for(CustomerProduct cp: this.records){
            if(cp.getCustomerSSN().equals(key)){
                return new CustomerProduct(cp.getCustomerSSN(), cp.getProductID(), cp.getPurchaseDate());
            }
        }
        return null;
    }
    public void insertRecord(CustomerProduct record){
        this.records.add(record);
    }
    public void deleteRecord(String key){
        CustomerProduct record = getRecord(key);
        this.records.remove(record);
    }
    public void saveToFile(){
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(this.fileName, false))){
            String numOfRecords = Integer.toString(this.records.size());
            wr.write(numOfRecords);
            wr.newLine();
            for (int i=0; i<this.records.size(); i++){
                String line = this.records.get(i).lineRepresentation();
                wr.write(line);
                wr.newLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("File Location error.");
        }catch (IOException e){
            System.out.println("Error in writing file.");
        }
    }
}
