package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface OrderPayBo extends SuperBO {

    String pay() throws SQLException, ClassNotFoundException;
}
