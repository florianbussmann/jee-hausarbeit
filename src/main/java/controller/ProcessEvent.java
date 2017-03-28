package controller;


import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Event;
import model.Type;


@Named
@ConversationScoped
public class ProcessEvent implements Serializable {
    private Event          event;

    @Inject
    private SessionContext session;

    public void init() {}

    public Event getCur() {
        return this.event;
    }

    public void setEvent( final Event event ) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }

    public String getName() {
        return this.event.getName();
    }

    public String getDescription() {
        return this.event.getDescription();
    }

    public int getContingent() {
        return this.event.getContingent();
    }

    public String getPlace() {
        return this.event.getPlace();
    }

    public Date getDate() {
        return this.event.getDate();
    }

    public Type getType() {
        return this.event.getType();
    }

    public boolean isPublished() {
        return this.event.isPublished();
    }

    public long getId() {
        return this.event.getId();
    }

    public boolean isOwner() {
        return this.session.getCurrentUser().getId() == this.event.getCreator().getId();
    }

    public void publish() {
        this.event.setPublished( true );
    }
}
