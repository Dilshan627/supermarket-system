package bo.custom.impl;

import bo.custom.IncomeBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import entity.Orders;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeBOImpl implements IncomeBO {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> all = orderDAO.getAll();
        ArrayList<OrderDTO> allOrder = new ArrayList<>();
        for (Orders order : all) {
            allOrder.add(new OrderDTO(order.getOrderID(), order.getCusID(), order.getOrderDate(), order.getTotal()));
        }
        return allOrder;
    }

    @Override
    public String dayIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.dayIncome();
    }

    @Override
    public String monthIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.monthIncome();
    }

    @Override
    public String yearIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.yearIncome();
    }
}
