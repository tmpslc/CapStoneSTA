// CapStoneController.java
package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CapStoneController {
    @FXML private  VBox checkboxVBox = new VBox();
    @FXML private TextArea addressTextArea;
    @FXML private ScrollPane checkBoxPane;

    CheckBox box = new CheckBox (" ");

    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";

    public void initialize() {
        System.out.println("ran");
        
        Connection dataBaseConnection = getConnection();
        
        ArrayList<CheckBox> boxes = grabAmmountOfKids();
        
        //checkboxVBox.getChildren().addAll(boxes);
        
        ArrayList<String> firstNames = grabFirstNames();
        ArrayList<String> lastNames = grabLastNames();
        
        printCheckboxes(boxes, firstNames, lastNames);
    }
    
    private static Connection getConnection() {
        Connection dataBaseConnection = null;
        
        try {
            dataBaseConnection = DriverManager.getConnection(DATABASE_URL, "root",
                    "yoast");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return dataBaseConnection;
    }
    
    private ArrayList<CheckBox> grabAmmountOfKids() {
        //returns a number that is equal to the amount of children in the table
        String CHILD_LENGTH = "Select COUNT(*) FROM capstonesta.child";
        
        ArrayList<CheckBox> boxes = new ArrayList<CheckBox>();

        //create a prepared statement to quicken up queries
        PreparedStatement ps;
        //result set for the amount of children in the database
        ResultSet rs;
        
        try {
            //connects to the database and prepares the CHILD_LENGTH statement
            ps = CapStoneController.getConnection().prepareStatement(CHILD_LENGTH);
            
            //executes the CHILD_LENGTH statement
            rs = ps.executeQuery();
            
            if (rs.next()) {
                //sets val equal to the ammount of children in the database
                int val = rs.getInt(1);

                for (int i = 0; i <= val; i++) {
                     boxes.add(box);
                }
               
                System.out.println(boxes);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return boxes;
    }
    
    private ArrayList<String> grabFirstNames() {
        //return the first and last name's of all the children
        String CHILD_NAME = "Select first_name, last_name FROM capstonesta.child";
        
        //create two ArrayLists to store the firstNames and the checkboxes
        ArrayList<String> firstNames = new ArrayList<String>();
        
        //create a prepared statemet to quicken up name queries
        PreparedStatement firstNamePs;
        
        //result set for all the firstNames of the children
        ResultSet firstNameRs;
  
        try {
            //prepares a statement that will grab all the childrens firstNames
            firstNamePs = CapStoneController.getConnection().prepareStatement(CHILD_NAME);

            //executes that statement
            firstNameRs = firstNamePs.executeQuery();
           
            //while there are firstNames in the result set add them to the array list firstNames
            while (firstNameRs.next()) {
                firstNames.add(firstNameRs.getString(1));
                System.out.println(firstNames);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return firstNames;
    }
    
        private ArrayList<String> grabLastNames() {
        //return the first and last name's of all the children
        String CHILD_NAME = "Select first_name, last_name FROM capstonesta.child";
        
        //create two ArrayLists to store the firstNames and the checkboxes
        ArrayList<String> lastNames = new ArrayList<String>();
        
        //create a prepared statemet to quicken up name queries
        PreparedStatement lastNamePs;
        
        //result set for all the firstNames of the children
        ResultSet lastNameRs;
  
        try {
            //prepares a statement that will grab all the childrens firstNames
            lastNamePs = CapStoneController.getConnection().prepareStatement(CHILD_NAME);

            //executes that statement
            lastNameRs = lastNamePs.executeQuery();
           
            //while there are firstNames in the result set add them to the array list firstNames            
            while (lastNameRs.next()) {
                lastNames.add(lastNameRs.getString(2));
                System.out.println(lastNames);
            }
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return lastNames;
    }
    
    private void printCheckboxes(ArrayList<CheckBox> boxes, ArrayList<String> firstNames, 
            ArrayList<String> lastNames) {
        
        int val = 11;
        
        for (int i = 0; i < val; i++) {
            boxes.add(box);
        }
        
        //for the size of the array list boxes
        for (int i = 0; i < boxes.size(); i++) {            
            //set the in scope box to currentBox
            CheckBox currentBox = boxes.get(i);
            
            
            //set the in scope name to currentName
            String currentFirstName = firstNames.get(i);
            String currentLastName = lastNames.get(i);
            
            //add a checkbox to checkboxVBox
            //set the text of that box to the current name
            currentBox.setText(currentFirstName + " " + currentLastName);
            
            System.out.println(currentBox);
            boxes.add(currentBox);
        }
        checkboxVBox.getChildren().addAll(boxes);
        System.out.println("Here" + boxes);

    }

//    @FXML
//    private void checkBoxClicked() {
//        String firstName = box.getText();
//        
//        String SELECTKID_QUERY = "Select * from child where firstName = ?";
//        String GETKID_ADDRESS = "Select * from child where firstName = ?";
//        
//        if () {
//            addressPane.setText("Select address from child where firstName = ?");
//       }
//    }
}
