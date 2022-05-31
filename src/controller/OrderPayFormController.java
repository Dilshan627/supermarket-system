package controller;

import bo.BOFactory;
import bo.custom.OrderPayBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class OrderPayFormController {

    private final OrderPayBo orderPayBo = (OrderPayBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Pay);
    public JFXComboBox cmbPay;
    public Label lblPay;
    public AnchorPane context;

    public void initialize() {
        try {
            lblPay.setText(orderPayBo.pay());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList list= FXCollections.observableArrayList();
        list.add("cash");
        list.add("visa");
        cmbPay.setItems(list);
    }

    public void payOnAction(ActionEvent actionEvent) {
        if (!cmbPay.getValue().equals("")){
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Successfully...!").show();

            Stage stage = (Stage) context.getScene().getWindow();
            stage.close();
        }
    }

}
