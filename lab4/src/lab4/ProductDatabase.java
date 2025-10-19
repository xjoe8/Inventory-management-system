package lab4;

public class ProductDatabase extends Database<Product> {    
    
    public ProductDatabase(String filename){
        super(filename);
    }

    @Override
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
            
            return new Product(productID, productName, manufacturerName , supplierName, q, p);
        }
        catch (NumberFormatException ex){
        return null;
        }
    }
    
}
