package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.CompanyDAO;
import back_end.entity.Colors;
import back_end.entity.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAOImpl implements CompanyDAO {
    @Override
    public boolean add(Company company) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String name) throws Exception {
        return false;
    }

    @Override
    public boolean update(Company company) throws Exception {
        return false;
    }

    @Override
    public Company search(String name) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM company WHERE name=?", name);
        Company company = null;
        while (rst.next()) {
            company = new Company(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return company;
    }

    @Override
    public ArrayList<Company> getAll() throws Exception {
        return null;
    }

    @Override
    public Company get() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM company");
        Company company = null;
        while (rst.next()) {
            company = new Company(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return company;
    }
}
