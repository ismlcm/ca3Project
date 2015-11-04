/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.User;
import facades.UserFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import org.eclipse.persistence.internal.core.helper.CoreClassConstants;

/**
 *
 * @author Ismail Cam
 */
public class RunFacade
{

    public static void main( String[] args )
    {
        UserFacade uu = new UserFacade();

        User u = new User( "ismail2", "ismail2" );
        u.AddRole( "User" );
        uu.addUser( u );

        System.out.println( uu.getUser2( "ismail2" ).getUserName() );

        //System.out.println( u.getAll() );
//        UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("PU"));
//        List roles = new ArrayList();
//        roles.add("User");
//        User u = new User("Test", "Test", roles);
//        uf.addUser( u );
    }
}
