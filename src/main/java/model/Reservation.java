/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 00:45:38
 */
package model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import exception.EventContingentException;
import exception.ReservationCancelException;


/**
 * @author fbussmann
 *
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long  id;

    @OneToOne
    private Event event;

    @Column
    private int   ticketAmount;

    @ManyToOne
    @JoinColumn( nullable = false )
    private User  user;

    @Column
    @Temporal( TemporalType.TIMESTAMP )
    private Date  issueDate;

    public Reservation() {
        this.issueDate = new Date();
    }

    public Reservation( final Event event, final int amountTickets, final User user ) throws EventContingentException {
        event.reduceContingent( amountTickets );
        this.event = event;
        this.ticketAmount = amountTickets;
        this.user = user;
        this.issueDate = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public void setId( final Long id ) {
        this.id = id;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent( final Event event ) {
        this.event = event;
    }

    public int getTicketAmount() {
        return this.ticketAmount;
    }

    public void setTicketAmount( final int ticketAmount ) {
        this.ticketAmount = ticketAmount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser( final User user ) {
        this.user = user;
    }

    public Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate( final Date issueDate ) {
        this.issueDate = issueDate;
    }

    public void cancel() throws ReservationCancelException {
        if ( this.event.getDate().getTime() < new Date().getTime() ) {
            throw new ReservationCancelException();
        }

        this.event.raiseContingent( this.ticketAmount );
        this.ticketAmount = 0;
    }

}
