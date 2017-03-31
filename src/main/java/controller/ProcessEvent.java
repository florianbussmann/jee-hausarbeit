package controller;


import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Event;
import service.EventService;


@Named
@ConversationScoped
public class ProcessEvent implements Serializable {
    private Event          event;

    @Inject
    private SessionContext session;

    @Inject
    private EventService   eventService;

    @Inject
    Conversation           conversation;

    public void init() {
        this.conversation.begin();
    }

    public void setEvent( final Event event ) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

    public boolean isOwner() {
        return this.session.getCurrentUser().getId() == this.event.getCreator().getId();
    }

    public String changeEvent() {
        this.eventService.changeEvent( this.event );
        FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                "Die Veranstaltung wurde erfolgreich geändert.", null );
        FacesContext.getCurrentInstance().addMessage( null, msg );
        return "events";
    }

    @PreDestroy
    public void endConversation() {
        this.conversation.end();
    }
}
