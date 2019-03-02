/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irshed
 */
public class dbcon {
      static final Map<String,Connection> connectionPool;
     static{
         connectionPool=new HashMap<String, Connection>();
     }

   
    
    public static Connection getConnection(String dbname){
        
        Connection conn=connectionPool.get(dbname);
          try { 
            if(conn==null || conn.isClosed()){
                 Class.forName("com.mysql.jdbc.Driver").newInstance();


                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname,"root","12345678");
                 connectionPool.put(dbname, conn);

                 }
        
        } catch (Exception ex) {
            Logger.getLogger(dbcon.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return conn;
    }
}
