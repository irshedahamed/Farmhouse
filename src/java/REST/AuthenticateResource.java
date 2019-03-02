/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import General.Authenticate;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author irshed
 */
@Path("Authenticate")
public class AuthenticateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthenticateResource
     */
    public AuthenticateResource() {
    }

    /**
     * Retrieves representation of an instance of REST.AuthenticateResource
     * @param headers
     * @param body
     * @return an instance of java.lang.String
     * @throws org.json.simple.parser.ParseException
     * @throws java.sql.SQLException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@Context HttpHeaders headers,String body) throws ParseException, SQLException {
        String res = null; 
        String uname,pass;
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(body);
        uname =(String)obj.get("id");
        pass = (String)obj.get("password");
        
        Authenticate a = new Authenticate();
        a.setId(uname);
        a.setPassword(pass);
        if(a.isAuthenticated()){
            res =  a.getType();
        }
        JSONObject json = new JSONObject();
        json.put("result", res);
        return json.toString();
    }

    /**
     * PUT method for updating or creating an instance of AuthenticateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
