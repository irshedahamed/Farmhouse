/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import General.User;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author irshed
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        JSONArray array = new JSONArray();
        User user = new User();
        user.setName("Irshed");
        user.setCity("chennai");
        user.setPhno("12334");
        Gson g = new Gson();
        String s = g.toJson(user);
        JSONObject onj = new JSONObject();
        onj.put("password","sonu");
        array.add(s);
        array.add("sonu");
        String temp = array.toString();
        System.out.println(temp);
        
        JSONParser parser = new JSONParser();
        JSONArray newArray = (JSONArray)parser.parse(temp);
        
        
        
        User i = (User)g.fromJson((String) newArray.get(0), User.class);
        System.out.println(i.getName());
        System.out.println((String)newArray.get(1));
    }
   
}
