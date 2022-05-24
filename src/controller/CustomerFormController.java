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
import view.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
       // loadAllCustomers();
        txtCustomerId.setText(generateNewId());
    }

    private void loadAllCustomers() {
        tblCustomers.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(customer.getCusID(), customer.getCusTitle(), customer.getCusName(),customer.getCusAddress(),customer.getCity(),customer.getProvince(),customer.getPostCode()));
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
        String id = txtCustomerId.getText();
        String title = txtCustomerTitle.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String city = txtCustomerCity.getText();
        String province = txtCustomerProvince.getText();
        String postcode = txtPostalCode.getText();


        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtCustomerName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCustomerAddress.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {

            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                customerBO.saveCustomer(new CustomerDTO(id,title, name, address,city,province,postcode));
                tblCustomers.getItems().add(new CustomerTM(id,title, name, address,city,province,postcode));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {

            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }

                customerBO.updateCustomer(new CustomerDTO(id,title, name, address,city,province,postcode));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
            selectedCustomer.setCusTitle(title);
            selectedCustomer.setCusName(name);
            selectedCustomer.setCusAddress(address);
            selectedCustomer.setCity(city);
            selectedCustomer.setProvince(province);
            selectedCustomer.setProvince(postcode);

            tblCustomers.refresh();
        }
        btnAddNewCustomer.fire();
    }

    private boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.customerExist(id);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    private String generateNewId() {
        try {
            customerBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblCustomers.getItems().isEmpty()) {
            return "C00-001";

        } else {
            String id = getLastItemId();
            int newItemId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newItemId);
        }
    }

    private String getLastItemId() {
        ArrayList<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Arrays.sort(new ArrayList[]{tempCustomersList});
        return tempCustomersList.get(tempCustomersList.size() - 1).getCusID();
    }

}
