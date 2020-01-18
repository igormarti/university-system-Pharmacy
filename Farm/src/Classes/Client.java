/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Igor Martins
 */
public class Client extends Person{
    
     private Integer id_user;
        

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
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
