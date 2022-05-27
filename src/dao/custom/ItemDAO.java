package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
