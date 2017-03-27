package validator;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator( "dateValidator" )
public class DateValidator implements Validator {

    @Override
    public void validate( final FacesContext context, final UIComponent component, final Object value )
            throws ValidatorException {
        Date date = (Date) value;
        if ( date.before( new Date( System.currentTimeMillis() ) ) ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Bitte geben Sie ein Datum in der Zukunft an.", null );
            throw new ValidatorException( msg );
        }
    }

}
