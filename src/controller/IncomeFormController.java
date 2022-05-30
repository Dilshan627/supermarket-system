package controller;

import bo.BOFactory;
import bo.custom.IncomeBO;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDTO;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeFormController {
    public TableView<OrderTM> tblIncome;
    public Label lblDay;
    public Label lblMonth;
    public Label lblYear;

    IncomeBO incomeBO = (IncomeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Income);

    public void initialize() {
        tblIncome.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tblIncome.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CusID"));
        tblIncome.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        tblIncome.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            lblDay.setText(incomeBO.dayIncome());
            lblMonth.setText(incomeBO.monthIncome());
            lblYear.setText(incomeBO.yearIncome());
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
}
