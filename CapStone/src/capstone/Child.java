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
    private String firstName;
    private String lastName;
    private String address;
    
    public Child (String firstName, String lastName, String address) //constructor for object Child
    {
        //initializing address, firstName and lastName
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    
    public void setAddress(String address) //setter for address, uses String tokenizer to split the address into different parts
    {
        //takes in address and splits at every space
        String[] splitAddress = address.split(" ");
        
        try {
            //create Integer called houseNum and sets it to the first split
            Integer houseNum = Integer.parseInt(splitAddress[0]);
            //create String called street and sets it to the second split
            String street = splitAddress[1];
            //create String called city and sets it to the third split 
            String city = splitAddress[2];
            //create String called state and sets it to the fourth split
            String state = splitAddress[3];
            //create Integer called zipNum and sets it to the fifth split
            Integer zipNum = Integer.parseInt(splitAddress[4]);
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException.getMessage()); //throws error message if any of these splits have an error occur
        }
    }
    
    public String getAddress() //getter for address
    {
        return address;
    }
    
    public void setFirstName(String firstName) //setter for firstName
    {
        this.firstName = firstName;
    }
    
    public String getFirstName() //getter for firstName
    {
        return firstName;
    }
    
    public void setLastName(String lastName) //setter for lastName
    {
        this.lastName = lastName;
    }
    
    public String getLastName() //getter for lastName
    {
        return lastName;
    }
    
    @Override
    public String toString() //toString for Child
    {
        return String.format("First Name: %s%n"
                + "Last Name: %s%n"
                + "Address: %s%n",
                firstName, lastName, address);
    }
}
