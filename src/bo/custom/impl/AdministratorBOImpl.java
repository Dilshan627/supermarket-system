package bo.custom.impl;

import bo.custom.AdministratorBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import entity.Custom;
import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorBOImpl implements AdministratorBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<CustomDTO> MostSellItem() throws SQLException, ClassNotFoundException {
        ArrayList<Custom> all = queryDAO.MostSellItem();
        ArrayList<CustomDTO> MostSellItem = new ArrayList<>();
        for (Custom item : all) {
            MostSellItem.add(new CustomDTO(item.getDescription(), item.getOrderqty()));
        }
        return MostSellItem;
    }

    @Override
    public String lastSell() throws SQLException, ClassNotFoundException {
        return queryDAO.lastSell();
    }
}
