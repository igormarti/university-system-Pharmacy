/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author IGOR
 */
public class Farm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      
     try{   
        Registration registration = new Registration();
        
        Clerk c1 = new Clerk("Josè","Rua A","122.120.111-42",25,1500.00);
        Manager m1 = new Manager("Luis","Rua 1","210.244.442-24",38,2200.00);
        Admin a1 = new Admin("Zezito","Rua C","124.110.320-60",50,5500.00);
        Client cl1  = new Client("Hitário Melo","Rua B","122.154.024-24",24);
        
        registration.save(c1);
        registration.save(m1);
        registration.save(a1);
        registration.save(cl1);
        
        List<Person> people = registration.getPeople();
        
        System.out.println("----Informações-das-Pessoas----");
        for(Person p:people){
            System.out.println("Nome:"+p.name);
            System.out.println("Endereço:"+p.address);
            System.out.println("Cpf:"+p.cpf);
            System.out.println("Idade:"+p.age);
            System.out.println("---------------------------");
        }
       
        System.out.println("Quantidade de Pessoas:"+ registration.getQuantity());
        
        //-----------------------------------------------------------------------------
        
        Stock stock = new Stock();
        
        Medicine m = new Medicine("Antibiótico",1,"2018-10-15","Fabricador A","Remédio A",50.00);
        Hygienic h = new Hygienic("Bocal",2,"2018-11-15","Fabricador C","Produto E",20.00);
        
        stock.save(m);
        stock.save(h);
        
        List<Product> products = stock.getProducts();
        
        Iterator<Product> p = products.iterator();
        
        System.out.println("----Informações-dos-Produtos----");
        while(p.hasNext()){
            Product product  = p.next();
            System.out.println("Tipo:"+product.type);
            System.out.println("Código:"+product.cod);
            System.out.println("Valido até:"+product.expiration);
            System.out.println("Fabricante:"+product.fabricator);
            System.out.println("Nome:"+product.name);
            System.out.println("Preço:"+product.price);
            System.out.println("------------------------------");
        }
          
        System.out.println("Quantidade de Produtos:"+ stock.getQuantity());
        
     }catch(ArrayIndexOutOfBoundsException e){
         
         System.out.println("Erro ao tentar fazer as operações do sistema.");
         
     }   
        
        
    }
    
}
