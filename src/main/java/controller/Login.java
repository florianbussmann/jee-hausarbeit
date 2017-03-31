/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 18.03.2017 - 21:05:23
 */
package controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import service.UserService;


/**
 * @author agraf
 *
 */
@Named
@RequestScoped
public class Login {
    private String         email;
    private String         password;

    @Inject
    private SessionContext sessionContext;

    @Inject
    private UserService    userService;

    public String doLogin() {
        if ( !this.sessionContext.isLoggedIn() ) {
            User user = this.userService.getUserByEmail( this.email );
            if ( user == null ) {
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                        "Es existiert kein User mit dieser E-Mail Adresse", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "login";
            } else {
                if ( this.userService.checkAuthentication( user, this.password ) ) {
                    this.sessionContext.setCurrentUser( user );
                    System.out.println( this.sessionContext.isLoggedIn() );
                    return this.sessionContext.redirectEntryPoint();
                } else {
                    FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                            "Diese Kombination aus E-Mail-Adresse und Passwort existiert nicht", null );
                    FacesContext.getCurrentInstance().addMessage( null, msg );
                    return "login";
                }
            }
        } else {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Sie sind bereits angemeldet. Bitte melden Sie sich ab, bevor Sie sich mit einem anderen Nutzer anmelden.",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "login";
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail( final String email ) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword( final String password ) {
        this.password = password;
    }
}
