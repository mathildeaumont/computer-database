package com.excilys.util;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

// TODO: Auto-generated Javadoc
/**
 * The Class Validator.
 */
public class Validator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
	
	/**
	 * Checks if is valid name.
	 *
	 * @param name the name
	 * @return true, if is valid name
	 */
	public static boolean isValidName(String name) {
		if (name == null) {
			LOGGER.error("Error because of name is null");
		} else {
			LOGGER.error("Valid name");
		}
		return name != null;
	}
	
	/**
	 * Checks if is valid date.
	 *
	 * @param date the date
	 * @return true, if is valid date
	 */
	public static boolean isValidDate(String date) {
		if (date != null) {
			if (!date.isEmpty()) {
				String locale = LocaleContextHolder.getLocale().toString();
				if (locale.equals("fr")) {
					if (Pattern.matches(Regex.DATE_FORMAT_FR.getRegex(), date.trim())) {
						LOGGER.error("Valid date");
						return true;
					}
				} else if (locale.equals("en")) {
					if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), date.trim())) {
						LOGGER.error("Valid date");
						return true;
					}
				}
			}
		} 
		LOGGER.error("Error because of date is null");
		return false;
	}
}
