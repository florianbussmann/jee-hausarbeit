/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 16:11:47
 */
package controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;


/**
 * @author agraf
 *
 */
@Named( value = "register" )
@RequestScoped
public class Register {
    private String         email;
    private String         password;
    private String         confirmPassword;
    private String         firstName;
    private String         surname;
    private char           gender;
    @Inject
    private UserService    users;
    @Inject
    private SessionContext session;

    public String register() {
        if ( !this.session.isLoggedIn() ) {
            User user = this.users.getUserByEmail( this.email );
            if ( user == null ) {
                if ( this.confirmPassword.equals( this.password ) ) {
                    user = new User( this.email, this.password, this.firstName, this.surname, this.gender, false );
                    this.users.addUser( user );
                    System.out.println( "Erfolgreich registriert." );
                    return "start.jsf";
                } else {
                    FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                            "Die eingegebenen Passwörte stimmen nicht überein.", null );
                    FacesContext.getCurrentInstance().addMessage( null, msg );
                    return "register.jsf";
                }
            } else {
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                        "Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "register.jsf";
            }
        } else {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Sie sind bereits angemeldet. Bitte melden Sie sich ab, bevor Sie einen neuen Nutzer registrieren",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "register.jsf";
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
