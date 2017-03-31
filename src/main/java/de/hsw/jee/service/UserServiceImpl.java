/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 16:57:54
 */
package de.hsw.jee.service;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsw.jee.model.User;
import de.hsw.jee.utils.SecurePassword;


/**
 * @author agraf
 *
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private SecurePassword securePassword;

    @Inject
    private EntityManager  entityManager;

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
    public boolean addUser( final User user, final String password ) {
        try {
            user.setPassword( this.securePassword.getHash( password ) );
            this.entityManager.persist( user );
            return true;
        } catch ( NoSuchAlgorithmException ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean checkAuthentication( final User user, final String password ) {
        try {
            return this.securePassword.checkPassword( password, user.getPassword() );
        } catch ( NoSuchAlgorithmException ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager( final EntityManager entityManager ) {
        this.entityManager = entityManager;
    }

}
