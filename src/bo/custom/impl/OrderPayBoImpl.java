package bo.custom.impl;


import bo.custom.OrderPayBo;
import dao.DAOFactory;
import dao.custom.OrderDAO;

import java.sql.SQLException;

public class OrderPayBoImpl implements OrderPayBo {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String pay() throws SQLException, ClassNotFoundException {
        return orderDAO.pay();
    }
}
