/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee;

/**
 *
 * @author User
 */
public class EmployeeUser {

    private String employeeId, name, email, address, phoneNumber;

    //Constructor
    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber){
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation(){ //returns the data of the employee comma separated
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    public String getSearchKey(){  //returns the employee id
        return this.employeeId;
    }
}

