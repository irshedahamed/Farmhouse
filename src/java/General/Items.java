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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author irshed
 */
public class Items {

    private String farmerid;
    private String categoryid;
    private String item;
    private String quantity;
    private String price;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public static String insert(Items obj) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        if (Items.isItemAvailable(obj.getFarmerid(), obj.getCategoryid(),obj.getItem())) {
            String sql = "update item_info set quantity=? , ppkg=? where cat_id=? and id=? and item=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getQuantity());
            stmt.setString(2, obj.getPrice());
            stmt.setString(3, obj.getCategoryid());
            stmt.setString(4, obj.getFarmerid());
            stmt.setString(5, obj.getItem());
            stmt.executeUpdate();
        } else {
            String sql = "insert into item_info values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getFarmerid());
            stmt.setString(2, obj.getCategoryid());
            stmt.setString(3, obj.getItem());
            stmt.setString(4, obj.getQuantity());
            stmt.setString(5, obj.getPrice());
            stmt.executeUpdate();
        }
        if (stmt != null) {
            stmt.close();
        }

        return "Inserted";
    }

    public static boolean isItemAvailable(String farmerid, String categoryid,String item) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from item_info where cat_id like ? and id like ? and item like ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, categoryid);
        stmt.setString(2, farmerid);
        stmt.setString(3, item);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            System.out.println("TRue");
            return true;
        }
        return false;
    }

    public static List<Items> getItemById(String farmerid) throws SQLException{
        List<Items> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from item_info where id like ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, farmerid);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Items i = new Items();
            i.setFarmerid(farmerid);
            i.setCategoryid(rs.getString("cat_id"));
            i.setItem(rs.getString("item"));
            i.setQuantity(rs.getString("quantity"));
            i.setPrice(rs.getString("ppkg"));
            list.add(i);
        }
        return list;
    }

}
