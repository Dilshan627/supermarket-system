package controller;

import bo.BOFactory;
import bo.custom.IncomeBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDTO;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IncomeFormController {
    public TableView<OrderTM> tblIncome;
    public Label lblDay;
    public Label lblMonth;
    public Label lblYear;
    public ComboBox cmbDate;
    public ComboBox cmbMonth;
    public ComboBox cmbYear;

    IncomeBO incomeBO = (IncomeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Income);

    public void initialize() {
        ObservableList allDate = FXCollections.observableArrayList();
        for (int i = 1; i < 32; i++) {
            allDate.add(i);
        }
        ObservableList allMonth = FXCollections.observableArrayList();
        for (int i = 1; i < 13; i++) {
            allMonth.add(i);
        }
        ObservableList allYear = FXCollections.observableArrayList();
        for (int i = 2020; i < 2026; i++) {
            allYear.add(i);
        }

        cmbDate.setItems(allDate);
        cmbMonth.setItems(allMonth);
        cmbYear.setItems(allYear);

        String date = String.valueOf(LocalDate.now());
        String[] dateParts = date.split("-");
        String day = dateParts[2];
        String month = dateParts[1];
        String year = dateParts[0];
        cmbDate.setValue(day);
        cmbMonth.setValue(month);
        cmbYear.setValue(year);

        String now = cmbYear.getSelectionModel().getSelectedItem() + "-" + cmbMonth.getSelectionModel().getSelectedItem() + "-" + cmbDate.getSelectionModel().getSelectedItem();
        tblIncome.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tblIncome.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CusID"));
        tblIncome.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        tblIncome.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            lblDay.setText(incomeBO.dayIncome(now));
            lblMonth.setText(incomeBO.monthIncome(month));
            lblYear.setText(incomeBO.yearIncome(now));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadAllOrder();
    }

    private void loadAllOrder() {
        tblIncome.getItems().clear();
        try {
            ArrayList<OrderDTO> order = incomeBO.getAllOrder();
            for (OrderDTO orderDTO : order) {
                tblIncome.getItems().add(new OrderTM(orderDTO.getOrderID(), orderDTO.getCusID(), orderDTO.getOrderDate(), orderDTO.getTotal()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String now = cmbYear.getSelectionModel().getSelectedItem() + "-" + cmbMonth.getSelectionModel().getSelectedItem() + "-" + cmbDate.getSelectionModel().getSelectedItem();
        String Month = String.valueOf(cmbMonth.getSelectionModel().getSelectedItem());
        lblDay.setText(incomeBO.dayIncome(now));
        lblMonth.setText(incomeBO.monthIncome(Month));
        lblYear.setText(incomeBO.yearIncome(now));
    }
}
