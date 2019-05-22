// CapStoneController.java
package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class CapStoneController {
    @FXML 
    private ListView checkboxListView = new ListView();
    @FXML 
    private TextArea addressTextArea;
    @FXML 
    private ScrollPane checkBoxPane;
    
    private ObservableList<String> names = FXCollections.observableArrayList();


    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";
    private static String CHILD_NAME = "SELECT first_name, last_name FROM capstonesta.child";
    private static String ADDRESS = "SELECT ";


    public void initialize() {        
        ObservableList<String> names = grabNames();
        
        checkboxListView.setItems(names);
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
    
    private ObservableList<String> grabNames() {
        PreparedStatement namePs;
        ResultSet nameRs;
        
        try {
            namePs = CapStoneController.getConnection().prepareStatement(CHILD_NAME);
            
            nameRs = namePs.executeQuery();
            
            while (nameRs.next()) {
                names.add(nameRs.getString(1) + " " + nameRs.getString(2));
            } 
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return names;
    }
    
    
}
