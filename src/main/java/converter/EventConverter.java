/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 28.03.2017 - 16:58:34
 */
package converter;


import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import model.Event;
import service.EventService;


/**
 * @author agraf
 *
 */

// Workaround, da in FacesConverter nicht Injected werden kann
@Named
public class EventConverter implements Converter {

    @Inject
    private EventService eventService;

    /** {@inheritDoc} */
    @Override
    public Object getAsObject( final FacesContext context, final UIComponent component, final String value ) {
        try {
            long id = Long.parseLong( value );
            Event event = this.eventService.getEvent( id );
            if ( event != null ) {
                return event;
            } else {
                FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_WARN,
                        "Es existiert kein Event mit der übergebenen ID.", null );
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect( "events.jsf" );
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                }
                return null;
            }
        } catch ( NumberFormatException nfx ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                    "Der übergebene Parameter kann nicht zu einer ID konvertiert werden.", null );
            throw new ConverterException( msg );
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getAsString( final FacesContext context, final UIComponent component, final Object value ) {
        try {
            Event event = (Event) value;
            return event != null ? Long.toString( event.getId() ) : null;
        } catch ( ClassCastException ccex ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                    "Das übergebene Objekt ist keine Instanz der Klasse Event.", null );
            throw new ConverterException( msg );
        }
    }

}
