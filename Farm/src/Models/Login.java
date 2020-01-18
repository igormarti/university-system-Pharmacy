
package Models;

import Classes.Users;
import DB.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor Martins
 */
public class Login {
    
   private Connection connection = null;
   private String query;
   private Users user;
    
    public Users Logar(String login) throws SQLException,ClassNotFoundException{
        
        this.query = "SELECT * FROM users WHERE login=?"; 
        
        try{
         
         this.connection = ConnectionDataBase.getConnection();
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setString(1,login);
         ResultSet rs = ps.executeQuery();
         rs.first();
        
            this.user = new Users();
            this.user.setId(rs.getInt("id"));
            this.user.setName(rs.getString("name"));
            this.user.setPassword(rs.getString("password"));
          
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto(a).");
        }finally{
            this.connection.close();   
        }
       
        
        return this.user;
    }
    
    
}
