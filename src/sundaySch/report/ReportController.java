/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sundaySch.report;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author cosmos hagan
 */
public class ReportController implements Initializable {

    @FXML
    private JFXButton loadButton;

    @FXML
    private BarChart<?, ?> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         loader();
        
    }    

    @FXML
    private void loadButton(ActionEvent event) {
      loader();

    }
    
    public void loader(){
        
         XYChart.Series series1 = new XYChart.Series();
        series1.getData().addAll(new XYChart.Data<>("TEACHERS", 100));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.getData().addAll(new XYChart.Data<>("STUDENTS", 200));
        
          XYChart.Series series3 = new XYChart.Series();
        series3.getData().addAll(new XYChart.Data<>("MALE (Student)", 110));
        
        XYChart.Series series4 = new XYChart.Series();
        series4.getData().addAll(new XYChart.Data<>("FEMALE (Student)", 90));
        
        barChart.getData().addAll(series1,series2,series3,series4);

    }
    
}
