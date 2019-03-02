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
public class Review {

    private String reviewid;
    private String review;

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public static String insert(String farmerid, String review) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        String reviewid = "";
        conn = dbcon.getConnection("system");
        String sql = "select * from review";
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.afterLast();
        if (!rs.previous()) {
            reviewid = "1";
        } else {
            String s = rs.getString("reviewid");
            int i = Integer.parseInt(s);
            i++;
            reviewid = String.valueOf(i);
        }
        String sql1 = "insert into review values(?,?)";
        stmt1 = conn.prepareStatement(sql1);
        stmt1.setString(1, reviewid);
        stmt1.setString(2, review);
        stmt1.executeUpdate();
        
        Farmer f = Farmer.getFarmerbyId(farmerid);
        if(f.getReviewid()!=null){
            String temp = f.getReviewid();
            f.setReviewid(temp+","+reviewid);
        }else{
            f.setReviewid(reviewid);
        }
        Farmer.update(f);
        
        if (stmt != null) {
            stmt.close();
        }
        if (stmt1 != null) {
            stmt1.close();
        }
        if (rs != null) {
            rs.close();
        }
        return "Updated";
    }
}
