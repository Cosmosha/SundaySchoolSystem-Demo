/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class SplashViewController implements Initializable {

    @FXML
    private AnchorPane Pane;
    @FXML
    private Label systemLabel;
    @FXML
    private Label message;
    @FXML
    private Pane spinnerPane;
    @FXML
    private Label logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         splash();
         new splashView().start();
    }    
    
     void splash(){
        TranslateTransition trans0 = new TranslateTransition(Duration.seconds(0.5),logo); 
        trans0.setByY(700);
        trans0.play();
        
        TranslateTransition trans00 = new TranslateTransition(Duration.seconds(0.5),systemLabel); 
        trans00.setByY(700);
        trans00.play();
        
        TranslateTransition trans000 = new TranslateTransition(Duration.seconds(0.5),message); 
        trans000.setByY(700);
        trans000.play();
        
           
       
            
            
            trans0.setOnFinished(event1 ->{
               logo.setVisible(true);
               
            TranslateTransition trans11 = new TranslateTransition(Duration.seconds(1),logo); 
            trans11.setByY(-700);
            trans11.play();
            
            trans11.setOnFinished(event2 ->{
            
               systemLabel.setVisible(true);
            
                TranslateTransition trans111 = new TranslateTransition(Duration.seconds(1),systemLabel); 
                trans111.setByY(-700);
                trans111.play();
                
                
               trans111.setOnFinished(event3 ->{
                   message.setVisible(true);
               TranslateTransition trans14 = new TranslateTransition(Duration.seconds(1),message); 
                trans14.setByY(-700);
                trans14.play();
                
                trans14.setOnFinished(event4 ->{
                      spinnerPane.setVisible(true);
                    FadeTransition ft = new FadeTransition(Duration.seconds(1),spinnerPane);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    
                    
                  ft.setOnFinished(event5 ->{
                      FadeTransition ft1 = new FadeTransition(Duration.seconds(3.5),Pane);
                      ft1.setFromValue(1);
                      ft1.setToValue(0.1);
                      ft1.play();
                      
                      ft1.setOnFinished(event6 ->{
                         Pane.getScene().getWindow().hide();
                    });
                  });               
                });
              });           
            });
        });
    }  
}

class splashView extends Thread{
        
        @Override
        public void run(){
               
            try {
                Thread.sleep(8000);
                
                    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                         
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/sundaySch/login/login.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UNDECORATED); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SplashViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

             } catch (Exception e) {
            }
        }
    }
