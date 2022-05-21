package model;

import java.math.BigDecimal;

public class ItemDTO {
    private String code;
    private String description;
    private String packageSize;
    private BigDecimal unitPrice;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String code, String description, String packageSize, BigDecimal unitPrice, int qtyOnHand) {
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
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
