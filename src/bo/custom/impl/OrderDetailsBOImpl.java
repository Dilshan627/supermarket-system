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
        ArrayList<OrderDetail> orderDetail = orderDetailsDAO.order(id);
        ArrayList<OrderDetailDTO> list = new ArrayList();
        for (OrderDetail detail : orderDetail) {
            list.add((new OrderDetailDTO(detail.getOrderID(), detail.getItemCode(), detail.getOrderqty(), detail.getDiscount(), detail.getPrice())));
        }
        return list;
    }

    @Override
    public ArrayList<OrderDetailDTO> orderId() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> orderDetails = orderDetailsDAO.orderId();
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        for (OrderDetail detail : orderDetails) {
            list.add(new OrderDetailDTO(detail.getOrderID()));
        }
        return list;
    }
}
