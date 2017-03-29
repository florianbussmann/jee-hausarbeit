/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 22:58:15
 */
package service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import controller.SessionContext;
import model.Event;
import model.Type;


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
    private SessionContext session;

    public Type[] getPossibleTypes() {
        return Type.values();
    }

    @Transactional
    public void addEvent( final Event event ) {
        this.entityManager.persist( event );
    }

    public List<Event> getEvents( final boolean published ) {
        TypedQuery<Event> query = this.entityManager
                .createQuery( "SELECT event FROM Event event WHERE event.published = :published", Event.class )
                .setParameter( "published", published );
        return query.getResultList();
    }

    public List<Event> getDraftedEvents() {
        return getEvents( false );
    }

    public List<Event> getPublishedEvents() {
        return getEvents( true );
    }

    public List<Event> getVisibleEvents() {
        TypedQuery<Event> query = this.entityManager
                .createQuery( "SELECT event FROM Event event WHERE event.published='true' OR event.creator= :creator",
                        Event.class )
                .setParameter( "creator", this.session.getCurrentUser() );
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
        TypedQuery<Event> result = this.entityManager.createQuery(
                "SELECT event FROM Event event WHERE event.published='true' AND (event.name REGEXP :query OR event.description REGEXP :query )",
                Event.class ).setParameter( "query", RegExQuery );
        return result.getResultList();
    }
}
