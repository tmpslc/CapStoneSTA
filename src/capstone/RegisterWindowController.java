/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class RegisterWindowController {
    @FXML
    private Button registerButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/?user=root";
    protected static String REGISTER_QUERY =  "INSERT INTO capstonesta.login "
            + "(username, password) VALUES (?,?)";
    protected static String CHECK_USERNAME_QUERY = "SELECT * FROM capstonesta.login "
            + "WHERE username = ?";

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
    
    @FXML
    private void registerButtonPressed() {
        boolean registerGood = false;
        PreparedStatement ps;

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (username.equals("")) {
            Alert usernameEmptyAlert = new Alert(Alert.AlertType.WARNING);

            usernameEmptyAlert.setTitle("Username is Empty");
            usernameEmptyAlert.setHeaderText("Please Enter a Username");
            usernameEmptyAlert.setContentText("Enter a password into the username field");
            usernameEmptyAlert.showAndWait();
        } else if (password.equals("")) {
            Alert passwordEmptyAlert = new Alert(Alert.AlertType.WARNING);

            passwordEmptyAlert.setTitle("Password is Empty");
            passwordEmptyAlert.setHeaderText("Please Enter a Password");
            passwordEmptyAlert.setContentText("Enter a password into the password field");
            passwordEmptyAlert.showAndWait();
        } else if (checkUsername(username)) {
            Alert usernameSameAlert = new Alert(Alert.AlertType.WARNING);

            usernameSameAlert.setTitle("Username Already in User");
            usernameSameAlert.setHeaderText("Please pick a different username");
            usernameSameAlert.setContentText("Username is in use by a different user please change " +
                    "your current username");
            usernameSameAlert.showAndWait();
        } else {
            registerGood = true;
        }

        try {
            ps = RegisterWindowController.getConnection().prepareStatement(RegisterWindowController.REGISTER_QUERY);

            ps.setString(1, username);
            ps.setString(2, password);

            if (ps.executeUpdate() > 0 && registerGood == true) {
                Parent root;

                try {
                    //grab the currently open window and set it to selectionWindow
                    Stage selectionWindow = (Stage) registerButton.getScene().getWindow();
                    //close the window
                    selectionWindow.close();

                    //create an FXMLLoader called root set to NewHandicapWindow.fxml
                    root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
                    //create a new stage called newHandicapWindow
                    Stage loginWindow = new Stage();
                    loginWindow.setTitle("Handicap Calculator");
                    //set the scene to root
                    loginWindow.setScene(new Scene(root, 600, 400));
                    loginWindow.show();
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
        } catch (SQLException sqlException) {
            //create an Alert called sqlAlert for sqlExceptions
            Alert sqlAlert = new Alert(Alert.AlertType.ERROR);

            sqlAlert.setTitle("ERROR");
            sqlAlert.setHeaderText("SQL Alert");
            sqlAlert.setContentText("Check stack trace");
            
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void backButtonPressed() {
        Parent root;
        try {
            //grab the currently open window and set it to registerWindow
            Stage registerWindow = (Stage) registerButton.getScene().getWindow();
            //close the window
            registerWindow.close();

            //create an FXMLLoader called root set to LoginWindow.fxml
            root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            //create a new stage called loginWindow
            Stage loginWindow = new Stage();
            loginWindow.setTitle("Handicap Calculator");
            //set the scene to root
            loginWindow.setScene(new Scene(root, 600, 400));
            loginWindow.show();
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

    //check that the username hasn't been used before
    private boolean checkUsername(String username) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean checkUsername = false;

        try {
            ps = RegisterWindowController.getConnection().prepareStatement(RegisterWindowController.CHECK_USERNAME_QUERY);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                checkUsername = true;
            }
        } catch (SQLException sqlException) {
            //create an Alert called sqlAlert for sqlExceptions
            Alert sqlAlert = new Alert(Alert.AlertType.ERROR);

            sqlAlert.setTitle("ERROR");
            sqlAlert.setHeaderText("SQL Alert");
            sqlAlert.setContentText("Username is already in use");
            sqlAlert.showAndWait();
            
            sqlException.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (RegisterWindowController.getConnection() != null) {
                try {
                    RegisterWindowController.getConnection().close();
                } catch (SQLException e) { /* ignored */}
            }
        }

        return checkUsername;
    }
}
