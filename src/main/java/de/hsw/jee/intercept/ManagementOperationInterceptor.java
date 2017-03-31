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
 */
@ManagementOperation
@Interceptor
@Dependent
public class ManagementOperationInterceptor {

    @Inject
    private SessionContext sessionContext;

    @AroundInvoke
    public Object checkAuthorization( final InvocationContext context ) throws Exception {
        System.out.println( "interception started" );
        User user = this.sessionContext.getCurrentUser();
        if ( ( user == null ) || !user.isManager() ) {
            System.out.println( "not a manager" );
            throw new NotAuthorizedException();
        }

        System.out.println( "continue" );

        return context.proceed();
    }

}
