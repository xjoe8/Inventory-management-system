package lab4;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct>{
    
    private static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String fileName){
        super(fileName);
    }
    
    @Override
    public CustomerProduct createRecordFrom(String line){
        String[] parts = line.split(",\\s*");
        if (parts.length < 4) 
            return null;
        LocalDate date = LocalDate.parse(parts[2].trim(),Formatter);
        CustomerProduct record = new CustomerProduct(parts[0].trim(), parts[1].trim(), date);
        record.setPaid(Boolean.valueOf(parts[3].trim()));
        return record;
    }
    
}
