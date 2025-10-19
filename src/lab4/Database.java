package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database<T extends Storable> {
    
    protected String filename;
    protected ArrayList<T> records = new ArrayList<>();
    
    public Database(String filename){
            this.filename= filename;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<T> getRecords() { 
        return records; 
    }
    
    public void setRecords(ArrayList<T> records) { 
        this.records = records; 
    }
    
    public void readFromFile(){
        
        try (Scanner reader = new Scanner(new File(filename) )){
            int lineCount = 0; 
            while( reader.hasNextLine() ){
                lineCount++;
                String line = reader.nextLine().trim();
                if(line.isEmpty())
                    continue;
                T record = createRecordFrom(line);
                
                if(record != null){
                records.add(record);
                }
                else{
                    System.out.println("Skipping invalid record at " + lineCount + ": " + line);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("Error : file not found.");
        }
    }
    
    public abstract T createRecordFrom(String line);
    
    public ArrayList<T> returnAllRecords(){
        return records;
    }
    
    public boolean contains(String key ){
        for(T record : records){
            if(record.getSearchKey().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
    
    public T getRecord(String key){
        for(T record : records){
            if( record.getSearchKey().equalsIgnoreCase(key)){
                return record;
            }
        }
        return null;
    }
    
    public void insertRecord(T record){
        records.add(record);
    }
    
    public void deleteRecord(String key){
        T delete = null;
        for(T record : records){
            if( record.getSearchKey().equalsIgnoreCase(key)){
                delete = record;
                break;
            }
        }
        if(delete != null){
            records.remove(delete);
            System.out.println("Product with ID " + key + " was removed successfully.");
        }
        else
            System.out.println("Error deleting record");
    }
    
    
    public void saveToFile(){
        try( PrintWriter writer = new PrintWriter(new FileWriter(filename, false))){
            for(T record : records){
                writer.println(record.lineRepresentation());
            }
            System.out.println("Data sucessfully saved");
        }
        catch(IOException e){
            System.out.println("Error saving");
        }
    }
}
