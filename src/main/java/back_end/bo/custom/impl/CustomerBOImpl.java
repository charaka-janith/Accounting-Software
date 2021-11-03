/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back_end.bo.custom.impl;

import back_end.bo.custom.CustomerBO;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CustomerDAO;
import back_end.dto.CustomerDTO;
import back_end.entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO dao = (CustomerDAO) DAOFactory.getInstace().getDAO(DAOFactory.DAOFactoryTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO cs) throws Exception {
        return dao.add(new Customer(cs.getId(), cs.getName(), cs.getAddress(), cs.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String customerID) throws Exception {
        return dao.delete(customerID);
    }

    @Override
    public boolean updateCustomer(CustomerDTO cs) throws Exception {
        return dao.update(new Customer(cs.getId(), cs.getName(), cs.getAddress(), cs.getSalary()));
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer c = dao.search(id);
        return new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> all = dao.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary()));
        }
        return allCustomers;
    }

}
