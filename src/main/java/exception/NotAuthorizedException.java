/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 11:19:53
 */
package exception;


import javax.faces.context.FacesContext;


/**
 * @author fbussmann
 *
 */
public class NotAuthorizedException extends Exception {

    public NotAuthorizedException() {
        FacesContext.getCurrentInstance().getExternalContext().setResponseStatus( 403 );
    }

}
