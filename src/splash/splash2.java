/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author cosmos hagan
 */
public class splash2 extends Application{
    
      @Override
   public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("/splash/splashView.fxml"));   
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    
      public static void main(String[] args) {
        launch(args);
    }
}
