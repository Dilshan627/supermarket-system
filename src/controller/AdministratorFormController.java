package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdministratorFormController {
    public AnchorPane sideContext;
    public AnchorPane context;

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/administrator-form.fxml"));
        context.getChildren().add(parent);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/login-form.fxml"));
        context.getChildren().add(parent);
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        sideContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/item-form.fxml"));
        sideContext.getChildren().add(parent);
    }
}
