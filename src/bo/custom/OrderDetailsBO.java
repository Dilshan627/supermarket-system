package bo.custom;

import bo.SuperBO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {

    ArrayList<OrderDetailDTO> searchOrder(String id) throws SQLException, ClassNotFoundException;

}
