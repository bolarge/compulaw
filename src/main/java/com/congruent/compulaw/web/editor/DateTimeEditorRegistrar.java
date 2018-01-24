package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class DateTimeEditorRegistrar implements PropertyEditorRegistrar {
	
	private DateTimeFormatter dateTimeFormatter;
	
	public DateTimeEditorRegistrar(){}

	public DateTimeEditorRegistrar(String dateFormatPattern) {
		dateTimeFormatter = DateTimeFormat.forPattern(dateFormatPattern);
	}

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(DateTime.class, new DateTimeEditor(dateTimeFormatter));		
	}
	
	private static class DateTimeEditor extends PropertyEditorSupport {
		
		private DateTimeFormatter dateTimeFormatter;
		
		public DateTimeEditor(DateTimeFormatter dateTimeFormatter) {
			this.dateTimeFormatter = dateTimeFormatter;
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			setValue(DateTime.parse(text, dateTimeFormatter));
		}
		}
}
