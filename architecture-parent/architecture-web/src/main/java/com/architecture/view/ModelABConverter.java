package com.architecture.view;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.archtecture.model.entities.ModelAb;
import com.sgv.model.entities.PapelModel;

@FacesConverter("ModelABConverter")
public class ModelABConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && value.trim().length() > 0) {

			try {

				PapelModel p = new PapelModel();
				p.setCodigo(Long.valueOf(value.toString()));

				return p;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}

		}

		else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return ((ModelAb) object).getCodigo().toString();
		} else {
			return null;
		}
	}
}