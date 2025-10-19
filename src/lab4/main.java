package lab4;
import java.util.Scanner;
public class main{
    static public void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Inventory Management System");
        System.out.println("Choose from:");
        System.out.println("1- Admin");
        System.out.println("2- Employees");
        int choice1 = scan.nextInt();
        while(choice1 !=1&& choice1 !=2){
            System.out.println("Invalid Choice. Please Try Again");
            choice1 = scan.nextInt();
        }
        if (choice1 ==1){

        }else{
            System.out.println("Enter Employee ID:");
            String employeeID = scan.nextLine();
            EmployeeUserDatabase employees = new EmployeeUserDatabase("Employees.txt");
            while (!(employees.contains(employeeID))){
                System.out.println("Invalid Employee ID. Please try again.");
                employeeID = scan.nextLine();
            }
            System.out.println("Please Choose from the following options:");
            System.out.println("1- Add new products");
            System.out.println("2- ");
        }
    }
}