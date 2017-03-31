/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 01:25:12
 */
package de.hsw.jee.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsw.jee.exception.EventContingentException;
import de.hsw.jee.model.Event;
import de.hsw.jee.model.Reservation;
import de.hsw.jee.model.Type;
import de.hsw.jee.model.User;
import de.hsw.jee.service.UserService;


/**
 * @author agraf
 *
 */

@Named
@RequestScoped
public class DatabaseInit {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserService   userService;

    @Transactional
    public void reset() {
        try {
            delete();
            add();
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Die Datenbank wurde zurückgesetzt.",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        } catch ( Exception ex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                    "Diee Datenbestand konnte aufgrund eines Fehlers nicht zurück gesetzt werden.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            ex.printStackTrace();
        }
    }

    @Transactional
    public void drop() {
        try {
            delete();
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Der Datenbank wurde geleert.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        } catch ( Exception ex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                    "Der Datenbestand konnte aufgrund eines Fehlers nicht geleert werden.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            ex.printStackTrace();
        }
    }

    @Transactional
    private void delete() {
        this.entityManager.createQuery( "DELETE FROM Reservation" ).executeUpdate();
        this.entityManager.createQuery( "DELETE FROM Event" ).executeUpdate();
        this.entityManager.createQuery( "DELETE FROM User" ).executeUpdate();
    }

    @Transactional
    private void add() throws ParseException, EventContingentException {
        // Add users
        User user1 = new User( "admin@admin.de", "admin", "admin", 'm', true );
        User user2 = new User( "foo@bar.de", "foo", "bar", 'm', true );
        User user3 = new User( "test@test.de", "test", "test", 'w', true );
        User user4 = new User( "user@user.de", "user", "user", 'm', false );
        User user5 = new User( "sonst@was.com", "sonst", "was", 'w', false );
        User user6 = new User( "keine@idee.de", "keine", "idee", 'm', false );

        this.userService.addUser( user1, "admin" );
        this.userService.addUser( user2, "admin" );
        this.userService.addUser( user3, "admin" );
        this.userService.addUser( user4, "user" );
        this.userService.addUser( user5, "user" );
        this.userService.addUser( user6, "user" );

        // Add events
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );

