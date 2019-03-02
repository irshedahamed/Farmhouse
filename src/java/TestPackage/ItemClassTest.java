/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import General.Items;
import com.google.gson.Gson;
import java.sql.SQLException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author irshed
 */
public class ItemClassTest {
    public static void main(String[] args) throws SQLException {
        Items i = new Items();
        i.setFarmerid("farmer2");
        i.setCategoryid("1");
        i.setItem("tomato");
        i.setPrice("2");
        i.setQuantity("3");
        //System.out.println(Items.insert(i));
        JSONParser parse = new JSONParser();
        Gson g = new Gson();
        String json = g.toJson(i);
        System.out.println(json);
    }
}
