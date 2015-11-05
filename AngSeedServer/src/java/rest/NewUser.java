/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.User;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ismail Cam
 */
@Path( "newuser" )
public class NewUser
{

    Gson gson;
    UserFacade uf = new UserFacade();

    @Context
    private UriInfo context;

    public NewUser()
    {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy( FieldNamingPolicy.IDENTITY ).create();
    }

    /**
     * Retrieves representation of an instance of rest.NewUser
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces( "application/json" )
    public String getJson()
    {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
        
        String s = "Haloo";
        
        return s;
    }

    /**
     * PUT method for updating or creating an instance of NewUser
     *
     * @param content representation for the resource
     *
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes( "application/json" )
    public void putJson( String content )
    {
    }

    @POST
    @Consumes( "application/json" )
    @Path( "adduser" )
    public String createUser( String user )
    {
        User user1 = new User(null, null);
        
        boolean e;
        JsonObject o = new JsonObject();
        user1 = gson.fromJson( user, User.class );
        user1.AddRole( "User" );
        e = uf.addUser( user1 );
        if ( e )
        {
            o.addProperty( "userName", user1.getUserName() );
            o.addProperty( "ErrorMsg", "Has been created" );
            return gson.toJson( o );

        }
        else
        {
            o.addProperty( "userName", "intet" );
            o.addProperty( "ErrorMsg", "could not been created" );
        }
        return gson.toJson( o );
    }
}
