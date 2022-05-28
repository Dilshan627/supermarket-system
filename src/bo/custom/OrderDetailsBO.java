package bo.custom;

import bo.SuperBO;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    String orderCount() throws SQLException, ClassNotFoundException;
}
