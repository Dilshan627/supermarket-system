package controller;

import bo.BOFactory;
import bo.custom.AdministratorBO;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
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
    public BarChart barChart;

    public void initialize() {
        orderBarChart();
    }

    private void orderBarChart() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Most Sell Item");
        ArrayList<CustomDTO> customDTOS = null;
        try {
            customDTOS = bo.MostSellItem();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < customDTOS.size(); i++) {
                dataSeries.getData().add(new XYChart.Data(customDTOS.get(i).getDescription(),customDTOS.get(i).getOrderqty()));
            }
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

    public void inComeOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext, "income");
    }
}
