/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author User
 */
public class EmployeeUserDatabase {
        
    private ArrayList<EmployeeUser> records;
    private String filename;
    
    //Constructor
    public EmployeeUserDatabase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    
    public void readFromFile() throws IOException{
        try (BufferedReader reader= new BufferedReader(new FileReader(filename));){
            records.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                EmployeeUser employee= createRecordFrom(line);
                // Add it to the list "records"
                records.add(employee);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeUserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeUserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public EmployeeUser createRecordFrom(String line){  //converts line into EmployeeUser
        StringTokenizer st = new StringTokenizer(line, ",");

        // Extract data in order
        String employeeId = st.nextToken();
        String name = st.nextToken();
        String email = st.nextToken();
        String address = st.nextToken();
        String phoneNumber = st.nextToken();

        // Create an EmployeeUser object
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);

        return employee;
    }
    
    public ArrayList<EmployeeUser> returnAllRecords(){  //to be used in other classes
        return records;
    }
    
    public boolean contains(String key){    //Checks if an employee with a specific employeeId exists in records
        for (EmployeeUser emp : records){
            if (emp.getSearchKey().equals(key))
                return true;
        }
        return false;
    }
    
    public EmployeeUser getRecord(String key){  //Finds and returns the EmployeeUser whose employeeId matches the given key.
        for (EmployeeUser emp : records){
            if (emp.getSearchKey().equals(key))
                return emp;
        }
        return null;
    }
    
    public void insertRecord(EmployeeUser record){  //Adds a new employee object to the records list.
        records.add(record);
    }
    
    public void deleteRecord(String key){   //Removes the employee with id = key from the records list.
        for (EmployeeUser emp : records){
            if (emp.getSearchKey().equals(key)){
                records.remove(emp);
                break;
            }
        }
    }
    
    public void saveToFile(){
        try (FileWriter writer= new FileWriter(filename)){
            for (EmployeeUser emp : records){
                writer.write(emp.lineRepresentation() + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeUserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeUserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
