package bo.custom;

import bo.SuperBO;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IncomeBO extends SuperBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    String dayIncome() throws SQLException, ClassNotFoundException;

    String monthIncome() throws SQLException, ClassNotFoundException;

    String yearIncome() throws SQLException, ClassNotFoundException;
}
