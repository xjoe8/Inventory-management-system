package lab4;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Lab4 {
    private static String readValid(Scanner input, String prompt, String regex, String errorMsg) {
    String value;
    while (true) {
        System.out.print(prompt);
        value = input.nextLine().trim();
        if (value.matches(regex)) {
            return value;
        } else {
            System.out.println("Error: " + errorMsg);
        }
    }
}

    
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("====================================");
        System.out.println(" Welcome to the Inventory System ");
        System.out.println("====================================");
        System.out.println("1. Admin");
        System.out.println("2. Employee");
        System.out.print("Select your role: ");
        int roleChoice = input.nextInt();
        input.nextLine(); // consume newline

        if (roleChoice == 1) {
            // ---------- ADMIN ROLE ----------
            AdminRole admin = new AdminRole();

            while (true) {
                System.out.println("\n========= Admin Menu =========");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Remove Employee");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        String id = readValid(input, "Enter Employee ID (E followed by digits): ",
                                "E\\d+", "Employee ID must start with 'E' followed by digits.");

                        String name = readValid(input, "Enter Name: ",
                                "[A-Za-z ]+", "Name must contain letters and spaces only.");

                        String email = readValid(input, "Enter Email: ",
                                "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$", "Please enter a valid email address.");

                        String address = readValid(input, "Enter Address: ",
                                ".+", "Address cannot be empty.");

                        String phone = readValid(input, "Enter Phone Number (11 digits): ",
                                "\\d{11}", "Phone number must contain exactly 11 digits.");

                        admin.addEmployee(id, name, email, address, phone);
                        break;
                        
                    case 2:
                        EmployeeUser[] employees = admin.getListOfEmployees();
                        System.out.println("\n--- Employee List ---");
                        for (EmployeeUser e : employees) {
                            System.out.println(e.lineRepresentation());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to remove: ");
                        String removeId = input.nextLine();
                        admin.removeEmployee(removeId);
                        break;

                    case 4:
                        admin.logout();
                        System.out.println("Logged out successfully.");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } else if (roleChoice == 2) {
            // ---------- EMPLOYEE ROLE ----------
            EmployeeRole employee = new EmployeeRole();

            while (true) {
                System.out.println("\n========= Employee Menu =========");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Purchase Product");
                System.out.println("4. Return Product");
                System.out.println("5. Apply Payment");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        String pid = input.nextLine();

                        System.out.print("Enter Product Name: ");
                        String pname = input.nextLine();

                        System.out.print("Enter Manufacturer Name: ");
                        String manu = input.nextLine();

                        System.out.print("Enter Supplier Name: ");
                        String supplier = input.nextLine();

                        System.out.print("Enter Quantity: ");
                        int qty = input.nextInt();

                        System.out.print("Enter Price: ");
                        double price = input.nextDouble();
                        input.nextLine();

                        employee.addProduct(pid, pname, manu, supplier, qty, price);
                        break;

                    case 2:
                        Product[] products = employee.getListOfProducts();
                        System.out.println("\n--- Product List ---");
                        for (Product p : products) {
                            System.out.println(p.lineRepresentation());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Customer SSN: ");
                        String ssn = input.nextLine();

                        System.out.print("Enter Product ID: ");
                        String productId = input.nextLine();

                        System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                        String purchaseDateStr = input.nextLine();
                        LocalDate purchaseDate = LocalDate.parse(purchaseDateStr, formatter);

                        employee.purchaseProduct(ssn, productId, purchaseDate);
                        break;

                    case 4:
                        System.out.print("Enter Customer SSN: ");
                        String ssnReturn = input.nextLine();

                        System.out.print("Enter Product ID: ");
                        String productReturnId = input.nextLine();

                        System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                        LocalDate purchaseDateReturn = LocalDate.parse(input.nextLine(), formatter);

                        System.out.print("Enter Return Date (dd-MM-yyyy): ");
                        LocalDate returnDate = LocalDate.parse(input.nextLine(), formatter);

                        double refund = employee.returnProduct(ssnReturn, productReturnId, purchaseDateReturn, returnDate);
                        if (refund != -1) {
                            System.out.println("Product returned successfully. Refund: " + refund);
                        }
                        break;

                    case 5:
                        System.out.print("Enter Customer SSN: ");
                        String paySSN = input.nextLine();

                        System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                        LocalDate payDate = LocalDate.parse(input.nextLine(), formatter);

                        employee.applyPayment(paySSN, payDate);
                        break;

                    case 6:
                        employee.logout();
                        System.out.println("Logged out successfully.");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } else {
            System.out.println("Invalid role choice!");
        }
    }
}
