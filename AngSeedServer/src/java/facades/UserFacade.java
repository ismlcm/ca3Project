package facades;

import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserFacade
{

    private final Map<String, User> users = new HashMap<>();
    private EntityManagerFactory emf;

    public UserFacade()
    {
        EntityManagerFactory e = Persistence.createEntityManagerFactory( "PU" );
        emf = e;
//        //Test Users
//        User user = new User( "user", "test" );
//        user.AddRole( "User" );
//        users.put( user.getUserName(), user );
//        User admin = new User( "admin", "test" );
//        admin.AddRole( "Admin" );
//        users.put( admin.getUserName(), admin );
//
//        User both = new User( "user_admin", "test" );
//        both.AddRole( "User" );
//        both.AddRole( "Admin" );
//        users.put( both.getUserName(), both );

//        String username = "iso";
//        String password = "iso";
////
//        User u = new User( username, password );
//        u.AddRole( "User" );
//        users.put( username, u );
//        addUser( u );
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public User getUserByUserId( String id )
    {
        return users.get( id );
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser( String userName, String password )
    {
        User user = getUser2( userName ); //users.get( userName );
        return user != null && user.getPassword().equals( password ) ? user.getRoles() : null;
    }

    public boolean addUser( User user )
    {
        boolean con = false;
        EntityManager em = getEntityManager();
        
        try
        {
            if(!em.contains(user)){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            }  
           
            if (em.contains(user)) {
                con = true;
            }else{
                System.out.println(" Dont  Exists ");
            }
        }
        finally
        {
            em.close();
        }
        return con;
    }

    public User getUser2(String username)
    {
        EntityManager em = getEntityManager();
        //Long idL = (long) id;
        
        User user = null;
        
        try
        {
            user = em.find( User.class, username);
        }
        catch (Exception e)
        {
            
        }
        
        return user;
    }
    
    
    public int getAll()
    {
        int i = 0;
        
        EntityManager em = getEntityManager();

        User user = null;
        Query query = null;
        
        try
        {
            query = em.createQuery( "SELECT u from USER u" ); //em.find( User.class, username );
            //query.setParameter( "username", username);
            List<String> list = query.getResultList();
            
            i = list.size();
        }
        catch (Exception e)
        {
            em.close();
        }
        
        return i;
    }

}
