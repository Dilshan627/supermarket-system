package bo.impl;

import bo.ItemBO;
import entity.Item;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
       /* ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return allItems;*/
        return null;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean itemExist(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return null;
    }
}
