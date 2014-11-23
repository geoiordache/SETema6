package mta.se.tema6.main.exceptions;

/**
 * Treating exceptions
 * @author George Iordache 22/11/2014
 *
 */
@SuppressWarnings("serial")

public class InputException extends Exception{
	/**
     * The constructor for the input exception
     * @param input The input on witch the exception was obtained
     * @param errorMessage  The default exception message
     */
    public InputException(String input, String errorMessage) {
        super("Input Format exception on \"" + input + "\" : " + errorMessage);
    }
}
