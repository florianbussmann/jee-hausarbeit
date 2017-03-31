/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 16:11:47
 */
package de.hsw.jee.controller;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.model.User;
import de.hsw.jee.service.UserService;


/**
 * @author agraf
 *
 */
@Named
@ViewScoped
public class Register implements Serializable {
    private static final long serialVersionUID = -2030156315717245369L;

    private String            email;
    private String            password;
    private String            confirmPassword;
    private String            firstName;
    private String            surname;
    private char              gender;

    @Inject
    private UserService       userService;

    @Inject
    private SessionContext    sessionContext;

    public String register() {
        if ( !this.sessionContext.isLoggedIn() ) {
            User user = this.userService.getUserByEmail( this.email );
            if ( user == null ) {
                if ( this.confirmPassword.equals( this.password ) ) {
                    user = new User( this.email, this.firstName, this.surname, this.gender, false );
                    this.userService.addUser( user, this.password );
                    FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                            "Ihre Registrierung war erfolgreich. Bitte melden Sie sich an.", null );
                    FacesContext.getCurrentInstance().addMessage( null, msg );
                    return "login";
                } else {
                    FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                            "Die eingegebenen Passwörte stimmen nicht überein.", null );
                    FacesContext.getCurrentInstance().addMessage( null, msg );
                    return "register";
                }
            } else {
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                        "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "register";
            }
        } else {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Sie sind bereits angemeldet. Bitte melden Sie sich ab, bevor Sie einen neuen Nutzer registrieren",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "register";
        }
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword( final String confirmPassword ) {
        this.confirmPassword = confirmPassword;
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

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname( final String surname ) {
        this.surname = surname;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender( final char gender ) {
        this.gender = gender;
    }

}
