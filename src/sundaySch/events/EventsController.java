/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javafx.util.Duration;
import sundaySch.util.Utility;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class EventsController implements Initializable {

    @FXML
    private JFXTextField searchTxt, eventName, venueTxt, eventDescription;
    @FXML
    private JFXButton deleteButton, clearButton,saveButton, editButton;
    @FXML
    private ProgressIndicator prog;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTimePicker eventTime;
    @FXML
    private JFXTreeTableView<User> treeview;
 
    String eventID;
    
  
    Utility handler;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalTime t = LocalTime.now();
        LocalDate da = LocalDate.now();
        
        eventDate.setValue(da);
        eventTime.setValue(t);
        
       prog.setVisible(false);
        treeView();
  
    }   
    
    
    
     void add(){
        Timeline timeline = new Timeline();
        
        KeyValue key1 = new KeyValue(prog.progressProperty(), 0.01);
        KeyFrame frame1 = new KeyFrame(Duration.millis(550), key1);
        
        KeyValue key2 = new KeyValue(prog.progressProperty(), 1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), key2);
        prog.setVisible(true);
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.play();

        
        String eID ="EV001";
    
          String name = eventName.getText();
        String venue = venueTxt.getText();
        LocalDate dob = eventDate.getValue();
        LocalTime tym = eventTime.getValue();
        String evntDes = eventDescription.getText();
       
        
         Boolean flag = (name.isEmpty() || venue.isEmpty() || evntDes.isEmpty());
         if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
          }
         
         String  st= "INSERT INTO event VALUES ("+
              "'" +eID+"'," +    
              "'" +name+"'," +
              "'" +venue+ "'," +
              "'" +dob+ "'," +   
              "'" +tym+ "'," +
              "'" +evntDes+ "'" +
              ")";
      
      System.out.println(st);
         
        if(handler.execAction(st)){
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Saved");
                    alert.show();
                   // clear();
                }
                
            });
            
        }else{
             timeline.setOnFinished(e->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Failed");
                alert.show();
               prog.setVisible(false); 
            });
        }
   }
    
    void editButton(){
    
    }

    @FXML
    private void saveButton(ActionEvent event) {
         add();
    }

    @FXML
    private void deleteButton(ActionEvent event) {
        int index=treeview.getSelectionModel().getSelectedIndex();
       // list.remove(index);
    }
    
     @FXML
    private void editButton(ActionEvent event) {
       editButton();
    }

    @FXML
    private void clearButton(ActionEvent event) {
    }
    
    
     
   private void treeView(){
         JFXTreeTableColumn<User,String> eventIDCol = new JFXTreeTableColumn<>("Event ID");
        eventIDCol.setPrefWidth(180);
        eventIDCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().eventID;
            }
        });
       
         JFXTreeTableColumn<User,String> nameCol = new JFXTreeTableColumn<>("Event");
        nameCol.setPrefWidth(180);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().eventName;
            }
        });
        
         JFXTreeTableColumn<User,String> venueCol = new JFXTreeTableColumn<>("Venue");
        venueCol.setPrefWidth(140);
        venueCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().venueTxt;
            }
        });
        
            JFXTreeTableColumn<User,String> dateCol = new JFXTreeTableColumn<>("Date");
        dateCol.setPrefWidth(140);
        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().eventDate;
            }
        });
        
         JFXTreeTableColumn<User,String> timeCol = new JFXTreeTableColumn<>("Time");
        timeCol.setPrefWidth(140);
        timeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().eventTime;
            }
        });
        
        
        JFXTreeTableColumn<User,String> DescriptionCol = new JFXTreeTableColumn<>("Description");
        DescriptionCol.setPrefWidth(160);
        DescriptionCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().eventDescription;
            }
        });
        
        
        
        
        ObservableList<User> users = FXCollections.observableArrayList();
          users.add(new User("EV001","Children's Camp","St.Johns","12/09/2018","7:00 AM","All Children and Teachers Event"));
          users.add(new User("EV002","Teacher's all-night","Fijai Methodist","14/12/2018","7:00 PM","All Teachers Event"));

//        users.add(new User("Cosmos Hagan","Junior Class","0273509432"));
//        users.add(new User("Cosmos Hagan","Junior Class","0273509432"));
      
        
        
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(eventIDCol,nameCol, venueCol, dateCol, timeCol,DescriptionCol);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        
        searchTxt.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 treeview.setPredicate(new Predicate<TreeItem<User>>() {
                     @Override
                     public boolean test(TreeItem<User> user) {
                         Boolean flag = user.getValue().eventName.getValue().contains(newValue)||user.getValue().eventID.getValue().contains(newValue)||user.getValue().venueTxt.getValue().contains(newValue)|| user.getValue().eventDate.getValue().contains(newValue) ; 
                          return flag;
                     }
                 });
                
             }
         });
        
    }    

   
   
   
     class User extends RecursiveTreeObject<User>{
        StringProperty eventID;
        StringProperty eventName;
        StringProperty venueTxt;
        StringProperty eventDate;
        StringProperty eventTime;
        StringProperty eventDescription;

     
        
        public User(String eventID,String eventName, String venueTxt,String eventDate, String eventTime,String eventDescription){
            this.eventID = new SimpleStringProperty(eventID);
            this.eventName = new SimpleStringProperty(eventName);
            this.venueTxt = new SimpleStringProperty(venueTxt);
            this.eventDate = new SimpleStringProperty(eventDate);
            this.eventTime = new SimpleStringProperty(eventTime);
            this.eventDescription = new SimpleStringProperty(eventDescription);
          
        }
    }
     
     
       void progress(){
           
         String eName = eventName.getText();
         String vTxt = venueTxt.getText();
         String eDesc = eventDescription.getText();
         
        prog.setVisible(true);
        Timeline timeline = new Timeline();
        
        KeyValue key1 = new KeyValue(prog.progressProperty(), 0.01);
        KeyFrame frame1 = new KeyFrame(Duration.millis(550), key1);
        
        KeyValue key2 = new KeyValue(prog.progressProperty(), 1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), key2);
        
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.play();
        if(eName.isEmpty()|| vTxt.isEmpty() || eDesc.isEmpty()){
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Enter all Field");
                    alert.show();
                    // prog.setVisible(false);
                }
            });
            
        }else{
             timeline.setOnFinished(e->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Saved");
                alert.show();
               prog.setVisible(false); 
            });
        }
    }
    
}
