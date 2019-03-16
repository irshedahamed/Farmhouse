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
public class User {
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
    
    public static User insert(User user) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        conn = dbcon.getConnection("system");
        String sql ="select * from user_details";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.afterLast();
        if(!rs.previous()){
            user.setId("user1");
        }else{
            String s = rs.getString("id");
            int i = Integer.parseInt(s.substring(4));
            i++;
            user.setId("user"+String.valueOf(i));
        }
        String sql1="insert into user_details values(?,?,?,?,?,?)";
        stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, user.getId());
        stmt1.setString(2, user.getName());
        stmt1.setString(3, user.getPhno());
        stmt1.setString(4, user.getCity());
        stmt1.setString(5, user.getMailid());
        stmt1.setString(6, user.getReviewid());
        stmt1.executeUpdate();
        if(stmt!=null)
            stmt.close();
        if(stmt1!=null)
            stmt1.close();
        if(rs!=null)
            rs.close();
        return user;
    }
    
     public static User getUserbyId(String id) throws SQLException {
        User f = new User();
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from user_details where id=?";
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
}
