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


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.User;


/**
 * @author agraf
 *
 */
@Named
@SessionScoped
public class SessionContext implements Serializable {
    private static final long serialVersionUID = 6207785445617104641L;

    private User              currentUser;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser( final User currentUser ) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn() {
        return this.currentUser != null;
    }

    public String logout() {
        this.currentUser = null;
        FacesContext.getCurrentInstance().addMessage( "",
                new FacesMessage( FacesMessage.SEVERITY_INFO, "Sie wurden abgemeldet.", "" ) );
        return "login";
    }

    // TODO: store request uri and redirect to it after successful auth
    public String checkAuthentication() {
        if ( !isLoggedIn() ) {
            System.out.println( "Redirect unauthorized user" );
            FacesContext.getCurrentInstance().addMessage( "", new FacesMessage( FacesMessage.SEVERITY_WARN,
                    "Sie haben versucht einen geschützen Bereich einzusehen.", "" ) );
            return "login";
        }
        return null;
    }
}
