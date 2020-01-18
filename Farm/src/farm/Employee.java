
package farm;

/**
 *
 * @author IGOR
 */
public class Employee extends Person{
    
    protected Double salary;

    public Employee(String name, String address, String cpf, Integer age, Double salary) {
        super(name, address, cpf, age);
        this.salary = salary;
    }
       
       
    
}
