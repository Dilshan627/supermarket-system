package entity;

import java.time.LocalDate;

public class Orders {
    private String OrderID;
    private String CusID;
    private LocalDate OrderDate;
    private double total;

    public Orders() {
    }

    public Orders(String orderID, String cusID, LocalDate orderDate, double total) {
        OrderID = orderID;
        CusID = cusID;
        OrderDate = orderDate;
        this.total = total;
    }

    public Orders(String orderID, String cusID, String orderDate, double total) {
        OrderID = orderID;
        CusID = cusID;
        OrderDate = LocalDate.parse(orderDate);
        this.total = total;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        OrderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
