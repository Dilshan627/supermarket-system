package controller;

import bo.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import view.tm.ItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboardFormController {
    public AnchorPane context;
    public JFXButton btnAddNewItem;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ItemTM> tblItems;
    public JFXTextField txtPackageSize;

    public void initialize() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));


        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtPackageSize.setText(newValue.getPackageSize());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtPackageSize.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    private void loadAllItems() {
//        tblItems.getItems().clear();
//        try {
//
//            ArrayList<ItemDTO> allItem = ItemBO.getAllItems();
//
//            for (ItemDTO item : allItem) {
//                tblItems.getItems().add(new ItemTM(item.getCode(), item.getDescription(), item.getPackageSize(), item.getUnitPrice(), item.getQtyOnHand()));
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/login-form.fxml"));
        context.getChildren().add(parent);
    }
}
