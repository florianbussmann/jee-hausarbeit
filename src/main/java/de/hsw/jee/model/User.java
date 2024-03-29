/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 19.03.2017 - 17:23:47
 */
package de.hsw.jee.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author agraf
 *
 */
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long              id;

    @OneToMany( mappedBy = "creator" )
    private List<Event>       createdEvents;

    @Column( nullable = false, unique = true )
    private String            email;

    @Column( nullable = false )
    private String            password;

    @Column( nullable = false )
    private String            firstName;

    @Column( nullable = false )
    private String            surname;

    @Column( nullable = false )
    private char              gender;

    @Column( nullable = false )
    private boolean           manager;

    @OneToMany( mappedBy = "user" )
    private List<Reservation> reservations;

    public User() {}

    public User( final String email, final String firstName, final String surname, final char gender,
            final boolean manager ) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.manager = manager;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals( final Object other ) {
        if ( other == null ) {
            return false;
        }
        if ( !( other instanceof User ) ) {
            return false;
        }

        if ( other == this ) {
            return true;
        }
        if ( User.class.cast( other ).getId() == this.id ) {
            return true;
        }

        return false;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail( final String email ) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword( final String password ) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname( final String surname ) {
        this.surname = surname;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender( final char gender ) {
        this.gender = gender;
    }

    public boolean isManager() {
        return this.manager;
    }

    public void setManager( final boolean manager ) {
        this.manager = manager;
    }

    public Long getId() {
        return this.id;
    }

    public void setId( final Long id ) {
        this.id = id;
    }

    public List<Event> getCreatedEvents() {
        return this.createdEvents;
    }

    public void setCreatedEvents( final List<Event> createdEvents ) {
        this.createdEvents = createdEvents;
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations( final List<Reservation> reservations ) {
        this.reservations = reservations;
    }
}
