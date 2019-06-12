/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.util.ArrayList;

/**
 *
 * @author cmoney896
 */
public class Van 
{
    //variables
    final int MAXKIDS = 16;
    private ArrayList<Child> children;
    
    //constructor
    public Van(ArrayList<Child> children)
    {
        this.children = children;
    }
    
    //add a child to the van
    public void addChild(Child child)
    {
        if (children.size() < MAXKIDS)
        {
            children.add(child);
        }
        else
        {
            System.out.println("No more kids can sit in this van.");
        }
    }
    
    //display the kids on the screen
    public void showKids()
    {
        for (int i = 0; i < children.size(); i++)
        {
            System.out.println(children.get(i).toString());
        }
    }
}
