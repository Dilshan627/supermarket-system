package dao.custom;

import dao.CrudDAO;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDAO  extends CrudDAO<Orders,String> {

    String orderCount() throws SQLException, ClassNotFoundException;

    String dayIncome() throws SQLException, ClassNotFoundException;

    String monthIncome() throws SQLException, ClassNotFoundException;

    String yearIncome() throws SQLException, ClassNotFoundException;

    String pay() throws SQLException, ClassNotFoundException;

}
