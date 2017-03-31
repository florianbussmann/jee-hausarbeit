/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 10:57:21
 */
package de.hsw.jee.intercept;


import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import de.hsw.jee.annotation.ManagementOperation;
import de.hsw.jee.controller.SessionContext;
import de.hsw.jee.exception.NotAuthorizedException;
import de.hsw.jee.model.User;


/**
 * @author fbussmann
 *
 *         Bei der Verwendung der Annotation @ManagementOperation wird durch diesen Interceptor geprüft, ob der
 *         Aufrufende einer Methode ein Manager ist. Bei der aktuellen Implementierung ist dies weitestgehend nicht
 *         möglich, da der Nutzer bei fehlenden Berechtigungen in den Views automatisch beim Betreten der Seite
 *         umgeleitet wird. Der Interceptor wurde als zusätzliche Sicherheißtsnahme entworfen, der auch automatisch
 *         greifen würde wenn die Präsentationsschicht ausgetauscht werden sollte. Dadurch wird sichergestellt, das zu
 *         keinem Zeitpunkt die Integrität des Datenbestandes verletzt werden kann.
 */
@ManagementOperation
@Interceptor
@Dependent
public class ManagementOperationInterceptor {

    @Inject
    private SessionContext sessionContext;

    @AroundInvoke
    public Object checkAuthorization( final InvocationContext context ) throws Exception {
        User user = this.sessionContext.getCurrentUser();
        if ( ( user == null ) || !user.isManager() ) {
            throw new NotAuthorizedException();
        }

        return context.proceed();
    }

}
