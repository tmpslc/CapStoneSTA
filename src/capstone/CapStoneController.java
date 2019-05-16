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

public class CapStoneController {

    @FXML private ScrollPane addressPane;
    @FXML private ScrollPane checkInPane;

    @FXML CheckBox box = new CheckBox (" ");

    private static String DATABASE_URL = "jdbc:mysql://localhost@3306";

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
    
    public void initialize()
    {
        //returns a number that is equal to the amount of children in the table
        String CHILD_LENGTH = "Select COUNT(*) FROM capstonesta.child";
        String CHILD_NAME = "Select first_name, last_name FROM capstonesta.child";
        
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<CheckBox> boxes = new ArrayList<CheckBox>();
        
        PreparedStatement ps;
        ResultSet rs;
        ResultSet nameRs;
        
        try {
            ps = CapStoneController.getConnection().prepareStatement(CHILD_LENGTH);
            
            rs = ps.executeQuery();
            
            Integer val =  ((Number) rs.getObject(1)).intValue();
            
            for (int i = 0; i <= val; i++) {
                boxes.add(box);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        try {
            PreparedStatement namePs = CapStoneController.getConnection().prepareStatement(CHILD_NAME);
            nameRs = namePs.executeQuery();
            
            while (nameRs.next()) {
                names.add(nameRs.getString(1));
            }
            
        } catch (SQLException sqlExcpetion) {
            
        }
        
        for (int i = 0; i <= boxes.size(); i++) {
            CheckBox currentBox = boxes.get(i);
            
            String currentName = names.get(i);
            
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
