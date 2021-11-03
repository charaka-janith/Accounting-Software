/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.CustomerDAO;
import back_end.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate("insert into Customer values(?,?,?,?)", c.getId(), c.getName(), c.getAddress(), c.getSalary());
    }

    @Override
    public boolean delete(String customerID) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate("delete from Customer where id=?", customerID);
    }

    public boolean update(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate("update Customer set name=?,address=?,salary=? where id=?", c.getName(), c.getAddress(), c.getSalary(), c.getId());
    }

    @Override
    public Customer search(String id) throws ClassNotFoundException, SQLException {

        ResultSet rst = CrudUtil.executeQuery("select * from Customer where id=?", id);
        Customer customer = null;
        while (rst.next()) {
            customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("select * from Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            Customer c = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
            allCustomers.add(c);
        }
        return allCustomers;
    }

    @Override
    public String getCustomersLastID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select max(id) from Customer");
        String s = null;
        while (rst.next()) {
            s = rst.getString(1);
        }
        return s;

    }
}
