/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 16:57:54
 */
package service;


import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import model.User;


/**
 * @author agraf
 *
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {
    private HashMap<String, User> users;

    @Inject
    private EntityManager         entityManager;

    public User getUserByEmail( final String email ) throws IllegalStateException {
        List users = this.entityManager.createQuery( "SELECT user FROM User user WHERE user.email = :email" )
                .setParameter( "email", email ).getResultList();
        if ( users.size() == 1 ) {
            return (User) users.get( 0 );
        } else if ( users.size() == 0 ) {
            return null;
        } else {
            throw new IllegalStateException();
        }
    }

    @Transactional
    public void addUser( final User user ) {
        this.entityManager.persist( user );
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager( final EntityManager entityManager ) {
        this.entityManager = entityManager;
    }

}