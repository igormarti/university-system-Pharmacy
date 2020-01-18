
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
public class Client implements Funcoes{
    
    private String query;
    private Connection connection;
     
    public Boolean Save(Classes.Client client) throws SQLException, ClassNotFoundException {
        
        this.query =  "INSERT INTO client (id_user,address,number) VALUES(?,?,?)";
         
        this.connection = ConnectionDataBase.getConnection();
        
        try{
         
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setInt(1,client.getId_user());
         ps.setString(2,client.getAddress());
         ps.setInt(3,client.getNumber());
         ps.execute();
         
         JOptionPane.showMessageDialog(null,"Guardado com sucesso.");
          
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto(a).");
        }finally{
            this.connection.close();   
        }
             
        return true;
    } 
    
    
    public List<Classes.Client> getClients() throws ClassNotFoundException, SQLException{
        
     this.query =  "SELECT * FROM client INNER JOIN users ON client.id_user=users.id";
     this.connection = ConnectionDataBase.getConnection();
     List <Classes.Client> clients = new ArrayList<>();
     
     Classes.Client client = null;
     Statement q = this.connection.createStatement();
     ResultSet r = q.executeQuery(this.query);
     
     while(r.next()){
         client = new Classes.Client();
         client.setId(r.getInt("client.id"));
         client.setName(r.getString("users.name"));
         client.setAddress(r.getString("client.address"));
         client.setNumber(r.getShort("client.number"));
         clients.add(client);
     }
        return clients;
    }
    
    
    public Classes.Users getClient(Integer id) throws ClassNotFoundException, SQLException{
        
       this.query = "SELECT * FROM client INNER JOIN users ON client.id_user=users.id WHERE client.id=?";
       Classes.Users client = null;
        
       try{
         
         this.connection = ConnectionDataBase.getConnection();
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setInt(1,id);
         ResultSet rs = ps.executeQuery();
         rs.first();
         
            client =  new Classes.Users();
            client.setId(rs.getRow());
            client.setName(rs.getString("users.name"));
            client.setAddress(rs.getString("client.address"));
            client.setNumber(rs.getShort("client.number"));
          
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Aconteceu algum erro inesperado.");
        }finally{
            this.connection.close();   
        }
       
        
        return client;
        
    }
    
    public void Edit(Classes.Client client) throws ClassNotFoundException, SQLException{
        
        this.query = "UPDATE client SET id_user=? , address=?, number=? WHERE id=?";
        this.connection = ConnectionDataBase.getConnection();
        
        try {
            try (PreparedStatement stmt = this.connection.prepareStatement(this.query)) {
                stmt.setInt(1,client.getId_user());
                stmt.setString(2, client.getAddress());
                stmt.setShort(3, client.getNumber());
                stmt.setInt(4,client.getId());
                stmt.execute();
                JOptionPane.showMessageDialog(null,"Editado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.connection.close();
        }
        
    }

    @Override
    public Boolean delete(Integer id) {
        
        this.query = "DELETE FROM client WHERE id=?";
        
        try {
            this.connection = ConnectionDataBase.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          PreparedStatement stmt = this.connection.prepareStatement(this.query);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return true;
    }
    
    public List<Classes.Users> search(String u) throws ClassNotFoundException, SQLException{
        
        if(!u.isEmpty()){
            this.query = "SELECT * FROM client INNER JOIN users ON client.id_user=users.id where users.name like'%"+u+"%' ORDER BY users.name";
        }else{
            this.query = "SELECT * FROM client INNER JOIN users ON client.id_user=users.id ORDER BY users.name";
        }
        
        this.connection = ConnectionDataBase.getConnection();
        
        List <Classes.Users> users = new ArrayList<>();
     
        Classes.Users user = null;
        Statement q = this.connection.createStatement();
        ResultSet r = q.executeQuery(this.query);

        while(r.next()){
            user = new Classes.Users();
            user.setId(r.getInt("client.id"));
            user.setName(r.getString("users.name"));
            user.setAddress(r.getString("client.address"));
            user.setNumber(r.getShort("client.number"));
            users.add(user);
        }
       
       return users;
    }    
    
    
}
