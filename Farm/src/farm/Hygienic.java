
package farm;


/**
 *
 * @author IGOR
 */
public class Hygienic extends Product{
    
    
    
    public Hygienic(String type,Integer cod, String expiration, String fabricator, String name, Double price) {
        super(type ,  cod, expiration, fabricator, name, price);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public void discount(Hygienic product, Integer discount) {
        super.discount(product, discount); 
    }

   
   
    
}
