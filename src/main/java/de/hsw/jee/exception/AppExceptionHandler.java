/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 15:38:24
 */
package de.hsw.jee.exception;


import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;


/**
 * @author fbussmann
 *
 *         Ein zusätzliches Error-Handling für die eigenen Exceptions.
 *
 */
public class AppExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public AppExceptionHandler( final ExceptionHandler wrapped ) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for ( Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();

            Throwable cause = t.getCause();
            while ( cause != null ) {
                if ( cause instanceof NotAuthorizedException ) {
                    NotAuthorizedException nae = (NotAuthorizedException) cause;
                    FacesContext fc = FacesContext.getCurrentInstance();
                    NavigationHandler nav = fc.getApplication().getNavigationHandler();
                    try {
                        nav.handleNavigation( fc, null, "/ERROR/notAuthorized.xhtml" );
                        fc.renderResponse();
                        /*
                         * fc.getExternalContext().responseSendError( 403, nae.getMessage() ); fc.responseComplete();
                         */
                    } finally {
                        i.remove();
                        cause = null;
                    }
                } else {
                    cause = cause.getCause();
                }
            }
        }

        getWrapped().handle();
    }

}
