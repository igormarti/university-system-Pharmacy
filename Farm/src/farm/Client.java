
package farm;

import java.util.List;

/**
 *
 * @author IGOR
 */
public class Client  extends Person{
    
    public Client(String name, String address, String cpf, Integer age) {
        super(name, address, cpf, age);
    }
    
   public List<Product> buyProduct(List<Product> products,Product product){
       
       boolean success = products.remove(product);
        
       if(success == true){
            return products;
       }else{
            return null;
       }
        
   }
    
}
