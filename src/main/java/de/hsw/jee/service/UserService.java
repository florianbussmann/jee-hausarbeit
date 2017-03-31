/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 27.03.2017 - 18:44:34
 */
package de.hsw.jee.service;


import de.hsw.jee.model.User;


/**
 * @author fbussmann
 *
 */
public interface UserService {

    boolean addUser( User user, String password );

    boolean checkAuthentication( User user, String password );

    User getUserByEmail( String email );

}
