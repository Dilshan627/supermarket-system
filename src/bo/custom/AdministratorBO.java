package bo.custom;

import bo.SuperBO;
import model.CustomDTO;
import model.CustomerDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface AdministratorBO extends SuperBO {

    ArrayList<CustomDTO> MostSellItem() throws SQLException, ClassNotFoundException;
}
