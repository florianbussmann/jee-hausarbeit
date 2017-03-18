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
import javax.inject.Named;


/**
 * @author agraf
 *
 */
@Named( value = "login" )
@RequestScoped
public class Login {
    private String name;
    private String passwort;

    public void doLogin() {
        System.out.println( "Login des Nutzers" );
    }

    public String getName() {
        return this.name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public String getPasswort() {
        return this.passwort;
    }

    public void setPasswort( final String passwort ) {
        this.passwort = passwort;
    }
}
