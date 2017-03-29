/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 13:45:29
 */
package controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Event;
import service.EventService;


/**
 * @author fbussmann
 *
 */
@Named
@ViewScoped
public class EventFilterView implements Serializable {

    private static final long serialVersionUID = -2666536764493425372L;

    private List<Event>       events;

    private List<Event>       filteredEvents;

    @Inject
    private EventService      eventService;

    @PostConstruct
    public void init() {
        this.events = this.eventService.getPublishedEvents();
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents( final List<Event> events ) {
        this.events = events;
    }

    public List<Event> getFilteredEvents() {
        return this.filteredEvents;
    }

    public void setFilteredEvents( final List<Event> filteredEvents ) {
        this.filteredEvents = filteredEvents;
    }
}
