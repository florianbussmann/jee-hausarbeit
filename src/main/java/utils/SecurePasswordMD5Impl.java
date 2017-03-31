/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 31.03.2017 - 10:03:14
 */
package utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/**
 * @author fbussmann
 *
 *         http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 */
@Named
@ApplicationScoped
public class SecurePasswordMD5Impl implements SecurePassword {

    /** {@inheritDoc} */
    @Override
    public String getHash( final String password ) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance( "MD5" );
        // Add password bytes to digest
        md.update( password.getBytes() );
        // Get the hash's bytes
        byte[] bytes = md.digest();
        // This bytes[] has bytes in decimal format;
        // Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < bytes.length; i++ ) {
            sb.append( Integer.toString( ( bytes[i] & 0xff ) + 0x100, 16 ).substring( 1 ) );
        }
        // return complete hashed password in hex format
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword( final String password, final String storedHash ) throws NoSuchAlgorithmException {
        return getHash( password ).equals( storedHash );
    }

}
