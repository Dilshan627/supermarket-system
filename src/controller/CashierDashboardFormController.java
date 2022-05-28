package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailsBO;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class CashierDashboardFormController {
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Order_Details);
    public AnchorPane context;
    public AnchorPane sideContext;
    public Label lblCustomer;
    public Label lblItem;
    public Label lblOrder;

    public void initialize() {
        try {
            lblCustomer.setText(customerBO.customerCount());
            lblItem.setText(itemBO.itemCount());
            lblOrder.setText(orderDetailsBO.orderCount());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(context, "login");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext, "customer");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext, "place-order");
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(context, "cashier-dashboard");
    }

    public void orderHistoryOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext, "order-history");
    }

}