        Event event1 = new Event( "Parkway Drive",
                "Die australischen Metaller machen auf ihrer aktuellen Europa Tour auch in Deutschland halt!",
                sdf.parse( "2017-05-01 20:00" ), "Münster", 1000, Type.Konzert, user1, false );
        Event event2 = new Event( "Otto",
                "Der lustigste Ostfriese seit der Eiszeit – mindestens! Jetzt präsentiert Otto sein neues Programm „Holdrio Again“ live!",
                sdf.parse( "2017-12-24 20:00" ), "Berlin", 100, Type.Comedy, user1, true );
        Event event3 = new Event( "Adoro - Tour 2018",
                "Adoro hauchen mit ihren wunderschönen klassischen Stimmen populären Hits Gefühl und Romantik ein.",
                sdf.parse( "2018-01-15 20:00" ), "Düsseldorf", 112, Type.Kultur, user1, true );
        Event event4 = new Event( "Madame Tussauds Berlin",
                "Persönlichkeiten und Berühmtheiten von Boris Becker bis hin zu Herbert Grönemeyer und George Clooney - täuschend echt!",
                sdf.parse( "2017-07-13 20:00" ), "Berlin", 436, Type.Messe, user1, true );
        Event event5 = new Event( "Bayer 04 Leverkusen - 1. FC Köln",
                "Tore bejubeln mit deinen Freunden – bei Bayer 04!", sdf.parse( "2017-09-19 20:00" ), "Leverkusen", 10,
                Type.Sport, user2, false );
        Event event6 = new Event( "Hamlet",
                "»Sein oder Nicht-Sein.« Shakespeares berühmte Rachetragödie Hamlet in einer Neuinszenierung am Schauspiel Hannover.",
                sdf.parse( "2017-10-10 20:00" ), "Hannover", 123, Type.Theather, user2, true );
        Event event7 = new Event( "Disneys ALADDIN",
                "Das neue Musical vom Broadway um die zeitlose Geschichte von Aladdin, Dschinni und den drei Wünschen!",
                sdf.parse( "2017-08-08 20:00" ), "München", 510, Type.Musical, user2, true );
        Event event8 = new Event( "Michael Mittermeier: Wild - das neue Programm",
                "Der bayerische Komiker kommt mit neuem Programm auf Tour!", sdf.parse( "2017-06-28 20:00" ), "Hameln",
                56, Type.Comedy, user2, true );
        Event event9 = new Event( "NightWash Live",
                "Eine bunte Mischung von Top-Comedians und neuen Talenten aus dem Comedy-Waschsalon - jetzt Tickets sichern!",
                sdf.parse( "2020-12-04 20:00" ), "Frankfurt am Main", 69, Type.Comedy, user3, true );
        Event event10 = new Event( "Amon Amarth: Jomsviking European Tour",
                "Die schwedischen Death-Metal-Wikinger Amon Amarth kommen auf ihrer „Jomsviking“-Europatour nach Deutschland!",
                sdf.parse( "2017-07-01 20:00" ), "New York", 43, Type.Konzert, user3, true );
        Event event11 = new Event( "Enter Shikari", "Die Ikonen des Trancecore kommen 2017 auf Deutschland-Tour!",
                sdf.parse( "2017-05-12 20:00" ), "Paris", 123, Type.Konzert, user3, false );
        Event event12 = new Event( "KoRn", "Die Nu-Metal-Götter KoRn kehren im August nach Deutschland zurück!",
                sdf.parse( "2017-11-11 20:00" ), "Moskau", 8000, Type.Konzert, user3, false );

        this.entityManager.persist( event1 );
        this.entityManager.persist( event2 );
        this.entityManager.persist( event3 );
        this.entityManager.persist( event4 );
        this.entityManager.persist( event5 );
        this.entityManager.persist( event6 );
        this.entityManager.persist( event7 );
        this.entityManager.persist( event8 );
        this.entityManager.persist( event9 );
        this.entityManager.persist( event10 );
        this.entityManager.persist( event11 );
        this.entityManager.persist( event12 );

        Reservation res1 = new Reservation( event1, 5, user1 );
        Reservation res2 = new Reservation( event2, 7, user1 );
        Reservation res3 = new Reservation( event3, 8, user1 );
        Reservation res4 = new Reservation( event4, 3, user2 );
        Reservation res5 = new Reservation( event5, 2, user2 );
        Reservation res6 = new Reservation( event6, 1, user2 );
        Reservation res7 = new Reservation( event7, 5, user3 );
        Reservation res8 = new Reservation( event8, 4, user3 );
        Reservation res9 = new Reservation( event9, 3, user4 );
        Reservation res10 = new Reservation( event10, 2, user4 );
        Reservation res11 = new Reservation( event11, 11, user4 );
        Reservation res12 = new Reservation( event12, 3, user5 );
        Reservation res13 = new Reservation( event1, 23, user5 );
        Reservation res14 = new Reservation( event1, 5, user6 );
        Reservation res15 = new Reservation( event2, 2, user6 );
        Reservation res16 = new Reservation( event3, 1, user1 );

        this.entityManager.persist( res1 );
        this.entityManager.persist( res2 );
        this.entityManager.persist( res3 );
        this.entityManager.persist( res4 );
        this.entityManager.persist( res5 );
        this.entityManager.persist( res6 );
        this.entityManager.persist( res7 );
        this.entityManager.persist( res8 );
        this.entityManager.persist( res9 );
        this.entityManager.persist( res10 );
        this.entityManager.persist( res11 );
        this.entityManager.persist( res12 );
        this.entityManager.persist( res13 );
        this.entityManager.persist( res14 );
        this.entityManager.persist( res15 );
        this.entityManager.persist( res16 );
    }
}
