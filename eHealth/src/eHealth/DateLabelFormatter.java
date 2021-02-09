package eHealth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;
/**
 * This class is used to ensure that whenever the JDatePicker in the GUI is used the selected date is correctly formated
 * @author johannes
 *
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    /**
	 * <h4>String to value</h4>
	 * Method to convert a string to a date for the JDatePicker
	 * <p>
	 * @param text
	 * @return date for JDatePicker
	 */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    /**
	 * <h4>Value to String</h4>
	 * Method to create a string out of the selected date in the JDatePicker
	 * <p>
	 * @param value
	 * @return date as string
	 */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
