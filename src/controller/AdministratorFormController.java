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
    public PieChart pieOrder;

    public void initialize() {
        setDataPicChart();
    }


    private void setDataPicChart() {
        ObservableList<PieChart.Data> productDate = FXCollections.observableArrayList();
        ArrayList<CustomDTO> customDTOS = null;
        try {
            customDTOS = bo.MostSellItem();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < customDTOS.size(); i++) {
            productDate.add(new PieChart.Data(customDTOS.get(i).getDescription(), customDTOS.get(i).getOrderqty()));
        }
        productDate.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " ", data.pieValueProperty())
                )
        );
        pieOrder.setData(productDate);
        pieOrder.setTitle("Most Sell Item");

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
