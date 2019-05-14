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
        //takes in address and splits at every space
        String[] splitAddress = address.split(" ");
        
        try {
            //create Integer called houseNum and sets it to the first split
            Integer houseNum = Integer.parseInt(splitAddress[0]);
            //create String called street and sets it to the second split
            String street = splitAddress[1];
            String city = splitAddress[2];
            String state = splitAddress[3];
            Integer zipNum = Integer.parseInt(splitAddress[4]);
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException.getMessage());
        }
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
        return String.format("First Name:%s%n"
                + "Last Name:%s%n"
                + "Address:%s%n",
                firstName, lastName, address);
    }
}
