package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import view.tm.ItemTM;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemFormController {
    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    public AnchorPane context;
    public JFXButton btnAddNewItem;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ItemTM> tblItems;
    public JFXTextField txtPackageSize;
    public Label txtCode;

    public void initialize() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));


        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtPackageSize.setText(newValue.getPackageSize());
                txtUnitPrice.setText(newValue.getUnitPrice().toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
        txtCode.setText(generateNewId());
    }

    private void loadAllItems() {
        tblItems.getItems().clear();
        try {
            ArrayList<ItemDTO> allItem = itemBO.getAllItems();
            for (ItemDTO item : allItem) {
                tblItems.getItems().add(new ItemTM(item.getCode(), item.getDescription(), item.getPackageSize(), item.getUnitPrice(), item.getQtyOnHand()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtPackageSize.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        btnSave.setText("Save");
        txtDescription.requestFocus();
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtDescription.getLength() != 0 && txtUnitPrice.getLength() != 0 && txtQtyOnHand.getLength() != 0 && txtPackageSize.getLength() != 0) {


            String code = txtCode.getText();
            String description = txtDescription.getText();
            String packageSize = txtPackageSize.getText();
            Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

            String price= String.valueOf(unitPrice);
            String qty = String.valueOf(qtyOnHand);


            if (!description.matches("[A-Za-z ]+")) {
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                txtDescription.requestFocus();
                return;
            } else if (!packageSize.matches("[0-9]+g|[0-9]+kg")) {
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                txtPackageSize.requestFocus();
                return;
            } else if (!price.matches("[0-9]+.[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                txtUnitPrice.requestFocus();
                return;
            } else if (!qty.matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "Invalid").show();
                txtQtyOnHand.requestFocus();
                return;
            }


            if (btnSave.getText().equalsIgnoreCase("save")) {
                try {
                    if (existItem(code)) {
                        new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                    }
                    //Save Item
                    itemBO.saveItem(new ItemDTO(code, description, packageSize, unitPrice, qtyOnHand));

                    tblItems.getItems().add(new ItemTM(code, description, packageSize, unitPrice, qtyOnHand));

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (!existItem(code)) {
                        new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                    }
                    itemBO.updateItem(new ItemDTO(code, description, packageSize, unitPrice, qtyOnHand));

                    ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                    selectedItem.setDescription(description);
                    selectedItem.setPackageSize(packageSize);
                    selectedItem.setQtyOnHand(qtyOnHand);
                    selectedItem.setUnitPrice(unitPrice);
                    tblItems.refresh();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            btnAddNewItem.fire();

        }
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemBO.itemExist(code);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        if (txtDescription.getLength() != 0 && txtUnitPrice.getLength() != 0 && txtQtyOnHand.getLength() != 0 && txtPackageSize.getLength() != 0) {
            String code = tblItems.getSelectionModel().getSelectedItem().getCode();
            try {
                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                itemBO.deleteItem(code);
                tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
                tblItems.getSelectionModel().clearSelection();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private String generateNewId() {
        try {
            itemBO.generateNewItemCode();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblItems.getItems().isEmpty()) {
            return "I00-001";

        } else {
            String id = getLastItemId();
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        }
    }

    private String getLastItemId() {
        ArrayList<ItemTM> tempItemList = new ArrayList<>(tblItems.getItems());
        Arrays.sort(new ArrayList[]{tempItemList});
        return tempItemList.get(tempItemList.size() - 1).getCode();
    }

}
