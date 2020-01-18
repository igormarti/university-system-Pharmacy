
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
public class Employee implements Funcoes{
    
    private String query;
    private Connection connection;
     
    public Boolean Save(Classes.Employee e) throws SQLException, ClassNotFoundException {
        
        this.query = "INSERT INTO employee (user_id,adm,salary) VALUES(?,?,?)";
         
        this.connection = ConnectionDataBase.getConnection();
        
        try{
         
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setInt(1,e.getId_user());
         ps.setInt(2,e.getAdmin());
         ps.setDouble(3,e.getSalary());
         ps.execute();
         
         JOptionPane.showMessageDialog(null,"Guardado com sucesso.");
          
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto(a).");
        }finally{
            this.connection.close();   
        }
             
        return true;
    } 
    
    
    public List<Classes.Employee> getEmployees() throws ClassNotFoundException, SQLException{
        
     this.query =  "SELECT * FROM employee INNER JOIN users ON employee.user_id=users.id";
     this.connection = ConnectionDataBase.getConnection();
     List <Classes.Employee> ems = new ArrayList<>();
     
     Classes.Employee e = null;
     Statement q = this.connection.createStatement();
     ResultSet r = q.executeQuery(this.query);
     
     while(r.next()){
         e = new Classes.Employee();
         e.setId(r.getInt("employee.id"));
         e.setName(r.getString("users.name"));
         e.setSalary(r.getDouble("employee.salary"));
         e.setAdmin(r.getInt("employee.adm"));
         ems.add(e);
     }
        return ems;
    }
    
    
    public Classes.Employee getEmployee(Integer id) throws ClassNotFoundException, SQLException{
        
       this.query = "SELECT * FROM employee INNER JOIN users ON employee.user_id=users.id WHERE employee.id=?";
       Classes.Employee e = null;
        
       try{
         
         this.connection = ConnectionDataBase.getConnection();
         PreparedStatement ps = this.connection.prepareStatement(this.query);
         ps.setInt(1,id);
         ResultSet rs = ps.executeQuery();
         rs.first();
         
            e =  new Classes.Employee();
            e.setId(rs.getInt("employee.id"));
            e.setName(rs.getString("users.name"));
            e.setSalary(rs.getDouble("employee.salary"));
            e.setAdmin(rs.getInt("employee.adm"));
          
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null,"Aconteceu algum erro inesperado.");
        }finally{
            this.connection.close();   
        }
       
        
        return e;
        
    }
    
    public void Edit(Classes.Employee e) throws ClassNotFoundException, SQLException{
       
        this.query = "UPDATE employee SET user_id=? , salary=?, adm=? WHERE id=?";
        this.connection = ConnectionDataBase.getConnection();
        
        try {
            try (PreparedStatement stmt = this.connection.prepareStatement(this.query)) {
                stmt.setInt(1,e.getId_user());
                stmt.setDouble(2, e.getSalary());
                stmt.setInt(3, e.getAdmin());
                stmt.setInt(4,e.getId());
                stmt.execute();
                JOptionPane.showMessageDialog(null,"Editado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.connection.close();
        }
        
    }

    @Override
    public Boolean delete(Integer id) {
        
        this.query = "DELETE FROM employee WHERE id=?";
        
        try {
            this.connection = ConnectionDataBase.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
          PreparedStatement stmt = this.connection.prepareStatement(this.query);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return true;
    }
    
    public List<Classes.Employee> search(String u) throws ClassNotFoundException, SQLException{
        
        if(!u.isEmpty()){
            this.query = "SELECT * FROM employee INNER JOIN users ON employee.user_id=users.id where users.name like'%"+u+"%' ORDER BY users.name";
        }else{
            this.query = "SELECT * FROM employee INNER JOIN users ON employee.user_id=users.id ORDER BY users.name";
        }
        
        this.connection = ConnectionDataBase.getConnection();
        
        List <Classes.Employee> ems = new ArrayList<>();
     
        Classes.Employee e = null;
        Statement q = this.connection.createStatement();
        ResultSet r = q.executeQuery(this.query);

        while(r.next()){
            e = new Classes.Employee();
            e.setId(r.getInt("employee.id"));
            e.setName(r.getString("users.name"));
            e.setSalary(r.getDouble("employee.salary"));
            e.setAdmin(r.getInt("employee.adm"));
            ems.add(e);
        }
       
       return ems;
    }    
    
    
}
