/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 29.03.2017 - 11:29:09
 */
package de.hsw.jee.exception;


/**
 * @author fbussmann
 *
 *         Eine Reservierung kann nicht mehr storniert werden, wenn die Veranstaltung in der Vergangenheit liegt.
 */
public class ReservationCancelException extends Exception {

    public ReservationCancelException() {

    }

    @Override
    public String toString() {
        return "Diese Reservierung kann nicht mehr storniert werden.";
    }

    @Override
    public String getMessage() {
        return toString();
    }

}
