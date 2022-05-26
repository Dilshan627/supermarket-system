package model;

public class OrderDetailDTO {
    private String OrderID;
    private String ItemCode;
    private int Orderqty;
    private double Discount;


    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String itemCode, int orderqty, double discount) {
        OrderID = orderID;
        ItemCode = itemCode;
        Orderqty = orderqty;
        Discount = discount;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderqty() {
        return Orderqty;
    }

    public void setOrderqty(int orderqty) {
        Orderqty = orderqty;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "OrderID='" + OrderID + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", Orderqty=" + Orderqty +
                ", Discount=" + Discount +
                '}';
    }
}