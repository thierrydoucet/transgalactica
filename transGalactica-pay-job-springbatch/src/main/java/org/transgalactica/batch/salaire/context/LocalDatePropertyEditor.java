package org.transgalactica.batch.salaire.context;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.util.StringUtils;

//TODO : A supprimer lorsque l'implémentation spring sera disponible
public class LocalDatePropertyEditor extends PropertyEditorSupport {

	protected LocalDatePropertyEditor() {
	}

	@Override
	public void setAsText(String text) throws DateTimeParseException {
		if (StringUtils.hasText(text)) {
			setValue(LocalDate.parse(text));
		}
		else {
			setValue(null);
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		LocalDate value = (LocalDate) getValue();
		return (value != null ? value.toString() : "");
	}
}