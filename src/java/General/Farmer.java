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

public class Farmer {

    private String id;
    private String name;
    private String phno;
    private String city;
    private String mailid;
    private String reviewid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public static Farmer insert(Farmer user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from farmer_details";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.afterLast();
        if (!rs.previous()) {
            user.setId("farmer1");
        } else {
            String s = rs.getString("id");
            int i = Integer.parseInt(s.substring(6));
            i++;
            user.setId("farmer" + String.valueOf(i));
        }
        String sql1 = "insert into farmer_details values(?,?,?,?,?,?)";
        stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, user.getId());
        stmt1.setString(2, user.getName());
        stmt1.setString(3, user.getPhno());
        stmt1.setString(4, user.getCity());
        stmt1.setString(5, user.getMailid());
        stmt1.setString(6, user.getReviewid());
        stmt1.executeUpdate();
        if (stmt != null) {
            stmt.close();
        }
        if (stmt1 != null) {
            stmt1.close();
        }
        if (rs != null) {
            rs.close();
        }
        return user;
    }

    public static Farmer getFarmerbyId(String id) throws SQLException {
        Farmer f = new Farmer();
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from farmer_details where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            f.setId(id);
            f.setName(rs.getString("name"));
            f.setCity(rs.getString("city"));
            f.setMailid(rs.getString("mailid"));
            f.setPhno(rs.getString("phno"));
            f.setReviewid(rs.getString("reviewid"));
        }
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }

        return f;
    }

    public static String update(Farmer f) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "update farmer_details set reviewid=? where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, f.getReviewid());
        stmt.setString(2, f.getId());
        stmt.executeUpdate();
        if (stmt != null) {
            stmt.close();
        };
        return "Updated";
    }

    public static String setReview(String id, String review) throws SQLException {
        int r1 = 0, r2 = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        Farmer f = Farmer.getFarmerbyId(id);
        if(f.getReviewid()!=null)
          r1 = Integer.valueOf(f.getReviewid());
        r2 = Integer.valueOf(review);
        r1 = (r1 + r2) / 2;
        review = String.valueOf(r1);
        String sql = "update farmer_details set reviewid=? where id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, review);
        stmt.setString(2, f.getId());
        stmt.executeUpdate();
        if (stmt != null) {
            stmt.close();
        };
        return "Updated";
    }

}
