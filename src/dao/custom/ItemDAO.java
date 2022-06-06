package dao.custom;

import dao.CrudDAO;
import entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    String ItemCount() throws SQLException, ClassNotFoundException;

    ArrayList<Item> Item() throws SQLException, ClassNotFoundException;
}
