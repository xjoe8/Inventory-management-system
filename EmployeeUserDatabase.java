package lab4;
import java.util.*;

public class EmployeeUserDatabase extends Database <EmployeeUser> {
    
    //Constructor
    public EmployeeUserDatabase(String filename){
        super(filename);
    }
    
    
    @Override
    public EmployeeUser createRecordFrom(String line){  //converts line into EmployeeUser
        StringTokenizer st = new StringTokenizer(line, ",");
        try {
            String employeeId = st.nextToken().trim();
            String name = st.nextToken().trim();
            String email = st.nextToken().trim();
            String address = st.nextToken().trim();
            String phoneNumber = st.nextToken().trim();

        return new EmployeeUser(employeeId, name, email, address, phoneNumber);
        }
        catch(Exception e) {
            return null;
        } 
    }
}
