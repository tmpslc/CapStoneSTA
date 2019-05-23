// CapStoneController.java
package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CapStoneController {
    @FXML 
    private ListView checkboxListView = new ListView();
    @FXML 
    private ListView addressListArea;

    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<String> addresses = FXCollections.observableArrayList();
    
    private int idChild = 0;
    private int idAddress = 0;

    private ObservableList<String> address = FXCollections.observableArrayList();

    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";
    private static String CHILD_NAME = "SELECT first_name, last_name FROM capstonesta.child";
    private static String CHILD_ID = "SELECT idChild FROM capstonesta.child WHERE first_name = ?"
            + "AND last_name = ?";
    private static String CROSS_REFERENCE = "SELECT idAddr FROM capstonesta.child_Address "
            + "WHERE idChild = ?";
    private static String ADDR_ID = "SELECT houseNum, street, zip FROM capstonesta.addr"
            + "WHERE idAddr = ?";


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
    
    @FXML
    private void getAddress() {        
        getIdChild();
        crossReference();
        addresses = getIdAddress(addresses);
        
        addressListArea.setItems(addresses);
    }
    
    private void getIdChild() {
        PreparedStatement idChildPs = null;
        ResultSet idChildRs;
        
        String fullName = (String) checkboxListView.getSelectionModel().getSelectedItem();
        
        String[] splitName = fullName.split(" ");
        
        System.out.println(splitName);
        
        String firstName = splitName[0];
        String lastName = splitName[1];
        
        System.out.println(firstName);
        
        try {
            CapStoneController.getConnection().prepareStatement(CHILD_ID);
            
            idChildPs.setString(1, firstName);
            idChildPs.setString(2, lastName);
            
            idChildRs = idChildPs.executeQuery();
            
            if (idChildRs.next()) {
                idChild = idChildRs.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    private void crossReference() {
        PreparedStatement crossReferencePs = null;
        ResultSet crossReferenceRs;
        
        try {
            CapStoneController.getConnection().prepareStatement(CROSS_REFERENCE);
            
            crossReferencePs.setString(1, Integer.toString(idChild));
            
            crossReferenceRs = crossReferencePs.executeQuery();
            
            if (crossReferenceRs.next()) {
                idAddress = crossReferenceRs.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();    
        }
    }
    
    private ObservableList<String> getIdAddress(ObservableList<String> addresses) {
        PreparedStatement idAddressPs = null;
        ResultSet idAddressRs;
        
        try {
            CapStoneController.getConnection().prepareStatement(ADDR_ID);
            
            idAddressPs.setString(1, Integer.toString(idAddress));
            
            idAddressRs = idAddressPs.executeQuery();
            
            if (idAddressRs.next()) {
                int houseNum = idAddressRs.getInt(1);
                String street = idAddressRs.getString(2);
                int zip = idAddressRs.getInt(3);
                                
                addresses.add(Integer.toString(houseNum) + " " + street + 
                        " " + Integer.toString(zip));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();    
        }
        
        return addresses;
    }
}
