/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back_end.dao;

import back_end.db.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    private static PreparedStatement getPreparedStatement(String sql, Object... parms) throws ClassNotFoundException, SQLException {
        //creating connection with mysql server
        Connection connection = DBConnector.getInstance().getConnection();
        System.out.println("CrudUtil.getPreparedStatement");
        System.out.println(connection);
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < parms.length; i++) {
            pstm.setObject(i + 1, parms[i]);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql, Object... parms) throws ClassNotFoundException, SQLException {
        return getPreparedStatement(sql, parms).executeUpdate() > 0;
    }

    public  static ResultSet executeQuery(String sql, Object... parms) throws ClassNotFoundException, SQLException {
        return getPreparedStatement(sql, parms).executeQuery();
    }

}
