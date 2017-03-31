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


import de.hsw.jee.model.Event;


/**
 * @author fbussmann
 *
 */
public class EventContingentException extends Exception {

    private Event event;

    public EventContingentException( final Event event ) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Für das Event " + this.event.toString() + " wurde das Kontingent bereits vollständig ausgeschöpft.";
    }

    @Override
    public String getMessage() {
        return toString();
    }

}
