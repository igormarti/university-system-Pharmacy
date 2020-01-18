/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Igor Martins
 */
public class Product {
    
      private Integer id;
      private String name;
      private String describe;
      private Float price;
      private Date expiration;
      private Integer type;
      private Integer quantity;
      
      
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    } 
      
      
    public String getName() {
        return name;
    }
    

    public void setName(String name_medicine) {
        this.name = name_medicine;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe_medicine) {
        this.describe = describe_medicine;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
      
      
      
}
