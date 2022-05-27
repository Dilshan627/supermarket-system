package controller;

import bo.BOFactory;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDTO;
import model.ItemDTO;
import view.tm.CartTM;
import view.tm.OrderDetailTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceOrderFormController {
    public JFXComboBox cmbCustomerId;
    public JFXTextField txtCustomerName;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public TableView<CartTM> tblOrderDetails;
    public Label lblId;
    public Label lblDate;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public JFXTextField txtDiscount;
    public JFXButton btnDelete;
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASE_ORDER);
    int cartSelectedRowCountForDelete = -1;
    ObservableList<CartTM> list = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("QTY"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Discount"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    Connection connection = DBConnection.getDbConnection().getConnection();
                    try {
                        if (!existCustomer(newValue + "")) {
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                        }

                        CustomerDTO search = purchaseOrderBO.searchCustomer(newValue + "");
                        txtCustomerName.setText(search.getCusName());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtCustomerName.clear();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            if (newItemCode != null) {

                try {
                    if (!existItem(newItemCode + "")) {
                    }
                    ItemDTO item = purchaseOrderBO.searchItem(newItemCode + "");
                    txtDescription.setText(item.getDescription());
                    txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                    Optional<CartTM> optOrderDetail = tblOrderDetails.getItems().stream().filter(detail -> detail.getItemCode().equals(newItemCode)).findFirst();
                    txtQtyOnHand.setText((optOrderDetail.isPresent() ? item.getQtyOnHand() - optOrderDetail.get().getQTY() : item.getQtyOnHand()) + "");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtDescription.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtUnitPrice.clear();
            }
        });

        tblOrderDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowCountForDelete = (int) newValue;
        });


        loadAllCustomerIds();
        loadAllItemCodes();
        lblId.setText(generateNewOrderId());
        lblDate.setText(LocalDate.now().toString());
    }

    private void loadAllCustomerIds() {
        try {
            ArrayList<CustomerDTO> all = purchaseOrderBO.getAllCustomers();
            for (CustomerDTO customerDTO : all) {
                cmbCustomerId.getItems().add(customerDTO.getCusID());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItemCodes() {
        try {
            ArrayList<ItemDTO> all = purchaseOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return purchaseOrderBO.checkItemIsAvailable(code);
    }

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return purchaseOrderBO.checkCustomerIsAvailable(id);
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().equals("") && !txtDiscount.getText().equals("")) {

            String discription = txtDescription.getText();
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qtyForCustomer = Integer.parseInt(txtQty.getText());
            double discount = Double.parseDouble(txtDiscount.getText());

            double calTot = (unitPrice * qtyForCustomer);
            Double total = calTot - ((calTot / 100) * discount);


            if (qtyOnHand < qtyForCustomer) {
                new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
                return;
            }

            CartTM tm = new CartTM(cmbItemCode.getValue(), discription, qtyForCustomer, unitPrice, discount, total);

            int numberOfRow = isExists(tm);

            if (numberOfRow == -1) {
                list.add(tm);
            } else {
                CartTM temp = list.get(numberOfRow);
                CartTM newTm = new CartTM(
                        temp.getItemCode(),
                        temp.getDescription(),
                        temp.getQTY() + qtyForCustomer,
                        unitPrice,
                        discount,
                        total + temp.getTotal()
                );
                list.remove(numberOfRow);
                list.add(newTm);
            }
            tblOrderDetails.setItems(list);
            quntityChange();
            calculateCost();


        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong. Check Fields... ").show();
        }
    }

    private void quntityChange() {
        int value = Integer.parseInt(txtQtyOnHand.getText());
        if (!txtQty.getText().equals("") & (value > 0)) {
            int q = Integer.parseInt(txtQty.getText());
            int q2 = Integer.parseInt(txtQtyOnHand.getText());
            int result = q2 - q;

            if (result <= 0) {
                new Alert(Alert.AlertType.WARNING, "Out Of Stock...!").show();
            } else {
                txtQtyOnHand.setText(String.valueOf(result));
            }
        }
    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < list.size(); i++) {
            if (tm.getItemCode().equals(list.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        if (cartSelectedRowCountForDelete == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            list.remove(cartSelectedRowCountForDelete);

            calculateCost();
            tblOrderDetails.refresh();
        }
    }

    private void calculateCost() {
        double total = 0;
        for (CartTM tm : list) {
            total += tm.getTotal();
        }
        lblTotal.setText(total + " /=");
    }

    public String generateNewOrderId() {
        try {
            return purchaseOrderBO.generateNewOrderID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OID-001";
    }

}
