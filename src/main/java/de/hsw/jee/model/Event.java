/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 22.03.2017 - 21:43:22
 */
package de.hsw.jee.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.hsw.jee.exception.EventContingentException;


/**
 * @author agraf
 *
 */
@Entity
public class Event {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long    id;

    @Column( nullable = false )
    private String  name;

    @Column( nullable = false )
    private String  description;

    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date    date;

    @Column( nullable = false )
    private String  place;

    @Column( nullable = false )
    private int     contingent;

    @Column( nullable = false )
    private Type    type;

    @Column
    private boolean published;

    @ManyToOne
    @JoinColumn( nullable = false )
    private User    creator;

    public Event( final String name, final String description, final Date date, final String place,
            final int contingent, final Type type, final User creator, final boolean published ) {
        super();
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.contingent = contingent;
        this.type = type;
        this.creator = creator;
        this.published = published;
    }

    public Event() {}

    public Long getId() {
        return this.id;
    }

    public void setId( final Long id ) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator( final User creator ) {
        this.creator = creator;
    }

    public void setDescription( final String description ) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate( final Date date ) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace( final String place ) {
        this.place = place;
    }

    public int getContingent() {
        return this.contingent;
    }

    public void setContingent( final int contingent ) {
        this.contingent = contingent;
    }

    public void raiseContingent( final int byAmount ) {
        this.contingent += byAmount;
    }

    public void reduceContingent( final int byAmount ) throws EventContingentException {
        if ( byAmount > this.contingent ) {
            throw new EventContingentException( this );
        }

        this.contingent -= byAmount;
    }

    public Type getType() {
        return this.type;
    }

    public void setType( final Type type ) {
        this.type = type;
    }

    public boolean isPublished() {
        return this.published;
    }

    public void setPublished( final boolean published ) {
        this.published = published;
    }

    public boolean isBookable() {
        return this.isPublished() && ( this.getContingent() > 0 );
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.name;
    }
}
