package capstone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindowController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";
    private static String LOGIN_QUERY = "SELECT * FROM capstonesta.login WHERE username = ? AND password = ?";

    
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

    /**
     * Method - loginButtonPressed()
     * @param none
     * Runs when the login button is pressed and checks the entered data against the database
     * @return none
     */
    @FXML
    private void loginButtonPressed() {
        //creates a PreparedStatement called ps
        PreparedStatement ps;

        //creates a ResultSet called rs
        ResultSet rs;

        //sets username to usernameTextField
        String username = usernameTextField.getText();
        //sets password to passwordField
        String password = passwordField.getText();

         try {
             //grabs the connection from this class and prepares the loginQuery statement
             ps = LoginWindowController.getConnection().prepareStatement(LOGIN_QUERY);

             //sets username to the first ?
             ps.setString(1, username);
             //sets password to the second ?
             ps.setString(2, password);

             //sets the ResultSet to the result of the query
             rs = ps.executeQuery();

             //if the login succeeds
             if (rs.next()) {
                 Parent root;
                 
                 try {
                     //grab the currently open window and set it to loginWindow
                     Stage loginWindow = (Stage) loginButton.getScene().getWindow();
                     //close the window
                     loginWindow.close();

                     //create an FXMLLoader called root set to NewHandicapWindow.fxml
                     root = FXMLLoader.load(getClass().getResource("Capstone.fxml"));
                     //create a new stage called selectionWindow
                     Stage selectionWindow = new Stage();
                     selectionWindow.setTitle("Capstone");
                     //set the scene to root
                     selectionWindow.setScene(new Scene(root, 600, 400));
                     selectionWindow.show();
                 } catch (IOException IoException) {
                     //create a new alert called ioAlert of type ERROR
                     Alert ioAlert = new Alert(Alert.AlertType.ERROR);

                     //set the title
                     ioAlert.setTitle("Error");
                     //set the header
                     ioAlert.setHeaderText("IO Error");
                     //set the body text
                     ioAlert.setContentText("Reference the stack trace");

                     //show and wait
                     ioAlert.showAndWait();

                     IoException.printStackTrace();
                 }
             }
         //if the login fails    
         } catch (SQLException sqlException) {
             //create an Alert called sqlAlert for sqlExceptions
            Alert sqlAlert = new Alert(Alert.AlertType.ERROR);

            sqlAlert.setTitle("ERROR");
            sqlAlert.setHeaderText("SQL Alert");
            sqlAlert.setContentText("Check stack trace");
            
            sqlException.printStackTrace();
         }
    }
}
