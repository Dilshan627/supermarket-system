package entity;



public class Item {
    private String code;
    private String description;
    private String packageSize;
    private Double unitPrice;
    private int qtyOnHand;

    public Item() {
    }

    public Item(String code, String description, String packageSize, Double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.packageSize = packageSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public Item(String description, int qtyOnHand) {
        this.description = description;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
