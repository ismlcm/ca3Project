package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {
    Gson gson;
    
    public Admin (){
    gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
            +"\"serverTime\": \""+now +"\"}"; 
  }
 
  @GET
  @Path("users")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllUsers() {
      UserFacade uf = new UserFacade();
      List users = uf.getAllUsers();
      return gson.toJson(users);
  }
  
  @DELETE
  @Path("user/{username}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void removeUser(@PathParam("username") String username){
      UserFacade uf = new UserFacade();
      uf.deleteUser(username);
  }
}
