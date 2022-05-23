package controller;

import bo.BOFactory;
import bo.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDTO;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    public JFXButton btnAddNewCustomer;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<CustomerTM> tblCustomers;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtPostalCode;
    public Label txtCustomerId;

    public void initialize() {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CusID"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CusTitle"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CusName"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CusAddress"));
        tblCustomers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("City"));
        tblCustomers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Province"));
        tblCustomers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("PostCode"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCustomerId.setText(newValue.getCusID());
                txtCustomerTitle.setText(newValue.getCusTitle());
                txtCustomerName.setText(newValue.getCusName());
                txtCustomerAddress.setText(newValue.getCusAddress());
                txtCustomerCity.setText(newValue.getCity());
                txtCustomerProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostCode());
            }
        });

        txtCustomerAddress.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomers.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnAddNew_OnAction(ActionEvent actionEvent) {

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}
