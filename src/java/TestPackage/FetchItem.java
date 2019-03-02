/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import General.Items;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author irshed
 */
public class FetchItem {

    public static void main(String[] args) throws SQLException, ParseException {
        List<Items> list = Items.getItemById("farmer2");
        Gson g = new Gson();
        String json = g.toJson(list);
        System.out.println(json);

        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(json);

        List<Items> li = new ArrayList<>();
       
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
               Items ix = new Gson().fromJson(obj.get(i).toString(), Items.class);
                li.add(ix);
            }
        }
        
         for(Items l : li){
            System.out.println(l.getItem());
        }
        
        System.out.println(obj);
    }
}
