package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CashierDashboardFormController {
    public AnchorPane context;
    public AnchorPane sideContext;


    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/login-form.fxml"));
        context.getChildren().add(parent);
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        sideContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/customer-form.fxml"));
        sideContext.getChildren().add(parent);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/cashier-dashboard-form.fxml"));
        context.getChildren().add(parent);
    }

    public void orderHistoryOnAction(ActionEvent actionEvent) {
    }
}
