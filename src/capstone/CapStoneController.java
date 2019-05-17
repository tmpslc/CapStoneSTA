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
    @FXML private  VBox checkboxVBox;
    @FXML private TextArea addressTextArea;
    @FXML private ScrollPane checkBoxPane;

    CheckBox box = new CheckBox (" ");

    private static String DATABASE_URL = "jdbc:mysql://localhost@3306";

    public void initialize() {
        System.out.println("ran");
        
        Connection dataBaseConnection = getConnection();
        
        ArrayList<CheckBox> boxes = grabAmmountOfKids();
        ArrayList<String> names = grabNames();
        
        printCheckboxes(boxes, names);
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
            
            //sets val equal to the ammount of children in the database
            Integer val =  ((Number) rs.getObject(1)).intValue();
            System.out.println(val);
            
            //adds checkboxes to the array list boxes based on how many children 
            //there are
            for (int i = 0; i <= val; i++) {
                boxes.add(box);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return boxes;
    }
    
    private ArrayList<String> grabNames() {
        //return the first and last name's of all the children
        String CHILD_NAME = "Select first_name, last_name FROM capstonesta.child";
        
        //create two ArrayLists to store the names and the checkboxes
        ArrayList<String> names = new ArrayList<String>();
        
        //create a prepared statemet to quicken up name queries
        PreparedStatement namePs;
        
        //result set for all the names of the children
        ResultSet nameRs;
  
        try {
            //prepares a statement that will grab all the childrens names
            namePs = CapStoneController.getConnection().prepareStatement(CHILD_NAME);
            //executes that statement
            nameRs = namePs.executeQuery();
            
            //while there are names in the result set add them to the array list names
            while (nameRs.next()) {
                names.add(nameRs.getString(1));
            }
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return names;
    }
    
    private void printCheckboxes(ArrayList<CheckBox> boxes, ArrayList<String> names) {        
        //for the size of the array list boxes
        for (int i = 0; i <= boxes.size(); i++) {
            //set the in scope box to currentBox
            CheckBox currentBox = boxes.get(i);
            
            //set the in scope name to currentName
            String currentName = names.get(i);
            
            //add a checkbox to checkboxVBox
            checkboxVBox.getChildren().add(box);
            //set the text of that box to the current name
            currentBox.setText(currentName);
        }
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
