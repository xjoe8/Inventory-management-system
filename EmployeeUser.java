package lab4;
public class EmployeeUser implements Storable {
    
    private String employeeId, name, email, address, phoneNumber;
    
    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        setEmployeeId(employeeId);
        setName(name);
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    public void setEmployeeId(String employeeId) {
        if (employeeId == null || !employeeId.matches("E\\d+")) {
            System.out.println("Error: Employee ID must start with 'E' followed by digits.");
            return;
        }
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        if (name == null || !name.matches("[A-Za-z ]+")) {
            System.out.println("Error: Name can only contain letters and spaces.");
            return;
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            System.out.println("Error: Invalid email format.");
            return;
        }
        this.email = email;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Error: Address cannot be empty.");
            return;
        }
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{11}")) {
            System.out.println("Error: Phone number must contain exactly 11 digits.");
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String lineRepresentation() {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    @Override
    public String getSearchKey() {
        return this.employeeId;
    }
}

