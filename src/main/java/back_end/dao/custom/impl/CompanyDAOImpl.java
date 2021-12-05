package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.custom.CompanyDAO;
import back_end.entity.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAOImpl implements CompanyDAO {
    @Override
    public boolean add(Company company) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "insert into Company values(?,?,?,?,?,?)",
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        );
    }

    @Override
    public boolean delete(String name) throws Exception {
        return false;
    }

    @Override
    public boolean update(Company company) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE Company SET Name=?, Address=?, PhoneNumber=?, Email=?, WebSite=?, BRN=?",
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        );
    }

    @Override
    public Company search(String name) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Company WHERE Name=?",
                name
        );
        Company company = null;
        while (rst.next()) {
            company = new Company(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("PhoneNumber"),
                    rst.getString("Email"),
                    rst.getString("WebSite"),
                    rst.getString("BRN")
            );
        }
        return company;
    }

    @Override
    public ArrayList<Company> getAll() throws Exception {
        return null;
    }

    @Override
    public Company get() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Company"
        );
        Company company = null;
        while (rst.next()) {
            company = new Company(
                    rst.getString("Name"),
                    rst.getString("Address"),
                    rst.getString("PhoneNumber"),
                    rst.getString("Email"),
                    rst.getString("WebSite"),
                    rst.getString("BRN"));
        }
        return company;
    }
}
