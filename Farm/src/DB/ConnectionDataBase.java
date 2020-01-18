/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor Martins
 */
public class ConnectionDataBase {
    
    
   private static Connection connection;
   
   static String driverJDBC = "com.mysql.cj.jdbc.Driver";
   static String url = "jdbc:mysql://localhost/farm?useTimezone=true&serverTimezone=UTC";
   static String user = "root";
   static String password = "";
   
    private ConnectionDataBase(){}
    
    public static Connection getConnection() throws ClassNotFoundException{
       
                
                  try{
                      Class.forName(driverJDBC);
                      connection = DriverManager.getConnection(url, user, password);
                  }catch(SQLException | RuntimeException e){
                      JOptionPane.showMessageDialog(null,"Erro:"+e);
                  }
                 
            
            return connection;
    }
   
}
