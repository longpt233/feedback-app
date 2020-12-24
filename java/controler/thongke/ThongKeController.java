package controler.thongke;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.GroupLetter;
import model.Letter;
import model.Problem;
import service.GroupLetterService;
import service.LetterService;
import service.ProblemService;
import service.impl.GroupLetterServiceIMPL;
import service.impl.LetterServiceIMPL;
import service.impl.ProblemServiceIMPL;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class ThongKeController implements Initializable {

   @FXML
    private Pane chartView;

   @FXML
   private TextField ButtonQuy, ButtonNam;

   @FXML
   private Button butProb, butStat;


    private List<GroupLetter> listGroup = new ArrayList<GroupLetter>();
    private GroupLetterService groupLetterService = new GroupLetterServiceIMPL();
    private LetterService letterService = new LetterServiceIMPL();
    private ProblemService problemService = new ProblemServiceIMPL();



    private BarChart createBarChart(List<Letter> listLetter) {
        Dictionary dicProblem = new Hashtable();// dic cac van de trong quy

        List<Problem> listProblem = new ArrayList<>();// tat ca van de trong du lieu
        try{
            listProblem=problemService.findAll();
        }
        catch (SQLException e){

        }
        for (Problem pro : listProblem){
            dicProblem.put(pro.getName(), 1);
        }

// tao dictionary cac van de
        for(Letter letter : listLetter){
            String tmp = letter.getProblem();
            dicProblem.put(tmp,(int)dicProblem.get(tmp)+1);

        }
        System.out.println(dicProblem);

        chartView.getChildren().clear();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Vấn Đề");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số Lượng");

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("cai gi do");


        for (Enumeration k = dicProblem.keys(); k.hasMoreElements();)
        {
//          dataSeries.getData().add(new XYChart.Data(k, dicProblem.get(k)));
            String x = (String) k.nextElement();
            dataSeries.getData().add(new XYChart.Data(x, dicProblem.get(x)));

        }

        BarChart chart = new BarChart(xAxis, yAxis);
        chart.setMaxWidth(700);
        chart.setMaxHeight(600);
        chart.setPrefWidth(700);
        chart.setPrefHeight(600);
        chart.setStyle("-fx-width: 100px;");
        chart.getData().addAll(dataSeries);
        chart.setTitle("Thống kê nhóm");


        chartView.getChildren().add(chart);
        return chart;
    }

    private PieChart createPieChart(List<Letter> listLetter){
        Integer soLuongTT1 = new Integer(0);
        Integer soLuongTT2 = new Integer(0);
        Integer soLuongTT3 = new Integer(0);

        for(Letter letter : listLetter){
            if(letter.getStatusLetter()==1) soLuongTT1+=1;
            if(letter.getStatusLetter()==2) soLuongTT2=soLuongTT2+1;
            if(letter.getStatusLetter()==3) soLuongTT3=soLuongTT3+1;


        }
        chartView.getChildren().clear();

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Có Thế Giải Quyết", soLuongTT1);
        PieChart.Data slice2 = new PieChart.Data("không Thế Giải Quyết", soLuongTT2);
        PieChart.Data slice3 = new PieChart.Data("Chờ Xem Xét", soLuongTT3);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        pieChart.setMaxWidth(700);
        pieChart.setMaxHeight(600);
        pieChart.setPrefWidth(700);
        pieChart.setPrefHeight(600);
        pieChart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        double total = 0;
                        for (PieChart.Data d : pieChart.getData()) {
                            total += d.getPieValue();
                        }
                        caption.setTranslateX(e.getSceneX()-300);
                        caption.setTranslateY(e.getSceneY()-200);
                        String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                        caption.setText(text);
                    }
            );
        }

        chartView.getChildren().addAll(pieChart, caption);
        return pieChart;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        List<Letter> a = new ArrayList<Letter>();
        butProb.setOnAction(ActionEvent->{
            chartView.toFront();

            try{
                String quy = ButtonQuy.getText();
                String nam = ButtonNam.getText();
                String dayAndMonth1=new String();
                String dayAndMonth2=new String();

                if(quy.equals("1")){
                    dayAndMonth1="-01-01";
                    dayAndMonth2="-03-31";

                }
                if(quy.equals("2")){
                    dayAndMonth1="-04-01";
                    dayAndMonth2="-06-31";
                }
                if(quy.equals("3")){
                    dayAndMonth1="-07-01";
                    dayAndMonth2="-09-31";
                }
                if(quy.equals("4")){
                    dayAndMonth1="-10-01";
                    dayAndMonth2="-12-31";
                }

                Date x = Date.valueOf(nam+dayAndMonth1);
                Date y = Date.valueOf(nam+dayAndMonth2);
                List<Letter> a  = letterService.findByApplyDate(x, y);
                createBarChart(a);

            }
            catch (Exception e){
                String nam = ButtonNam.getText();

                Date x = Date.valueOf(nam+"-01-01");
                Date y = Date.valueOf(nam+"-12-31");
                try{
                    List<Letter> a  = letterService.findByApplyDate(x, y);
                    createBarChart(a);
                }
                catch (SQLException e2){

                }

            }
        });


        butStat.setOnAction(ActionEvent->{

            try{
                String quy = ButtonQuy.getText();
                String nam = ButtonNam.getText();
                String dayAndMonth1=new String();
                String dayAndMonth2=new String();

                if(quy.equals("1")){
                    dayAndMonth1="-01-01";
                    dayAndMonth2="-03-31";

                }
                if(quy.equals("2")){
                    dayAndMonth1="-04-01";
                    dayAndMonth2="-06-31";
                }
                if(quy.equals("3")){
                    dayAndMonth1="-07-01";
                    dayAndMonth2="-09-31";
                }
                if(quy.equals("4")){
                    dayAndMonth1="-10-01";
                    dayAndMonth2="-12-31";
                }

                Date x = Date.valueOf(nam+dayAndMonth1);
                Date y = Date.valueOf(nam+dayAndMonth2);
                List<Letter> a  = letterService.findByApplyDate(x, y);
                createPieChart(a);

            }
            catch (Exception e){
                String nam = ButtonNam.getText();

                Date x = Date.valueOf(nam+"-01-01");
                Date y = Date.valueOf(nam+"-12-31");
                try{
                    List<Letter> a  = letterService.findByApplyDate(x, y);
                    createPieChart(a);
                }
                catch (SQLException e2){

                }

            }

        });
    }


}