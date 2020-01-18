
package farm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IGOR
 */
public class Registration implements Information{
    
    private final List<Person>person = new ArrayList();


    @Override
    public Integer getQuantity() {
     
        return this.person.size();
        
    }


    public String save(Person person) {
        this.person.add(person);
        
        return person.name;
    }

    public List<Person> getPeople() {
         return this.person;
    }

     public boolean removePerson(Person person){
       boolean success = this.person.remove(person);
       return success;
    }
    
    public boolean removeAllPeople(List<Person> person){
           boolean  success =   this.person.removeAll(person);
           return success;             
     }
    
}
