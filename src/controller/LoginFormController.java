package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;


public class LoginFormController {
    public AnchorPane context;
    public JFXPasswordField txtPwp;
    public JFXTextField txtUser;
    public JFXButton btnAdministratorSign;
    public JFXButton btnCashierSign;


    public void AdministratorSignInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUser.getText().equals("a") && txtPwp.getText().equals("1"))
            util.navigation.navigate(context, "administrator");
        else
            new Alert(Alert.AlertType.ERROR, "username or password invalid").show();
    }

    public void CashierSignInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUser.getText().equals("c") && txtPwp.getText().equals("1"))
            util.navigation.navigate(context, "cashier-dashboard");
        else
            new Alert(Alert.AlertType.ERROR, "username or password invalid").show();
    }
}
