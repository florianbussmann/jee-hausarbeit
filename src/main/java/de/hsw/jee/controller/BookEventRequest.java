/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 11:21:07
 */
package de.hsw.jee.controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.exception.EventContingentException;
import de.hsw.jee.exception.ReservationCancelException;
import de.hsw.jee.model.Event;
import de.hsw.jee.model.Reservation;
import de.hsw.jee.service.ReservationService;


/**
 * @author fbussmann
 *
 */
@Named
@RequestScoped
public class BookEventRequest {

    private Event              event;
    private int                amountTickets = 1;

    @Inject
    private ReservationService reservationService;

    @Inject
    private SessionContext     sessionContext;

    public void init() {}

    public void cancel( final Reservation reservation ) {
        try {
            this.reservationService.cancelReservation( reservation );
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Die Reservierung " + reservation.getId() + " wurde erfolgreich storniert.", null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        } catch ( ReservationCancelException ex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, ex.getMessage(), null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
        }
    }

    public String submit() {
        try {
            Reservation reservation = this.reservationService.submitReservation( this.event, this.amountTickets,
                    this.sessionContext.getCurrentUser() );
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Die Reservierung wurde erfolgreich durchgeführt. Ihr Reservierungscode lautet "
                            + reservation.getId() + ".",
                    null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return "myReservations";
        } catch ( EventContingentException ex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, ex.getMessage(), null );
            FacesContext.getCurrentInstance().addMessage( null, msg );
            return null;
        }
    }

    public String checkContingent() {
        if ( this.event.getContingent() == 0 ) {
            FacesContext.getCurrentInstance().addMessage( "",
                    new FacesMessage( FacesMessage.SEVERITY_WARN, "Für die Veranstaltung '" + this.event.toString()
                            + "' wurde das Kontingent bereits vollständig ausgeschöpft.", "" ) );
            return "events";
        }

        return null;
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
}
