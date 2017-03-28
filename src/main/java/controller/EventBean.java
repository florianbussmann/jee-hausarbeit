/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 22:55:45
 */
package controller;


import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Event;
import model.Type;
import model.User;
import service.EventService;


/**
 * @author agraf
 *
 */
@Named
@RequestScoped
public class EventBean {

    private String         name;

    private String         description;

    private Date           date;

    private String         place;

    private int            contingent;

    private Type           type;

    @Inject
    private SessionContext session;

    @Inject
    private EventService   eventService;

    public String createEvent() {
        User cur = this.session.getCurrentUser();
        if ( cur != null ) {
            if ( cur.isManager() ) {
                this.eventService.addEvent( new Event( this.name, this.description, this.date, this.place,
                        this.contingent, this.type, cur, false ) );
                return "start";
            } else {
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                        "Nur Manager dürfen Veranstaltungen erstellen.", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "createEvent";
            }
        } else {
            // TODO: View theorethisch nicht erreichbar, aber sollte als Annotation aufgenommen werden
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Dieser Bereich ist nur für angemeldete User. Bitte melden Sie sich an.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "login";
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription( final String description ) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate( final Date date ) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace( final String place ) {
        this.place = place;
    }

    public int getContingent() {
        return this.contingent;
    }

    public void setContingent( final int contingent ) {
        this.contingent = contingent;
    }

    public Type getType() {
        return this.type;
    }

    public void setType( final Type type ) {
        System.out.println( "setType: " + type );
        this.type = type;
    }
}
