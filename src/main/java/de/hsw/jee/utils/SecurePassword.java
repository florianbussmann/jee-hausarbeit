/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 10:02:11
 */
package de.hsw.jee.utils;


import java.security.NoSuchAlgorithmException;


/**
 * @author fbussmann
 *
 */
public interface SecurePassword {

    public String getHash( String password ) throws NoSuchAlgorithmException;

    public boolean checkPassword( String password, String storedHash ) throws NoSuchAlgorithmException;

}
