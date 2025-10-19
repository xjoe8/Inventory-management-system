package lab4;

import java.io.*;
import java.util.*;

public class AdminRole {
    
    private EmployeeUserDatabase database;
    
    //Constructor   //To initialize the admin’s access to the employee database.
    public AdminRole() throws IOException{
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }
    
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber){ // adds a new employee to the file named Employees.txt
        EmployeeUser employee= new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(employee);
        database.saveToFile();
    }
    
    public EmployeeUser[] getListOfEmployees(){ //Return all employees as an array
        ArrayList<EmployeeUser> list = database.returnAllRecords();
        EmployeeUser[] employeesArray= list.toArray(new EmployeeUser[list.size()]); //Converting the arraylist into an array (the needed)
        return employeesArray;
    }
    
    public void removeEmployee(String key){ //Delete the employee whose ID equals key.
        database.deleteRecord(key);
        database.saveToFile();
    }
    
    public void logout(){
        database.saveToFile();
    }
}
