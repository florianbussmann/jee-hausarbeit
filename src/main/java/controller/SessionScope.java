/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 17:25:17
 */
package controller;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import model.User;


/**
 * @author agraf
 *
 */
@Named( value = "sessionScope" )
@ApplicationScoped
public class SessionScope {
    private User currentUser;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser( final User currentUser ) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn() {
        return this.currentUser != null;
    }

    public void logOut() {
        this.currentUser = null;
    }
}
