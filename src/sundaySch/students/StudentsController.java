/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.students;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressIndicator;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class StudentsController implements Initializable {

    @FXML
    private MenuBar menuTab;

    @FXML
    private JFXTreeTableView<User> treeview;

    @FXML
    private JFXComboBox<String> genderCB;

    @FXML
    private JFXDatePicker dobTxt;

    @FXML
    private JFXTextField mothersTxt, mothersNumTxt, fathersNumTxt, fathersTxt;
    
    @FXML
    private JFXComboBox<String> classCB;

    @FXML
    private JFXComboBox<String> schoolCB;

    @FXML
    private JFXTextField nameSchTxt, phoneTxt, residenceTxt,searchTxt, nameTxt;

    @FXML
    private JFXButton clearButtn, deleteButton, saveButtn;

    @FXML
    private ProgressIndicator prog;
    @FXML
    private JFXButton editButton;

    
   ObservableList<String>gender = FXCollections.observableArrayList("Male", "Female");
   ObservableList<String>schlist = FXCollections.observableArrayList("Tertiary", "Secondary",  "Primary");
   ObservableList<String>classlist = FXCollections.observableArrayList("Senior Class", "Junior Class",  "Class 5&6", "Class 3&4","Class 1&2","Beginners Class");
  
    Utility handler;

    private void treeView() {
        JFXTreeTableColumn<User, String> sID = new JFXTreeTableColumn<>("Student ID");
        sID.setPrefWidth(150);
        sID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().sID;
            }
        });
        
        JFXTreeTableColumn<User, String> tName = new JFXTreeTableColumn<>("Name");
        tName.setPrefWidth(150);
        tName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Name;
            }
        });

        JFXTreeTableColumn<User, String> genderCol = new JFXTreeTableColumn<>("Gender");
        genderCol.setPrefWidth(150);
        genderCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Gender;
            }
        });

        JFXTreeTableColumn<User, String> DOB = new JFXTreeTableColumn<>("Date Of Birth");
        DOB.setPrefWidth(150);
        DOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().DOB;
            }
        });

        JFXTreeTableColumn<User, String> mothersNamCol = new JFXTreeTableColumn<>("Mother's Name");
        mothersNamCol.setPrefWidth(150);
        mothersNamCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().MothersName;
            }
        });

        JFXTreeTableColumn<User, String> mothersNumCol = new JFXTreeTableColumn<>("Mother's Number");
        mothersNumCol.setPrefWidth(150);
        mothersNumCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().MothersNumb;
            }
        });

        JFXTreeTableColumn<User, String> fatherNamCol = new JFXTreeTableColumn<>("Father's Name");
        fatherNamCol.setPrefWidth(150);
        fatherNamCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().FathersName;
            }
        });


        JFXTreeTableColumn<User, String> fathersNumCol = new JFXTreeTableColumn<>("Father's Number");
        fathersNumCol.setPrefWidth(150);
        fathersNumCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().FathersNum;
            }
        });

        JFXTreeTableColumn<User, String> residenceCol = new JFXTreeTableColumn<>("Residence");
        residenceCol.setPrefWidth(150);
        residenceCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Residence;
            }
        });
        
        
        
        JFXTreeTableColumn<User, String> classCol = new JFXTreeTableColumn<>("Class");
        classCol.setPrefWidth(150);
        classCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Class;
            }
        });

        
        JFXTreeTableColumn<User, String> schoolCol = new JFXTreeTableColumn<>("School");
        schoolCol.setPrefWidth(150);
        schoolCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().School;
            }
        });
        
        
        
        JFXTreeTableColumn<User, String> numCol = new JFXTreeTableColumn<>("Phone Number");
        numCol.setPrefWidth(150);
        numCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Number;
            }
        });

       

        ObservableList<User> users = FXCollections.observableArrayList();
         users.add(new User("ST001","Pricilla Arthur", "Female", "03/04/2002", "Sarah Arthur", "0247580432", "Ebenezer Arthur", "0547845213", "Effia-Kuma", "Senior Class", "Senior High", "Fijai","0240784563"));
        users.add(new User("ST002","Joycelyn Ackon", "Female", "23/10/2002", "Hannah Ackon", "0247580432", "Ernest Ackon", "0547845213", "WestLine", "Senior Class", "Senior High", "Secko","0240784563"));

         
       //   final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
         
      final TreeItem<User> root = new RecursiveTreeItem<User>(users,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(sID,tName, genderCol, DOB, mothersNamCol, mothersNumCol, fatherNamCol, fathersNumCol, residenceCol, classCol, schoolCol, numCol);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        
        
        
        searchTxt.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 treeview.setPredicate(new Predicate<TreeItem<User>>() {
                     @Override
                     public boolean test(TreeItem<User> user) {
                         Boolean flag = user.getValue().Name.getValue().contains(newValue)|| user.getValue().Class.getValue().contains(newValue)||user.getValue().Gender.getValue().contains(newValue)|| user.getValue().School.getValue().contains(newValue) || user.getValue().Residence.getValue().contains(newValue)|| user.getValue().DOB.getValue().contains(newValue) ; 
                          return flag;
                     }
                 });
                
             }
         });

    }

    @FXML
    private void editButton(ActionEvent event) {
    }

   
      private void combobox(){
       genderCB.setItems(gender);
       schoolCB.setItems(schlist);
       classCB.setItems(classlist);
   }
    
    
    class User extends RecursiveTreeObject<User> {
        StringProperty sID;
        StringProperty Name;
        StringProperty Gender;
        StringProperty DOB;
        StringProperty MothersName;
        StringProperty MothersNumb ;
        StringProperty FathersName;
        StringProperty FathersNum;
        StringProperty Residence;
        StringProperty Class;
        StringProperty School;
        StringProperty NameSch;
        StringProperty Number;
        

        public User(String sID,String Name, String Gender, String DOB, String MothersName, String MothersNumb,String FathersName, String FathersNum, String Residence, String Class,  String School, String NameSch, String Number) {
            
            this.sID = new SimpleStringProperty(sID);
            this.Name = new SimpleStringProperty(Name);
            this.Gender = new SimpleStringProperty(Gender);
            this.DOB = new SimpleStringProperty(DOB);
            this.MothersName = new SimpleStringProperty(MothersName);
            this.MothersNumb = new SimpleStringProperty(MothersNumb );
            this.FathersName = new SimpleStringProperty(FathersName);
            this.FathersNum = new SimpleStringProperty(FathersNum);
            this.Residence = new SimpleStringProperty(Residence);
            this.Class = new SimpleStringProperty(Class);
            this.School = new SimpleStringProperty(School);
            this.NameSch = new SimpleStringProperty(NameSch);
            this.Number = new SimpleStringProperty(Number);
           
        }
    }
    
     @FXML
    private void deleteButton(ActionEvent event) {
    }


    @FXML
    void clearButton(ActionEvent event) {

    }

    @FXML
    void saveButton(ActionEvent event) {
        add();
       
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

        
        
        String tID ="ST001";
    
        String name = nameTxt.getText();
        String genda = genderCB.getValue();
        LocalDate dob = dobTxt.getValue();
        String stat = schoolCB.getValue();
        String claz = classCB.getValue();
        String resid = residenceTxt.getText();
      String sch = nameSchTxt.getText();
        String phone = phoneTxt.getText();
       String mother = mothersTxt.getText();
       String motherNum = mothersNumTxt.getText();
       String father = fathersTxt.getText();
       String fatherNum = fathersNumTxt.getText();
        
         Boolean flag = (name.isEmpty() || genda.isEmpty() || genda.isEmpty() || resid.isEmpty() || mother.isEmpty()|| motherNum.isEmpty()|| father.isEmpty()|| fatherNum.isEmpty()  || phone.isEmpty()||stat.isEmpty()|| claz.isEmpty()||sch.isEmpty());
         if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
          }
         
         String  st= "INSERT INTO student VALUES ("+
              "'" +tID+"'," +    
              "'" +name+"'," +
              "'" +genda+ "'," +
              "'" +dob+ "'," +   
              "'" +mother+ "'," +
              "'" +motherNum+ "'," +
              "'" +father+ "'," +
              "'" +fatherNum+ "'," +
              "'" +resid+ "'," +
              "'" +stat+ "'," +
              "'" +sch+ "'," +
              "'" +claz+ "'," +
              "'" +phone+ "'" +
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LocalDate da = LocalDate.now();
         dobTxt.setValue(da);
        combobox();
        prog.setVisible(false);
        treeView();
        
    }
    
    
    void progress(){
        prog.setVisible(true);
        Timeline timeline = new Timeline();
        
        KeyValue key1 = new KeyValue(prog.progressProperty(), 0.01);
        KeyFrame frame1 = new KeyFrame(Duration.millis(550), key1);
        
        KeyValue key2 = new KeyValue(prog.progressProperty(), 1);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(1), key2);
        
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.play();
        if(nameTxt.getText().equals("Cosmos")){
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Saved");
                    alert.show();
                    
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
