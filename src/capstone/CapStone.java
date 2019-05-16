/*
Name: Greg Heiman
Class: Yoast PM
Date: 3/29/19
Desc: Movie Theater Project
*/

package capstone;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CapStone extends Application {
    
    /**
     * Method start()
     * @param stage - the window which the UI starts in
     * @throws Exception - is something wrong happens
     * @return void
     */
    @Override
    public void start(Stage stage) throws Exception {
        //set root equal to the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("CapStone.fxml"));
        
        //create a new scene based on root called scene
        Scene scene = new Scene(root);
        //set the stages title to Movie Theater
        stage.setTitle("CapStone");
        //set the stage to scene
        stage.setScene(scene);
        //show stage
        stage.show();
    }

    /**
     * Method main()
     * Start of the program
     * @param args - String array for the arguments to start the program
     * @return void
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
