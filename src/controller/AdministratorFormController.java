package controller;

import bo.BOFactory;
import bo.custom.AdministratorBO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import model.CustomDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorFormController {
    private final AdministratorBO bo = (AdministratorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Administrator);
    public AnchorPane sideContext;
    public AnchorPane context;
    public PieChart picChart;
    public BarChart barChart;

    public void initialize() {
        try {
            ArrayList<CustomDTO> customDTOS = bo.MostSellItem();
            for (int i = 0; i < customDTOS.size(); i++) {
                System.out.print(customDTOS.get(i).getDescription());
                System.out.println(customDTOS.get(i).getOrderqty());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        orderPicChart();

    }

    private void orderPicChart() {
//        ObservableList<PieChart.Data> productDate = FXCollections.observableArrayList();
//        try {
//            ArrayList<CustomDTO> customDTOS = bo.MostSellItem();
//            for (int i = 0; i < customDTOS.size(); i++) {
//                productDate.add(new PieChart.Data(customDTOS.get(i).getDescription(),customDTOS.get(i).getOrderqty()));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        productDate.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName()," ",data.pieValueProperty())));
//        picChart.setData(productDate);
//        picChart.setTitle("Most Sell Item");



        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("2014");

        dataSeries.getData().add(new XYChart.Data("Desktop", 178));
        dataSeries.getData().add(new XYChart.Data("Phone"  , 65));
        dataSeries.getData().add(new XYChart.Data("Tablet"  , 23));

        barChart.getData().add(dataSeries);


    }




    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(context, "administrator");
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(context, "login");
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext, "item");
    }
}
