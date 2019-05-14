/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

/**
 *
 * @author adiaz939
 */
public class Child {
    private String address;
    private String firstName;
    private String lastName;
    
    public Child (String address, String firstName, String lastName)
    {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    @Override
    public String toString() 
    {
        return 
    }
}
