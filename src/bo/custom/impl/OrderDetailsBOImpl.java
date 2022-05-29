package bo.custom.impl;

import bo.custom.OrderDetailsBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import entity.Orders;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

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
    public String orderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.orderCount();
    }
}
