/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back_end.bo.custom;
import back_end.bo.SuperBO;
import back_end.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customer) throws Exception ;

    public boolean deleteCustomer(String customerID) throws Exception ;

    public boolean updateCustomer(CustomerDTO customer) throws Exception ;

    public CustomerDTO searchCustomer(String id) throws Exception ;

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception ;

}
