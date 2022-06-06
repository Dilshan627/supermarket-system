package model;

import java.math.BigDecimal;

public class ItemDTO {
    private String code;
    private String description;
    private String packageSize;
    private Double unitPrice;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String description, int qtyOnHand) {
        this.description = description;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemDTO(String code, String description, String packageSize, Double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.packageSize = packageSize;
        this.unitPrice = unitPrice;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", packageSize='" + packageSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
