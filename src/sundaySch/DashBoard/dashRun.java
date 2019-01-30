/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.DashBoard;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author cosmos hagan
 */
public class dashRun extends Application{
     @Override
     public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));   
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("/images/sunsch.png"));
        stage.getIcons().add(icon);
        stage.setTitle("CTKMC Sunday School System");
        stage.show();
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}
