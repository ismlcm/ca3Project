package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.User;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
@RolesAllowed("User")
public class RestUser {
  Gson gson;
  UserFacade uf = new UserFacade();
  
  public RestUser (){
  gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}"; 
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("user")
  public String getUser(String Username){
      return gson.toJson(uf.getUser2(Username));
  }
  
 
}