/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 16:57:54
 */
package controller;


import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.User;


/**
 * @author agraf
 *
 */
@Named( value = "userService" )
@ApplicationScoped
public class UserService {
    private HashMap<String, User> users;

    @PersistenceContext( unitName = "h2" )
    private EntityManager         entityManager;

    public UserService() {
        // TODO: Lese User aus Datenbank
        this.users = new HashMap<>();
        this.users.put( "test", new User( "test", "test", "test", "test", 'm', true ) );
    }

    public User getUserByEmail( final String email ) {
        return this.users.get( email );
    }

    @Transactional
    public void addUser( final User user ) {
        this.users.put( user.getEmail(), user );
        this.getEntityManager().persist( user );
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager( final EntityManager entityManager ) {
        this.entityManager = entityManager;
    }
}
