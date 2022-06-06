package controller;

import bo.BOFactory;
import bo.custom.StoreBo;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StoresFormController {
    private final StoreBo bo = (StoreBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.store);
    public BarChart barStores;

    public void initialize() {
        loadData();
    }

    private void loadData() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Item");

        try {
            ArrayList<ItemDTO> allItem = bo.Item();
            for (ItemDTO item : allItem) {
                dataSeries1.getData().add(new XYChart.Data(item.getDescription(), item.getQtyOnHand()));
            }
            barStores.getData().add(dataSeries1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
