/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import General.Authenticate;
import General.Farmer;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("InsertFarmer")
public class InsertFarmerResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InsertFarmerResource
     */
    public InsertFarmerResource() {
    }

    /**
     * Retrieves representation of an instance of REST.InsertFarmerResource
     * @param headers
     * @param body
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     * @throws org.json.simple.parser.ParseException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@Context HttpHeaders headers, String body) throws SQLException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray arr = (JSONArray) parser.parse(body);
        Farmer user = new Gson().fromJson((String) arr.get(0), Farmer.class);
        String pass = (String) arr.get(1);
        Farmer er = Farmer.insert(user);
        Authenticate a = new Authenticate();
        a.insert(er.getId(), pass, "farmer");
        return er.getId();
    }

    /**
     * PUT method for updating or creating an instance of InsertFarmerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
