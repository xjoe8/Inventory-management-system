/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author basma
 */
public class ProductDatabase {
    private String filename;
    private ArrayList<Product> records = new ArrayList<>();
    
    public ProductDatabase(String filename){
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<Product> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Product> records) {
        this.records = records;
    }
    
    
    public void readFromFile(){
        
        try (Scanner reader = new Scanner(new File(filename) )){
            int lineCount = 0;
            while( reader.hasNextLine() ){
                lineCount++;
                String line = reader.nextLine().trim();
                if(line.isEmpty()) continue;
                Product p = createRecordFrom(line);
                
                if(p != null){
                records.add(p);
            }else{
                    System.out.println("skipping invalid record at" + lineCount + ":" + line);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("error opening file");
        }
    }
    
    public Product createRecordFrom(String line){
         String[] s = line.split(",");
         if(s.length != 6){
             return null;
         }
         try{
         String productID = s[0].trim();
         String productName = s[1].trim();
         String manufacturerName = s[2].trim();
         String supplierName = s[3].trim();
         int q = Integer.parseInt(s[4].trim());
         float p = Float.parseFloat(s[5].trim());
         
         return new Product( productID, productName, manufacturerName , supplierName, q, p );
    }catch (NumberFormatException ex){
        return null;
    }
    
    }
    
    
    public ArrayList<Product> returnAllRecords(){
        return(records);
    }
    
    public boolean contains(String key ){
        for(Product p : records){
            if(p.getSearchkey().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
    
    public Product getRecord(String key){
        for(Product p : records){
            if( p.getSearchkey().equalsIgnoreCase(key)){
                return p;
            }
        }
        return null;
    }
    
    public void insertRecord(Product record){
        records.add(record);
        
    }
    
    public void deleteRecord(String key){
        Product delete = null;
        for(Product p : records){
            if( p.getSearchkey().equalsIgnoreCase(key)){
                delete = p;
                break;
            }
        }
        if(delete != null){
            records.remove(delete);
            System.out.println("product with id" + key + "is removed succesfully");
        }else
            System.out.println("error deleting record");
    }
    
    
    public void saveToFile(){
        try( PrintWriter writer = new PrintWriter(new FileWriter(filename, false))){
            for(Product p : records){
                writer.println(p.lineRepresentation());
            }
            System.out.println("data sucessfully saved");
        }catch(IOException e){
            System.out.println("error saving");
        }
    }
    
}
