package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.util.Utilities;

import java.io.IOException;


public class LoginFormController {
    public AnchorPane context;
    public JFXPasswordField txtPwp;
    public JFXTextField txtUser;
    public JFXButton btnAdministratorSign;
    public Label btnCreate;
    public JFXComboBox cmbStatus;
    public JFXButton btnSignup;
    public JFXButton btnCashierSign;


    public void initialize() {
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.add("Administrator");
        observableList.add("Cashier");
        cmbStatus.setItems(observableList);
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        createVisible();
    }

    public void signupOnAction(ActionEvent actionEvent) {
        if (txtUser.getText().isEmpty() && txtPwp.getText().isEmpty() && cmbStatus.getValue().equals("")) {
            System.out.println("create");
        } else {

            loginVisible();
        }

    }

    public void createVisible() {
        btnAdministratorSign.setVisible(false);
        btnCashierSign.setVisible(false);
        btnCreate.setVisible(false);
        cmbStatus.setVisible(true);
        btnSignup.setVisible(true);
    }

    public void loginVisible() {
        btnAdministratorSign.setVisible(true);
        btnCashierSign.setVisible(true);
        btnCreate.setVisible(true);
        cmbStatus.setVisible(false);
        btnSignup.setVisible(false);
    }

    public void AdministratorSignInOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/Admin-dashboard-form.fxml"));
        context.getChildren().add(parent);
    }

    public void CashierSignInOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/cashier-dashboard-form.fxml"));
        context.getChildren().add(parent);
    }
}
