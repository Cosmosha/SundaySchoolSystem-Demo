/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.DashBoard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.dialog.ProgressDialog;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class DashBoardController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane pane4;
    @FXML
    private JFXButton teacherButton1;
    @FXML
    private JFXButton studentButtn1;
    @FXML
    private JFXButton statisticsButton1;
    @FXML
    private JFXButton actButton1;
    @FXML
    private JFXButton budget1;
    @FXML
    private JFXButton systemInfo1;

    /**
     * Initializes the controller class.
     */
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         pane1.setStyle("-fx-background-image: url(\"/images/ssch99.jpg\")");
         pane2.setStyle("-fx-background-image: url(\"/images/ssch8.jpg\")");
         pane3.setStyle("-fx-background-image: url(\"/images/ssch3.jpg\")");
         pane4.setStyle("-fx-background-image: url(\"/images/ssch2.jpg\")");
         Animation();
         menu();
    }
    

    void loader() {

        Task<Object> work = new Task<Object>() {
            @Override
            protected Object call() throws Exception {

                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 99);
                    updateMessage("Loading: " + i);
                    Thread.sleep(30);
                }

                return null;
            }

        };
        ProgressDialog ps = new ProgressDialog(work);
        ps.initStyle(StageStyle.UTILITY);
        ps.setTitle("Sunday School System");
       
        Thread th = new Thread(work);
        th.setDaemon(true);
        th.start();

        
    }
    

    void getWindow(String loc, String title) {

        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            Image icon = new Image(getClass().getResourceAsStream("/images/sunsch.png"));
            stage.getIcons().add(icon);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    void showWindow(String loc, String title) {

        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.UTILITY);
            Image icon = new Image(getClass().getResourceAsStream("/images/sunsch.png"));
            stage.getIcons().add(icon);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
      
    void logWindow(String loc, String title) {

        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void Animation(){
       
         FadeTransition fadeTrans = new FadeTransition(Duration.seconds(3),pane4);
         fadeTrans.setFromValue(1);
         fadeTrans.setToValue(0);
         fadeTrans.play();
         
         fadeTrans.setOnFinished(event -> {
         FadeTransition fadeTrans1 = new FadeTransition(Duration.seconds(3),pane3);
         fadeTrans1.setFromValue(1);
         fadeTrans1.setToValue(0);
         fadeTrans1.play();
         
                fadeTrans1.setOnFinished(event1 -> {
                FadeTransition fadeTrans2 = new FadeTransition(Duration.seconds(3),pane2);
                fadeTrans2.setFromValue(1);
                fadeTrans2.setToValue(0);
                fadeTrans2.play();
                
                fadeTrans2.setOnFinished(event2 ->{
                FadeTransition fadeTrans3 = new FadeTransition(Duration.seconds(3),pane2);
                fadeTrans3.setFromValue(0);
                fadeTrans3.setToValue(1);
                fadeTrans3.play();
                
                    fadeTrans3.setOnFinished(event3 ->{
                     FadeTransition fadeTrans4 = new FadeTransition(Duration.seconds(3),pane3);
                    fadeTrans4.setFromValue(0);
                    fadeTrans4.setToValue(1);
                    fadeTrans4.play(); 
                    
                        fadeTrans4.setOnFinished(event4 -> {
                         FadeTransition fadeTrans5 = new FadeTransition(Duration.seconds(3),pane4);
                        fadeTrans5.setFromValue(0);
                        fadeTrans5.setToValue(1);
                        fadeTrans5.play(); 
                        
                            fadeTrans5.setOnFinished(event5 -> {
                                Animation();
                            
                            });
                        
                        });
                    });
                });
             });
            
         });
        
    }

    @FXML
    private void teachButton(ActionEvent event) {
          loader();
      //  ((Node)event.getSource()).getScene().getWindow().hide();
          getWindow("/sundaySch/teachers/teachers.fxml", "CTK Sunday School System");
    }

    @FXML
    private void studentButton(ActionEvent event) {
          loader();
      //  ((Node)event.getSource()).getScene().getWindow().hide();
        getWindow("/sundaySch/students/students.fxml", "CTK Sunday School System");
    }

    @FXML
    private void statisticsButton(ActionEvent event) {
        getWindow("/sundaySch/report/report.fxml", "CTK Sunday School System");
    }

    @FXML
    private void actButton(ActionEvent event) {
        loader();
        getWindow("/sundaySch/events/events.fxml", "CTK Sunday School System");
    }

    @FXML
    private void budgetButton(ActionEvent event) {
    }

    @FXML
    private void InfoButton(ActionEvent event) {
        showWindow("/sundaySch/systemInfo/systemInfo.fxml", "CTK Sunday School System");
    }
    
    public void menu(){     
        try {
            VBox box = FXMLLoader.load(getClass().getResource("slideMenu.fxml"));
            drawer.setSidePane(box);
            
            for (Node node : box.getChildren()){
                if(node.getAccessibleText()!= null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                        switch(node.getAccessibleText()){
                        
                            case "Logout":
                            {
                                 node.getScene().getWindow().hide();
                                 logWindow("/sundaySch/login/login.fxml", "CTK Sunday School System");
                            }
                            
                            break;
                            case "Exit" :
                                System.exit(0);
                        }
                    });
                }
            }
            
            
            
            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                burgerTask2.setRate(burgerTask2.getRate()* -1);
                burgerTask2.play();
                
                if(drawer.isShown()){
                    drawer.close();
                }else{
                    drawer.open();
                }
            });   
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
