package back_end.dao.custom.impl;

import back_end.dao.CrudUtil;
import back_end.dao.DAOFactory;
import back_end.dao.custom.CompanyDAO;
import back_end.dao.custom.UserDAO;
import back_end.entity.Company;
import back_end.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class CompanyDAOImpl implements CompanyDAO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOFactoryTypes.USER);

    @Override
    public boolean add(Company company) throws SQLException, ClassNotFoundException {
        userDAO.add(new User(
                company.getUserName(),
                null,
                "company"
        ));
        return CrudUtil.executeUpdate(
                "INSERT INTO Company(Name, UserName, Address, PhoneNumber, Email, WebSite, BRN) VALUES(?,?,?,?,?,?,?)",
                company.getName(),
                company.getUserName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        );
    }

    @Override
    public boolean delete(String name) {
        return false;
    }

    @Override
    public boolean update(Company company) {
        return false;
    }

    @Override
    public Company search(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM Company WHERE Name=?",
                name
        );
        Company company = null;
        while (rst.next()) {
            company = new Company(
                    rst.getString("Name"),
                    rst.getString("UserName"),
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
    public ArrayList<Company> getAll() {
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
                    rst.getString("UserName"),
                    rst.getString("Address"),
                    rst.getString("PhoneNumber"),
                    rst.getString("Email"),
                    rst.getString("WebSite"),
                    rst.getString("BRN"));
        }
        return company;
    }

    @Override
    public boolean updateCompany(Company company, String oldUsername) throws SQLException, ClassNotFoundException {
        try {
            userDAO.add(new User(
                    company.getUserName(),
                    null,
                    "company"
            ));
        } catch (SQLIntegrityConstraintViolationException ignored) {}
        boolean updated = CrudUtil.executeUpdate(
                "UPDATE Company SET Name=?, UserName=?, Address=?, PhoneNumber=?, Email=?, WebSite=?, BRN=?",
                company.getName(),
                company.getUserName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getWebSite(),
                company.getBrn()
        );
        userDAO.delete(oldUsername);
        return updated;
    }
}
