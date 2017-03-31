/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 22:58:15
 */
package de.hsw.jee.service;


import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hsw.jee.annotation.ManagementOperation;
import de.hsw.jee.controller.SessionContext;
import de.hsw.jee.model.Event;
import de.hsw.jee.model.Type;
import de.hsw.jee.model.User;


/**
 * @author agraf
 *
 */
@Named
@ApplicationScoped
public class EventService {

    @Inject
    private EntityManager  entityManager;

    @Inject
    private SessionContext sessionContext;

    public Type[] getPossibleTypes() {
        return Type.values();
    }

    @Transactional
    public void addEvent( final Event event, final User creator ) {
        event.setCreator( creator );
        this.entityManager.persist( event );
    }

    public List<Event> getPublishedEvents() {
        TypedQuery<Event> query = this.entityManager
                .createQuery( "SELECT event FROM Event event WHERE event.published = true AND event.date > :sysdate",
                        Event.class )
                .setParameter( "sysdate", new Date( System.currentTimeMillis() ) );
        return query.getResultList();
    }

    @ManagementOperation
    public List<Event> getDraftedEvents() {
        TypedQuery<Event> query = this.entityManager.createQuery(
                "SELECT event FROM Event event WHERE event.published = false AND event.creator = :creator",
                Event.class ).setParameter( "creator", this.sessionContext.getCurrentUser() );
        return query.getResultList();
    }

    public List<Event> getVisibleEvents() {
        TypedQuery<Event> query = this.entityManager
                .createQuery(
                        "SELECT event FROM Event event WHERE (event.published='true' OR event.creator= :creator) AND event.date > :sysdate",
                        Event.class )
                .setParameter( "creator", this.sessionContext.getCurrentUser() )
                .setParameter( "sysdate", new Date( System.currentTimeMillis() ) );
        ;
        return query.getResultList();
    }

    @ManagementOperation
    public List<Event> getEventsForUser() {
        TypedQuery<Event> query = this.entityManager
                .createQuery( "SELECT event FROM Event event WHERE event.creator = :creator", Event.class )
                .setParameter( "creator", this.sessionContext.getCurrentUser() );
        return query.getResultList();
    }

    public Event getEvent( final long id ) {
        return this.entityManager.find( Event.class, id );
    }

    @Transactional
    public void publishEvent( final Event event ) {
        event.setPublished( true );
        this.entityManager.merge( event );
    }

    @Transactional
    public void changeEvent( final Event event ) {
        this.entityManager.merge( event );
    }

    public List<Event> search( final String query ) {
        String RegExQuery = ".*" + query + ".*";
        TypedQuery<Event> result = this.entityManager
                .createQuery(
                        "SELECT event FROM Event event WHERE (event.published='true' OR event.creator = :creator) AND (event.name REGEXP :query OR event.description REGEXP :query OR event.place REGEXP :query ) AND event.date > :sysdate ",
                        Event.class )
                .setParameter( "query", RegExQuery ).setParameter( "creator", this.sessionContext.getCurrentUser() )
                .setParameter( "sysdate", new Date( System.currentTimeMillis() ) );
        return result.getResultList();
    }
}
