/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import General.Items;
import General.UserDisplay;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author irshed
 */
public class DisplayItem {
    public static void main(String[] args) throws SQLException, ParseException {
         List<UserDisplay> list = UserDisplay.getItemById("2");
        Gson g = new Gson();
        String json = g.toJson(list);
        System.out.println(json);

        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(json);

        List<UserDisplay> li = new ArrayList<>();
       
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
               UserDisplay ix = new Gson().fromJson(obj.get(i).toString(), UserDisplay.class);
                li.add(ix);
            }
        }
        
         for(UserDisplay l : li){
            System.out.println(l.getFarname()+" "+l.getPhno()+" ");
        }
        
        System.out.println(obj);
    }
}
