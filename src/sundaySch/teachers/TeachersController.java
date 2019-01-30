/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.teachers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class TeachersController implements Initializable {
    
       @FXML
    private MenuBar menuTab;

    @FXML
    private JFXTreeTableView<User> treeview;
    @FXML
    private JFXDatePicker dobTxt;
    @FXML
    private JFXComboBox<String> genderCB, maritalCB, statusCB, classCB;
    @FXML
    private JFXTextField phoneTxt, emailTxt, occupationTxt, searchTxt, residenceTxt, nameTxt;
    @FXML
    private JFXButton saveButtn;
    @FXML
    private JFXButton clearButtn, deleteButton, editButton;
    @FXML
    private ProgressIndicator prog;
   
 
    Utility handler = Utility.getInstance();
   ObservableList<String>marital = FXCollections.observableArrayList("Single", "Married");   
   ObservableList<String>gender = FXCollections.observableArrayList("Male", "Female");
   ObservableList<String>status = FXCollections.observableArrayList("Regular", "Helper");
   ObservableList<String>classlist = FXCollections.observableArrayList("Senior Class", "Junior Class",  "Class 5&6", "Class 3&4","Class 1&2","Beginners Class");
  
   ObservableList<User> users = FXCollections.observableArrayList();
   

    
   private void combobox(){
       genderCB.setItems(gender);
       maritalCB.setItems(marital);
       statusCB.setItems(status);
       classCB.setItems(classlist);
   }
   
   private void clear(){
       nameTxt.setText(null);
       occupationTxt.setText(null);
       phoneTxt.setText(null);
       residenceTxt.setText(null);
       emailTxt.setText(null);
       genderCB.setValue(null);
       maritalCB.setValue(null);
       statusCB.setValue(null);
       classCB.setValue(null);
   }
   
   
     private void loadData() throws SQLException{
          
       String qu = "SELECT * from teacher";
        ResultSet rs = handler.execQuery(qu);
        
        while(rs.next()){
            String tID = rs.getString("teacher_id");
            String name = rs.getString("name");
            String genda = rs.getString("gender");
            String DOB = rs.getString("DOB");
            String marita = rs.getString("maritalStatus");
            String occup = rs.getString("occupation");
            String clas = rs.getString("class");
            String stat = rs.getString("status");
            String resid = rs.getString("residence");
            String fone = rs.getString("phone");
            String email = rs.getString("email");
            
            users.addAll(new User(tID, name, genda, DOB, marita, resid, occup, clas, stat, fone, email));
            
            System.out.println(rs);
            
        }
       
    }
 
 
   private void treeView() throws SQLException{
          JFXTreeTableColumn<User,String> tID = new JFXTreeTableColumn<>("Teacher ID");
        tID.setPrefWidth(180);
        tID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().tID;
            }
        });
       
         JFXTreeTableColumn<User,String> tName = new JFXTreeTableColumn<>("Name");
        tName.setPrefWidth(180);
        tName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Name;
            }
        });
        
         JFXTreeTableColumn<User,String> genderCol = new JFXTreeTableColumn<>("Gender");
        genderCol.setPrefWidth(140);
        genderCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Gender;
            }
        });
        
            JFXTreeTableColumn<User,String> DOB = new JFXTreeTableColumn<>("Date Of Birth");
        DOB.setPrefWidth(140);
        DOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().DOB;
            }
        });
        
         JFXTreeTableColumn<User,String> mStatusCol = new JFXTreeTableColumn<>("Marital Status");
        mStatusCol.setPrefWidth(140);
        mStatusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().MaritalStatus;
            }
        });
        
        
        JFXTreeTableColumn<User,String> residenceCol = new JFXTreeTableColumn<>("Residence");
        residenceCol.setPrefWidth(160);
        residenceCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Residence;
            }
        });
        
            JFXTreeTableColumn<User,String> occuptCol = new JFXTreeTableColumn<>("Occupation");
        occuptCol.setPrefWidth(160);
        occuptCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Occupation;
            }
        });
        
         JFXTreeTableColumn<User,String> classCol = new JFXTreeTableColumn<>("Class");
        classCol .setPrefWidth(140);
        classCol .setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Class;
            }
        });
        
        JFXTreeTableColumn<User,String> statusCol = new JFXTreeTableColumn<>("Status");
        statusCol .setPrefWidth(140);
        statusCol .setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Status;
            }
        });
        
        
         JFXTreeTableColumn<User,String> numCol = new JFXTreeTableColumn<>("Phone Number");
        numCol.setPrefWidth(140);
        numCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Number;
            }
        });
        
         JFXTreeTableColumn<User,String> emailCol = new JFXTreeTableColumn<>("Email");
        emailCol.setPrefWidth(160);
        emailCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Email;
            }
        });
        
        
       
      
             loadData();
          
