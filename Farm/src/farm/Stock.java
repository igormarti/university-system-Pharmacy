
package farm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IGOR
 */
public class Stock  implements Information{
    
   private final List<Product> product = new ArrayList();
    
    @Override
    public Integer getQuantity(){
        
        return this.product.size();
        
    }
    
     public String save(Product product) {
        this.product.add(product);
        
        return product.name;
    }

   
    public List<Product> getProducts() {
         return this.product;
    }
    
    public boolean removeProduct(Product product){
       boolean success = this.product.remove(product);
       return success;
    }
    
    public boolean removeAllProducts(List<Product> product){
           boolean  success =   this.product.removeAll(product);
           return success;             
     }

}
