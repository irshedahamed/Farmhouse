/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import General.Items;
import General.UserDisplay;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
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
@Path("FetchDisplay")
public class FetchDisplayResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FetchDisplayResource
     */
    public FetchDisplayResource() {
    }

    /**
     * Retrieves representation of an instance of REST.FetchDisplayResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@Context HttpHeaders headers, String body) throws ParseException, SQLException {
        String catid;
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(body);
        catid =(String)obj.get("id");
        List<UserDisplay> list = UserDisplay.getItemById(catid);
        Gson g = new Gson();
        String json =g.toJson(list);
        return json;
    }

    /**
     * PUT method for updating or creating an instance of FetchDisplayResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
