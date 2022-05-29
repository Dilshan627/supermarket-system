package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface CashierBO extends SuperBO {

    String orderCount() throws SQLException, ClassNotFoundException;
}
