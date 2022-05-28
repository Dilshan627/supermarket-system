package view.tm;

import java.time.LocalDate;

public class OrderTM {
    private String OrderID;
    private String CusID;
    private LocalDate OrderDate;
    private double Total;

    public OrderTM() {
    }

    public OrderTM(String orderID, String cusID, LocalDate orderDate, double total) {
        OrderID = orderID;
        CusID = cusID;
        OrderDate = orderDate;
        Total = total;
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
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "OrderID='" + OrderID + '\'' +
                ", CusID='" + CusID + '\'' +
                ", OrderDate=" + OrderDate +
                ", Total=" + Total +
                '}';
    }
}
