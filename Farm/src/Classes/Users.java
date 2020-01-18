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
public class Users extends Person{
        
       
    private String password;
    private String login;
    private String email;
      


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
       
    @Override
    public String toString(){
        return super.getName();
    }   
    
    @Override
    public String getSearch() {
        return Person.search;
    }

    @Override
    void setSearch(String search) {
       Person.search = search;
    }
       
}
