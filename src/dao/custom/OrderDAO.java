package dao.custom;

import dao.CrudDAO;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDAO  extends CrudDAO<Orders,String> {

    String orderCount() throws SQLException, ClassNotFoundException;

    String dayIncome(String day) throws SQLException, ClassNotFoundException;

    String monthIncome(String month) throws SQLException, ClassNotFoundException;

    String yearIncome(String year) throws SQLException, ClassNotFoundException;

}
