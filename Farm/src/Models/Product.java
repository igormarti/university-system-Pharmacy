
package Models;

import DB.ConnectionDataBase;
import Interfaces.Funcoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor Martins
 */
public class Product implements Funcoes{
    
    private String query;
    private Connection connection;
     
    public Boolean Save(Classes.Product product) throws SQLException, ClassNotFoundException {
        
        this.query =  "INSERT INTO products (name,description,expiration,price,type,quantity) VALUES(?,?,?,?,?,?)";
         
        this.connection = ConnectionDataBase.getConnection();
        
        try{
         
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setString(1,product.getName());
         ps.setString(2,product.getDescribe());
         ps.setDate(3,new java.sql.Date(product.getExpiration().getTime()));
         ps.setFloat(4,product.getPrice());
         ps.setInt(5,product.getType());
         ps.setInt(6,product.getQuantity());
         ps.execute();
         
         JOptionPane.showMessageDialog(null,"Guardado com sucesso.");
          
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto(a).");
        }finally{
            this.connection.close();   
        }
             
        return true;
    } 
    
    
    public List<Classes.Product> getProducts() throws ClassNotFoundException, SQLException{
        
     this.query =  "SELECT * FROM products";
     this.connection = ConnectionDataBase.getConnection();
     List <Classes.Product> products = new ArrayList<>();
     
     Classes.Product product = null;
     Statement q = this.connection.createStatement();
     ResultSet r = q.executeQuery(this.query);
     
     while(r.next()){
         product = new Classes.Product();
         product.setId(r.getInt("id"));
         product.setName(r.getString("name"));
         product.setDescribe(r.getString("description"));
         product.setExpiration(r.getDate("expiration"));
         product.setPrice(r.getFloat("price"));
         product.setType(r.getInt("type"));
          product.setQuantity(r.getInt("quantity"));
         products.add(product);
     }
        return products;
    }
    
    
    public Classes.Product getProduct(Integer id) throws ClassNotFoundException, SQLException{
        
       this.query = "SELECT * FROM products WHERE id=?";
       Classes.Product product = null;
        
       try{
         
         this.connection = ConnectionDataBase.getConnection();
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setInt(1,id);
         ResultSet rs = ps.executeQuery();
         rs.first();
         
            product =  new Classes.Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescribe(rs.getString("description"));
            product.setExpiration(rs.getDate("expiration"));
            product.setPrice(rs.getFloat("price"));
            product.setType(rs.getInt("type"));
            product.setQuantity(rs.getInt("quantity"));

            
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Aconteceu algum erro inesperado.");
        }finally{
            this.connection.close();   
        }
       
        
        return product;
        
    }
    
    public void Edit(Classes.Product p) throws ClassNotFoundException, SQLException{
        
        this.query = "UPDATE products SET name=? , description=?, expiration=?, price=?, type=?,quantity=? WHERE id=?";
        this.connection = ConnectionDataBase.getConnection();
        
        try {
            try (PreparedStatement stmt = this.connection.prepareStatement(this.query)) {
                stmt.setString(1,p.getName());
                stmt.setString(2, p.getDescribe());
                stmt.setDate(3, new java.sql.Date(p.getExpiration().getTime()));
                stmt.setFloat(4,p.getPrice());
                stmt.setInt(5,p.getType());
                stmt.setInt(6,p.getQuantity());
                stmt.setInt(7,p.getId());
                stmt.execute();
                JOptionPane.showMessageDialog(null,"Editado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.connection.close();
        }
        
    }

    @Override
    public Boolean delete(Integer id) {
        
        this.query = "DELETE FROM products WHERE id=?";
        
        try {
            this.connection = ConnectionDataBase.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          PreparedStatement stmt = this.connection.prepareStatement(this.query);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return true;
    }
    
    public List<Classes.Product> search(String u) throws ClassNotFoundException, SQLException{
        
        if(!u.isEmpty()){
            this.query = "SELECT * FROM products where name like'%"+u+"%' ORDER BY name";
        }else{
            this.query = "SELECT * FROM products ORDER BY name";
        }
        
        this.connection = ConnectionDataBase.getConnection();
        
        List <Classes.Product> products = new ArrayList<>();
     
        Classes.Product product = null;
        Statement q = this.connection.createStatement();
        ResultSet r = q.executeQuery(this.query);

        while(r.next()){
            product = new Classes.Product();
                product.setId(r.getInt("id"));
                product.setName(r.getString("name"));
                product.setDescribe(r.getString("description"));
                product.setExpiration(r.getDate("expiration"));
                product.setPrice(r.getFloat("price"));
                product.setType(r.getInt("type"));
                 product.setQuantity(r.getInt("quantity"));
                products.add(product);
        }
       
       return products;
    }    
    
    
}
