/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sundaySch.util.Utility;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class LoginController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXTextField usernameTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXButton signinButton;
    @FXML
    private BorderPane Pane;
    
     Utility util = Utility.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        String path = new File("src/images/jesus2.MP4").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0.01);
        player.play();
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void signin(ActionEvent event) throws SQLException {
        signin();
    }
    
    
    
     
    void signin() throws SQLException{
         String pass = passwordTxt.getText();
         String qu = "SELECT password FROM Login WHERE password = '"+pass+"'";
         ResultSet rs = util.execQuery(qu);
         System.out.println(rs);
      
         
        
            if (rs.next()){
                String sPass = rs.getString("password");
                if (pass.contentEquals(sPass)){
                    try {
                        NotificationType nt = NotificationType.SUCCESS;
                        TrayNotification tray = new TrayNotification();
                        tray.setTitle("System Login");
                        tray.setMessage("SUCCESSFUL");
                        tray.showAndDismiss(Duration.millis(600));
                        tray.setNotificationType(nt);
                        Pane.getScene().getWindow().hide();
                       
                        loadWindow("/sundaySch/DashBoard/DashBoard.fxml", "CTKMC Sunday School System");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               }
                return;
            }
            
             Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setContentText("Wrong Password");
                alert.showAndWait();
                passwordTxt.setText("");
                
               NotificationType nt = NotificationType.ERROR;
           
               TrayNotification tray = new TrayNotification();
               tray.setTitle("System Login");
               tray.setMessage("FAILED");
               tray.showAndDismiss(Duration.millis(600));
               tray.setNotificationType(nt); 
        
    }
    
    void loadWindow(String loc, String title) throws IOException{
        
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.DECORATED);
        Image icon = new Image(getClass().getResourceAsStream("/images/sunsch.png"));
        stage.getIcons().add(icon);  
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
        
    }
    
}


