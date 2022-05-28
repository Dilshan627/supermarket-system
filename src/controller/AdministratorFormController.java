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
        util.navigation.navigate(context,"administrator");
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(context,"login");
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        util.navigation.navigate(sideContext,"item");
    }
}
