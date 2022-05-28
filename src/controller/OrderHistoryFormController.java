package controller;

import bo.BOFactory;
import bo.custom.OrderDetailsBO;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDTO;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderHistoryFormController {
    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Order_Details);
    public TableView<OrderTM> tblOrder;


    public void initialize() {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CusID"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Total"));

        loadAllOrder();
    }

    private void loadAllOrder() {
        tblOrder.getItems().clear();
        try {
            ArrayList<OrderDTO> order=orderDetailsBO.getAllOrder();
            for (OrderDTO orderDTO:order) {
                tblOrder.getItems().add(new OrderTM(orderDTO.getOrderID(),orderDTO.getCusID(),orderDTO.getOrderDate(),orderDTO.getTotal()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
