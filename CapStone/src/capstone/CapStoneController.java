// CapStoneController.java
package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.CheckBox;

public class CapStoneController {

    @FXML private ScrollPane addressPane;
    @FXML private ScrollPane checkInPane;

    @FXML CheckBox box = new CheckBox (" ");

    @FXML
    private void createNewCheckBox()
    {
//        String DATABASE_URL = "jdbc:mysql://localhost@3306";
//        //returns a number that is equal to the amount of children in the table
//        String CHILD_LENGTH = "Select idChild COUNT(*) FROM capstonesta.child";
//        String CHILD_NAME = "Select first_name, last_name FROM capstonesta.child";
//
//        try (Connection connection = DriverManager.getConnection(DATABASE_URL,
//                "root", "yoast");
//
//        Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(CHILD_LENGTH)) {
//            //as long as there is more in the query
//            while (resultSet.next()) {
//                //set ammountOfChildren to the result of the query
//                Integer ammountOfChildren = Integer.parseInt(resultSet.getString("idChild"));
//
//
//                for (int i = 1; i <= ammountOfChildren; i++) {
//                    CheckBox box = new CheckBox(" ");
//                }
//
//                System.out.println();
//            }
//
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//
//        }

        final String DB_URl = "jdbc:mysql://localhost:3306/child?zeroDateTimeBehavior=convertToNull";
       final String SEL_QUERY = "select first_name, last_name from child";
       try (
               Connection connection = DriverManager.getConnection(DB_URl,"root","yoast");
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(SEL_QUERY) ){

           while (resultSet.next()) {
                //set ammountOfChildren to the result of the query
                Integer ammountOfChildren = Integer.parseInt(resultSet.getString("idChild"));


                for (int i = 1; i <= ammountOfChildren; i++) {
                    CheckBox box = new CheckBox(" ");
                }

                System.out.println();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

    }

//        try (Connection nameConnection = DriverManager.getConnection(DATABASE_URL, "root", "yoast");
//
//        Statement nameStatement = connection.createStatement());
//
//            ResultSet nameResultSet = nameStatement.executeQuery(CHILD_NAME) {
//
//            while (nameResultSet.next()) {
//
//            }
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
}
