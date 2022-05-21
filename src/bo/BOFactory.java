package bo;

import bo.impl.CustomerBOImpl;
import bo.impl.ItemBOImpl;
import bo.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER
    }

}
