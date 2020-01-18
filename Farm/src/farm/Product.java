
package farm;

/**
 *
 * @author IGOR
 */
abstract class Product {
    
    protected Integer cod;
    protected String expiration;
    protected String fabricator;
    protected String name;
    protected Double price;
    protected String type;

    public Product(String type,Integer cod, String expiration, String fabricator, String name, Double price) {
        this.cod = cod;
        this.expiration = expiration;
        this.fabricator = fabricator;
        this.name = name;
        this.price = price;
        this.type =  type;
    }
    
    public void discount(Medicine product,Integer discount){
         product.price -= product.price*discount/100;
    }
    
    public void discount(Hygienic product,Integer discount){
         product.price -= product.price*discount/100;
    }
    
}
