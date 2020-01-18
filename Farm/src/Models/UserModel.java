/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.Users;
import DB.ConnectionDataBase;
import Interfaces.Funcoes;
import Views.User;
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
public class UserModel implements Funcoes{
    
    private Users user;
    private String query;
    private Connection connection = null;
    
    
    public Boolean Save(Users user) throws SQLException, ClassNotFoundException {
        
        this.query =  "INSERT INTO users (name,email,password,login,birth,cpf) VALUES(?,?,?,?,?,?)";
         
        this.connection = ConnectionDataBase.getConnection();
        
        try{
         
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setString(1,user.getName());
         ps.setString(2,user.getEmail());
         ps.setString(3,user.getPassword());
         ps.setString(4,user.getLogin());
         ps.setDate(5,new java.sql.Date(user.getBirth().getTime()));
         ps.setString(6,user.getCpf());
         ps.execute();
         
         JOptionPane.showMessageDialog(null,"Guardado com sucesso.");
          
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto(a).");
        }finally{
            this.connection.close();   
        }
             
        return true;
    }
    
    public List<Users> getUsers() throws ClassNotFoundException, SQLException{
        this.query = "SELECT * FROM users";
        this.connection = ConnectionDataBase.getConnection();
        List<Users> users = new ArrayList<>();
        
             Statement consult  = null;
             ResultSet result = null;
             Users user = null;
             
            
        try {
            consult = (Statement) this.connection.createStatement();
            result = (ResultSet) consult.executeQuery(this.query);
             
            
            while(result.next()){
                user = new Users();
                user.setName( result.getString("name"));
                user.setId(result.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.connection.close(); 
        }
        
        return users;
    }

    @Override
    public Boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
