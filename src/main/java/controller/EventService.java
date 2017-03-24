/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 22:58:15
 */
package controller;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import model.Event;
import model.Type;


/**
 * @author agraf
 *
 */
@Named( value = "eventService" )
@ApplicationScoped
public class EventService {

    @Inject
    private EntityManager entityManager;

    public Type[] getPossibleTypes() {
        return Type.values();
    }

    // @Transactional
    public void addEvent( final Event event ) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist( event );
        this.entityManager.getTransaction().commit();
    }
}
