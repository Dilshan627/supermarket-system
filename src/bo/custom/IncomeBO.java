package bo.custom;

import bo.SuperBO;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IncomeBO extends SuperBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    String dayIncome(String date) throws SQLException, ClassNotFoundException;

    String monthIncome(String month) throws SQLException, ClassNotFoundException;

    String yearIncome(String year) throws SQLException, ClassNotFoundException;
}
