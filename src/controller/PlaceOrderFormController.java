package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class PlaceOrderFormController {

    public JFXComboBox cmbCustomerId;
    public JFXTextField txtCustomerName;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public TableView tblOrderDetails;
    public Label lblId;
    public Label lblDate;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public JFXTextField txtDiscount;
    public JFXButton btnDelete;

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}
