package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.custom.CustomerDAO;
import dao.DAOFactory;
import entity.Customer;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getCusID(), customer.getCusTitle(), customer.getCusName(), customer.getCusAddress(), customer.getCity(), customer.getProvince(), customer.getPostCode()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCusID(), dto.getCusTitle(), dto.getCusName(), dto.getCusAddress(), dto.getCity(), dto.getProvince(), dto.getPostCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusID(), dto.getCusTitle(), dto.getCusName(), dto.getCusAddress(), dto.getCity(), dto.getProvince(), dto.getPostCode()));
    }

    @Override
    public boolean customerExist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public String customerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.customerCount();
    }
}
