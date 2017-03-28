package validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator( "contingentValidator" )
public class ContingentValidator implements Validator {

    @Override
    public void validate( final FacesContext context, final UIComponent component, final Object value )
            throws ValidatorException {
        int contingent = (int) value;
        if ( contingent <= 0 ) {
            FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,
                    "Bitte geben Sie ein Kartenkontingent ein.", null );
            throw new ValidatorException( msg );
        }
    }

}
