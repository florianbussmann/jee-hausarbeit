/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 01:05:21
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
import de.hsw.jee.exception.EventContingentException;
import de.hsw.jee.exception.ReservationCancelException;
import de.hsw.jee.model.Event;
import de.hsw.jee.model.Reservation;
import de.hsw.jee.model.User;


/**
 * @author fbussmann
 *
 */

@Named
@ApplicationScoped
public class ReservationService {

    @Inject
    private EntityManager  entityManager;

    @Inject
    private SessionContext session;

    @ManagementOperation
    public List<Reservation> getReservations() {
        TypedQuery<Reservation> query = this.entityManager
                .createQuery(
                        "SELECT reservation FROM Reservation reservation WHERE reservation.event.creator = :user AND reservation.event.date > :sysdate",
                        Reservation.class )
                .setParameter( "user", this.session.getCurrentUser() )
                .setParameter( "sysdate", new Date( System.currentTimeMillis() ) );
        return query.getResultList();
    }

    public List<Reservation> getReservationsForEvent( final Event event ) {
        TypedQuery<Reservation> query = this.entityManager
                .createQuery( "SELECT reservation FROM Reservation reservation WHERE reservation.event = :event",
                        Reservation.class )
                .setParameter( "event", event );
        return query.getResultList();
    }

    public List<Reservation> getReservationsForUser( final User user ) {
        TypedQuery<Reservation> query = this.entityManager
                .createQuery(
                        "SELECT reservation FROM Reservation reservation WHERE reservation.user = :user AND reservation.event.date > :sysdate",
                        Reservation.class )
                .setParameter( "user", user ).setParameter( "sysdate", new Date( System.currentTimeMillis() ) );
        return query.getResultList();
    }

    @Transactional
    public Reservation submitReservation( final Event event, final int amountTickets, final User user )
            throws EventContingentException {
        Reservation reservation = new Reservation( event, amountTickets, user );
        this.entityManager.persist( reservation );
        this.entityManager.merge( event );
        return reservation;
    }

    @Transactional
    public void cancelReservation( final Reservation reservation ) throws ReservationCancelException {
        reservation.cancel();
        this.entityManager.merge( reservation.getEvent() );
        this.entityManager.merge( reservation );
    }

}
