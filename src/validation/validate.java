/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;

/**
 *
 * @author cosmos hagan
 */
public class validate {
     public static boolean isTextfieldNotEmpty(JFXTextField tf){
        boolean b = false;
        if(tf.getText().length() != 0 || !tf.getText().isEmpty()) {
            b = true;
        }
        return b;
}
     
     public static boolean isTextfieldNotEmpty(JFXTextField tf, String errorMessage){
        boolean b = false;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isTextfieldNotEmpty(tf)) {
            b = true;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        System.out.println(msg);
        return b;
    }
     
    
       public static boolean isPasswordFieldNotEmpty(JFXPasswordField tf){
        boolean b = false;
        if(tf.getText().length() != 0 || !tf.getText().isEmpty()) {
            b = true;
        }
        return b;
    }
    
     public static boolean isPasswordFieldNotEmpty(JFXPasswordField tf, String errorMessage){
        boolean b = false;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isPasswordFieldNotEmpty(tf)) {
            b = true;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        System.out.println(msg);
        return b;
    }
     
     
     
     
     public static boolean textFieldTypeNumber(JFXTextField tf){
         boolean b = false;
         
         if(tf.getText().matches("(2-5)?[0-9][0-9]{8}"))
           b = true;
         
        return b;
     }
     
     public static boolean textFieldTypeNumber(JFXTextField tf, String errorMessage){
         boolean b = true;
         String msg = null;
           tf.getStyleClass().remove("error");
           
         if(!textFieldTypeNumber(tf)) {
             b = true;
         
           tf.getStyleClass().add("error");
         
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setContentText("Please Enter a Valid Mobile Number\nEnter only 9 digits ignoring 0 or +233");
          alert.showAndWait();
         
         }  
          
         
             msg = errorMessage;
         
         System.out.println(msg);
         return b;
     }
     
     
     
    public static boolean isComboBoxEmpty(JFXComboBox cb){
        boolean b = false;
       if(!cb.getSelectionModel().isEmpty()){ 
           
            b = true;
       }
        return b;
    }
    
     public static boolean isComboBoxEmpty(JFXComboBox cb, String errorMessage){
        boolean b = false;
        String msg = null;
        cb.getStyleClass().remove("error");
        if(!isComboBoxEmpty(cb)) {
            b = true;
            msg = errorMessage;
            cb.getStyleClass().add("error");  
        }
        System.out.println(msg);
        return b;
    }
     

}
     
 
