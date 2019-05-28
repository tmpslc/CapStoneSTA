// CapStoneController.java
package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CapStoneController {
    @FXML 
    private ListView checkboxListView = new ListView();
    @FXML 
    private ListView addressListView = new ListView();

    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<String> addresses = FXCollections.observableArrayList();
    
    private int idChild = 0;
    private int idAddress = 0;

    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";
    private static String CHILD_NAME = "SELECT first_name, last_name FROM capstonesta.child";
    private static String CHILD_ID = "SELECT idChild FROM capstonesta.child WHERE first_name = ?"
            + "AND last_name = ?";
    private static String CROSS_REFERENCE = "SELECT idAddr FROM capstonesta.child_address "
            + "WHERE idChild = ?";
    private static String ADDR_ID = "SELECT houseNum, street, zip FROM capstonesta.addr "
            + "WHERE idaddr = ?";


    public void initialize() {        
        grabNames();
        
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
        
        System.out.println(idChild);
        System.out.println(idAddress);
        
        getIdAddress();
        
        for (int i = 0; i < addresses.size(); i++) {
            System.out.println(addresses.get(i));
        }
        
        addressListView.setItems(addresses);
    }
    
    private void getIdChild() {
        PreparedStatement idChildPs;
        ResultSet idChildRs;
        
        String fullName = (String) checkboxListView.getSelectionModel().getSelectedItem();
        
        String[] splitName = fullName.split(" ");
        
        String firstName = splitName[0];
        String lastName = splitName[1];
                
        try {
            idChildPs = CapStoneController.getConnection().prepareStatement(CHILD_ID);
            
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
        PreparedStatement crossReferencePs;
        ResultSet crossReferenceRs;
        
        try {
            crossReferencePs = CapStoneController.getConnection().prepareStatement(CROSS_REFERENCE);
            
            crossReferencePs.setString(1, Integer.toString(idChild));
            
            crossReferenceRs = crossReferencePs.executeQuery();
            
            if (crossReferenceRs.next()) {
                idAddress = crossReferenceRs.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();    
        }
    }
    
    private ObservableList<String> getIdAddress() {
        PreparedStatement idAddressPs;
        ResultSet idAddressRs;
        String fullAddress = "";
        boolean duplicate = false;
        
        try {
            idAddressPs = CapStoneController.getConnection().prepareStatement(ADDR_ID);
            
            idAddressPs.setString(1, Integer.toString(idAddress));
            
            idAddressRs = idAddressPs.executeQuery();
            
            if (idAddressRs.next()) {
                int houseNum = idAddressRs.getInt(1);
                String street = idAddressRs.getString(2);
                int zip = idAddressRs.getInt(3);
                
                fullAddress = Integer.toString(houseNum) + " " + street + 
                        " " + Integer.toString(zip);                

                for (int i = 0; i < addresses.size(); i++) {
                    if (fullAddress.equals(addresses.get(i))) {
                        duplicate = true;
                    } else {
                        duplicate = false;
                    }
                }

                
                if (duplicate == false) {
                    addresses.add(fullAddress);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();    
        }
        
        return addresses;
    }
    
    @FXML
    private void removeAddress() {
        int selectedIndex = addressListView.getSelectionModel().getSelectedIndex();
        
        addressListView.getItems().remove(selectedIndex);
    }
}
