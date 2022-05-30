package controller;

import bo.BOFactory;
import bo.custom.OrderPayBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class OrderPayFormController {

    private final OrderPayBo orderPayBo = (OrderPayBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Pay);
    public JFXComboBox cmbPay;
    public JFXButton btnPay;
    public Label lblPay;

    public void initialize() {
        try {
            lblPay.setText(orderPayBo.pay());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