//          users.add(new User("SST001","Cosmos Jojo Hagan","Male","17/06/1991","Single","Nkroful","Programmer","Junior Class","Regular","0273509432","cosmoshagan@gmail.com"));
//          users.add(new User("SST002","Augustina Mensah","Female","03/08/1992","Single","Anaji","Engineer","Junior Class","Helper","0551106565","tina@gmail.com"));

        
        
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(tID,tName, genderCol, DOB, mStatusCol,residenceCol, occuptCol, classCol, statusCol,numCol,emailCol);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        
        searchTxt.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 treeview.setPredicate(new Predicate<TreeItem<User>>() {
                     @Override
                     public boolean test(TreeItem<User> user) {
                         Boolean flag = user.getValue().Name.getValue().contains(newValue)|| user.getValue().Class.getValue().contains(newValue)|| user.getValue().Status.getValue().contains(newValue)||user.getValue().Gender.getValue().contains(newValue)|| user.getValue().MaritalStatus.getValue().contains(newValue) || user.getValue().Residence.getValue().contains(newValue)|| user.getValue().tID.getValue().contains(newValue) ; 
                          return flag;
                     }
                 });
                
             }
         });
        
    }    
   
   

    @FXML
    private void deleteButton(ActionEvent event) {
    }

    @FXML
    private void editButton(ActionEvent event) {
        edit();
    }
   
   
     class User extends RecursiveTreeObject<User>{
        StringProperty tID;
        StringProperty Name;
        StringProperty Gender;
        StringProperty DOB;
        StringProperty MaritalStatus;
        StringProperty Residence;
        StringProperty Occupation;
        StringProperty Class;
        StringProperty Status;
        StringProperty Number;
        StringProperty Email;
        
        public User(String tID,String Name, String Gender,String DOB, String MaritalStatus,String Residence,String Occupation, String Class, String Status, String Number, String Email){
            this.tID = new SimpleStringProperty(tID);
            this.Name = new SimpleStringProperty(Name);
            this.Gender = new SimpleStringProperty(Gender);
            this.DOB = new SimpleStringProperty(DOB);
            this.MaritalStatus = new SimpleStringProperty(MaritalStatus);
            this.Residence = new SimpleStringProperty(Residence);
            this.Occupation = new SimpleStringProperty(Occupation);
            this.Class = new SimpleStringProperty(Class);
            this.Status = new SimpleStringProperty(Status);
            this.Number = new SimpleStringProperty(Number);
            this.Email = new SimpleStringProperty(Email);
        }
    }

   
   

    @FXML
    private void saveButton(ActionEvent event) throws SQLException {
        add();
    }

    @FXML
    private void clearButton(ActionEvent event) {
        clear();
    }
    
 
    /**
     * Initializes the controller class.
     */
   
   
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String me = autoID();
        System.out.println("Auto ID number is "+me);
        
         LocalDate da = LocalDate.now();
         dobTxt.setValue(da);
         
        combobox();
        prog.setVisible(false);
           try {
               treeView();
           } catch (SQLException ex) {
               Logger.getLogger(TeachersController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
   }
    
    private String autoID(){
            String tID = "ST002";
           try {               
               String qu = "SELECT max(teacher_id) from teacher";
               ResultSet rs = handler.execQuery(qu);
               while(rs.next()){
                   int n = Integer.parseInt(tID.substring(3) + 1);
                   int x = String.valueOf(n).length();
                   tID = tID.substring(0, 5-x)+ String.valueOf(n);
               } 
                } catch (SQLException ex) {
               Logger.getLogger(TeachersController.class.getName()).log(Level.SEVERE, null, ex);
           }
           return tID;
    }
    
    
    
   void add() throws SQLException{
        Timeline timeline = new Timeline();
        
        KeyValue key1 = new KeyValue(prog.progressProperty(), 0.01);
        KeyFrame frame1 = new KeyFrame(Duration.millis(550), key1);
        
        KeyValue key2 = new KeyValue(prog.progressProperty(), 1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), key2);
        prog.setVisible(true);
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.play();

        
        String tID = autoID();
    
        String name = nameTxt.getText();
        String genda = genderCB.getValue();
        LocalDate dob = dobTxt.getValue();
        String marit = maritalCB.getValue();
        String stat = statusCB.getValue();
        String claz = classCB.getValue();
        String resid = residenceTxt.getText();
        String occu = occupationTxt.getText();
        String phone = phoneTxt.getText();
        String email = emailTxt.getText();
        
         Boolean flag = (name.isEmpty() || genda.isEmpty() || marit.isEmpty() || resid.isEmpty() || occu.isEmpty() || phone.isEmpty()||stat.isEmpty()|| claz.isEmpty()||email.isEmpty());
         if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
          }
         
         String  st= "INSERT INTO teacher VALUES ("+
              "'" +tID+"'," +    
              "'" +name+"'," +
              "'" +genda+ "'," +
              "'" +dob+ "'," +   
              "'" +marit+ "'," +
              "'" +resid+ "'," +
              "'" +occu+ "'," +
              "'" +claz+ "'," +
              "'" +stat+ "'," +
              "'" +phone+ "'," +
              "'" +email+ "'" +
              ")";
      
      System.out.println(st);
         
        if(handler.execAction(st)){
            timeline.setOnFinished((ActionEvent e) -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Saved");
                alert.show();
                clear();
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
        loadData();
        return;
   }
   
   void edit(){
       Timeline timeline = new Timeline();
        
        KeyValue key1 = new KeyValue(prog.progressProperty(), 0.01);
        KeyFrame frame1 = new KeyFrame(Duration.millis(550), key1);
        
        KeyValue key2 = new KeyValue(prog.progressProperty(), 1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), key2);
        prog.setVisible(true);
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.play();
        if(nameTxt.getText().equals("Cosmos")){
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("UPdated");
                    alert.show();
                    // prog.setVisible(false);
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
   

    
}
