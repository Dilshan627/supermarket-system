package bo.custom;

import bo.SuperBO;
import entity.Item;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StoreBo extends SuperBO {

    ArrayList<ItemDTO> Item() throws SQLException, ClassNotFoundException;
}
