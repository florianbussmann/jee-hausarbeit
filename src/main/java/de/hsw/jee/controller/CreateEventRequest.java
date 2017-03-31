/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 22:55:45
 */
package de.hsw.jee.controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.annotation.ManagementOperation;
import de.hsw.jee.model.Event;
import de.hsw.jee.model.User;
import de.hsw.jee.service.EventService;


/**
 * @author fbussmann
 *
 */
@Named
@RequestScoped
@ManagementOperation
public class CreateEventRequest {

    private Event          event = new Event();

    @Inject
    private EventService   eventService;

    @Inject
    private SessionContext sessionContext;

    public String createEvent() {
        User currentUser = this.sessionContext.getCurrentUser();
        if ( currentUser != null ) {
            if ( currentUser.isManager() ) {
                this.eventService.addEvent( this.event, currentUser );
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                        "Die Veranstaltung wurde erfolgreich angelegt.", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "events";
            } else {
                // nicht mehr erreichbar nach Umstellung auf @ManagementOperation
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                        "Nur Manager dürfen Veranstaltungen erstellen.", null );
                FacesContext.getCurrentInstance().addMessage( null, msg );
                return "createEvent";
            }
        } else {
            // TODO: View theorethisch nicht erreichbar, aber sollte als Annotation aufgenommen werden
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_WARN,
                    "Dieser Bereich ist nur für angemeldete User. Bitte melden Sie sich an.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "login";
        }
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent( final Event event ) {
        this.event = event;
    }

    public EventService getEventService() {
        return this.eventService;
    }

    public void setEventService( final EventService eventService ) {
        this.eventService = eventService;
    }

    public SessionContext getSessionContext() {
        return this.sessionContext;
    }

    public void setSessionContext( final SessionContext sessionContext ) {
        this.sessionContext = sessionContext;
    }

}
