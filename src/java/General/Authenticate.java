/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import dbcon.dbcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author irshed
 */
public class Authenticate {

    private String id;
    private String Password;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAuthenticated() throws SQLException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from login where id like ? and password like ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, Password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            type = rs.getString("type");
            flag = true;
        }
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
        return flag;
    }

    public boolean insert(String id, String pass, String type) throws SQLException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "insert into login values(?,?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, pass);
        stmt.setString(3, type);
        stmt.executeUpdate();
        flag = true;
        if (stmt != null) {
            stmt.close();
        }
        return flag;
    }

}
