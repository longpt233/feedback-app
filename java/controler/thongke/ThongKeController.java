package controler.thongke;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import model.GroupLetter;
import service.GroupLetterService;
import service.impl.GroupLetterServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

   @FXML
    private Pane chartView;


    private List<GroupLetter> listGroup = new ArrayList<GroupLetter>();
    private GroupLetterService groupLetterService = new GroupLetterServiceIMPL();



    private LineChart createChart() {

        chartView.getChildren().clear();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ten Nhom");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("So Luong");
        try{
            listGroup=groupLetterService.findAll();
        }
        catch(SQLException e){

        };

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("cai gi do");
//        for (GroupLetter groupLetter : listGroup){
//            dataSeries.getData().add(new XYChart.Data(groupLetter.getName(), groupLetter.getQuantity()));
//        }
        dataSeries.getData().add(new XYChart.Data("2010", 16.01));
        dataSeries.getData().add(new XYChart.Data("2012", 18.90));
        dataSeries.getData().add(new XYChart.Data("2014", 17.05));
        dataSeries.getData().add(new XYChart.Data("2016", 16.06));
        dataSeries.getData().add(new XYChart.Data("2018", 20.22));
        dataSeries.getData().add(new XYChart.Data("2020", 23.22));

        LineChart chart = new LineChart(xAxis, yAxis);
        chart.setMaxWidth(700);
        chart.setMaxHeight(600);
        chart.setPrefWidth(700);
        chart.setPrefHeight(600);
        chart.setStyle("-fx-width: 100px;");
        chart.getData().addAll(dataSeries);
        chart.setTitle("Top Programming Languages");

        chartView.getChildren().add(chart);
        return chart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createChart();
    }


}