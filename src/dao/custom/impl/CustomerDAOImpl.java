package dao.custom.impl;

import dao.custom.CustomerDAO;
import dao.SQLUtil;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)",entity.getCusID(), entity.getCusTitle(), entity.getCusName(),entity.getCusAddress(),entity.getCity(),entity.getProvince(),entity.getPostCode());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET CusTitle=?,CusName=?, CusAddress=? ,City=? ,Povince=? ,PostCode=? WHERE CusID=? ", entity.getCusTitle(), entity.getCusName(), entity.getCusAddress(),entity.getCity(),entity.getProvince(),entity.getPostCode(),entity.getCusID());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE CusID=?", id);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6),rst.getString(7));
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT CusID FROM Customer WHERE CusID=?", id).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT CusID FROM Customer ORDER BY CusID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("CusID");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public String customerCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT count(CusID) FROM customer");
        resultSet.next();
        String count=resultSet.getString(1);
        return count;
    }
}
