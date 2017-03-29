/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 11:21:07
 */
package controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.EventContingentException;
import model.Event;
import model.Reservation;
import service.ReservationService;


/**
 * @author fbussmann
 *
 */
@Named
@RequestScoped
public class ReservationRequest {

    private Event              event;
    private int                amountTickets = 1;

    @Inject
    private ReservationService reservationService;

    @Inject
    private SessionContext     sessionContext;

    public void init() {}

    public void submit() {
        try {
            Reservation reservation = this.reservationService.submitReservation( this.event, this.amountTickets,
                    this.sessionContext.getCurrentUser() );
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Die Reservierung wurde erfolgreich durchgeführt. Dein Reservierungscode lautet "
                            + reservation.getId() + ".",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        } catch ( EventContingentException ex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, ex.getMessage(), null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        }
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent( final Event event ) {
        this.event = event;
    }

    public int getAmountTickets() {
        return this.amountTickets;
    }

    public void setAmountTickets( final int amountTickets ) {
        this.amountTickets = amountTickets;
    }

    public ReservationService getReservationService() {
        return this.reservationService;
    }

    public void setReservationService( final ReservationService reservationService ) {
        this.reservationService = reservationService;
    }

    public SessionContext getSessionContext() {
        return this.sessionContext;
    }

    public void setSessionContext( final SessionContext sessionContext ) {
        this.sessionContext = sessionContext;
    };
}
