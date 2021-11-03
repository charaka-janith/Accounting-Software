package back_end.dao.custom;

import back_end.dao.CrudDAO;
import back_end.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    public String getCustomersLastID()throws Exception;
}
