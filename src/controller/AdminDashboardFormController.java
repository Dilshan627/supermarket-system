package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashboardFormController {
    public AnchorPane context;
    public JFXButton btnAddNewItem;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView tblItems;
    public JFXTextField txtPackageSize;


    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/login-form.fxml"));
        context.getChildren().add(parent);
    }


    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}
