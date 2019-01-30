/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.systemInfo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class SystemInfoController implements Initializable {

    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       rotation();
    }  
    
    public void rotation(){
         RotateTransition rt = new RotateTransition(Duration.seconds(35), image);
        rt.setByAngle(9*360);
        rt.play();
    }
    
}
