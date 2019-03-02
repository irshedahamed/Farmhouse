/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import General.Authenticate;
import General.User;
import com.google.gson.Gson;
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
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author irshed
 */
@Path("InsertUser")
public class InsertUserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InsertUserResource
     */
    public InsertUserResource() {
    }

    /**
     * Retrieves representation of an instance of REST.InsertUserResource
     *
     * @param headers
     * @param body
     * @return an instance of java.lang.String
     * @throws org.json.simple.parser.ParseException
     * @throws java.sql.SQLException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@Context HttpHeaders headers, String body) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONArray arr = (JSONArray) parser.parse(body);
        User user = new Gson().fromJson((String) arr.get(0), User.class);
        String pass = (String) arr.get(1);
        User er = User.insert(user);
        Authenticate a = new Authenticate();
        a.insert(er.getId(), pass, "user");
        return er.getId();
    }

    /**
     * PUT method for updating or creating an instance of InsertUserResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
