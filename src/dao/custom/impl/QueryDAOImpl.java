package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import entity.Custom;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<Custom> MostSellItem() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT i.ItemCode, i.Description , o.Orderqty  FROM Item i INNER JOIN `Order Details` o ON o.ItemCode = i.ItemCode GROUP BY ItemCode;");
        ArrayList<Custom> allCustomers = new ArrayList<>();
        while (resultSet.next()) {
            allCustomers.add(new Custom(resultSet.getString(2),resultSet.getInt(3)));
        }
        return allCustomers;
    }
}
