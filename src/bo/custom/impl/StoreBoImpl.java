package bo.custom.impl;

import bo.custom.StoreBo;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import entity.Item;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StoreBoImpl implements StoreBo {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> Item() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getDescription(),item.getQtyOnHand()));
        }
        return allItems;
    }
}
