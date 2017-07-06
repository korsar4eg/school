package org.university.validators;

import org.university.entites.Lesson;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@ManagedBean
@SessionScoped
@FacesValidator("lengthValidator")
public class LengthValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {


        if (value.toString().length() < 3){
            FacesMessage msg = new FacesMessage(
                    "Длина должна быть не менее 3 символов");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }


    }
}
