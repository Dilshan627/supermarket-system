package bo.custom.impl;

import bo.custom.OrderDetailsBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import entity.OrderDetail;
import entity.Orders;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);


    @Override
    public ArrayList<OrderDetailDTO> searchOrder(String id) throws SQLException, ClassNotFoundException {
        OrderDetail search = orderDetailsDAO.search(id);
        ArrayList<OrderDetailDTO> list = new ArrayList();
        list.add(new OrderDetailDTO(search.getOrderID(), search.getItemCode(), search.getOrderqty(), search.getDiscount(), search.getPrice()));
        return list;
    }
}
