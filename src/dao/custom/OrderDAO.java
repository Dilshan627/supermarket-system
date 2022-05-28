package dao.custom;

import dao.CrudDAO;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDAO  extends CrudDAO<Orders,String> {

    String orderCount() throws SQLException, ClassNotFoundException;
}
