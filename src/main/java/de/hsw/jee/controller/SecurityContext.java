/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 12:01:36
 */
package de.hsw.jee.controller;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.exception.NotAuthorizedException;
import de.hsw.jee.model.Event;


/**
 * @author fbussmann
 *
 *         Diese Komponente ist für weiterführende Autorisierungsprüfungen verantwortlich. Beispielsweise kann eine
 *         Veranstaltung nur von dem Manager bearbeitet werden, der sie auch erstellt hat.
 */
@Named
@ApplicationScoped
public class SecurityContext {

    @Inject
    private SessionContext sessionContext;

    public String checkOwnership( final Event event ) throws NotAuthorizedException {
        if ( !event.getCreator().equals( this.sessionContext.getCurrentUser() ) ) {
            throw new NotAuthorizedException();
        }

        return null;
    }

}
