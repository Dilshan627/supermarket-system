package controller;

import bo.BOFactory;
import bo.custom.OrderDetailsBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDTO;
import model.OrderDetailDTO;
import view.tm.OrderDetailTM;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderHistoryFormController {
    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Order_Details);
    public TableView<OrderDetailTM> tblOrder;
    public JFXTextField txtId;


    public void initialize() {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Orderqty"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Discount"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Price"));

    }


    public void searchonAction(ActionEvent actionEvent) {
        try {
            ArrayList<OrderDetailDTO> list = orderDetailsBO.searchOrder(txtId.getText());
            for (OrderDetailDTO orderDTO:list) {
                tblOrder.getItems().add(new OrderDetailTM(orderDTO.getOrderID(),orderDTO.getItemCode(),orderDTO.getOrderqty(),orderDTO.getDiscount(),orderDTO.getPrice()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
