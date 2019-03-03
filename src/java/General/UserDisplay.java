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
public class UserDisplay {
    private String farname;
    private String item;
    private String quantity;
    private String price;
    private String phno;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    
    public String getFarname() {
        return farname;
    }

    public void setFarname(String farname) {
        this.farname = farname;
    }

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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
     public static List<UserDisplay> getItemById(String catid) throws SQLException{
        List<UserDisplay> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = dbcon.getConnection("system");
        String sql = "select * from item_info where cat_id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, catid);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            UserDisplay disp = new UserDisplay();
            String id =  rs.getString("id");
            Farmer f = Farmer.getFarmerbyId(id);
            disp.setFarname(f.getName());
            disp.setPhno(f.getPhno());
            disp.setItem(rs.getString("item"));
            disp.setQuantity(rs.getString("quantity"));
            disp.setPrice(rs.getString("ppkg"));
            list.add(disp);
        }
        return list;
    }
}
