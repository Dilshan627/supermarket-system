package bo.custom.impl;

import bo.custom.CashierBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;

import java.sql.SQLException;

public class CashierBOImpl implements CashierBO {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public String orderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.orderCount();
    }
}
