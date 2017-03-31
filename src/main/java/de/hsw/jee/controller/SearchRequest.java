package de.hsw.jee.controller;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.model.Event;
import de.hsw.jee.service.EventService;


@Named
@ViewScoped
public class SearchRequest implements Serializable {
    private String       query;

    private List<Event>  events;

    private List<Event>  filteredEvents;

    @Inject
    private EventService eventService;

    public List<Event> search() {
        return this.eventService.search( this.query );
    }

    public void init() {
        if ( this.query != null ) {
            this.events = search();
        }
    }

    public String redirect() {
        return "/search.jsf?faces-redirect=true&q=" + this.query;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery( final String query ) {
        this.query = query;
    }

    public boolean queryFilled() {
        return this.query != null;
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
