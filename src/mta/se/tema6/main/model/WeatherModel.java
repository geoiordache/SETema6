package mta.se.tema6.main.model;

import mta.se.tema6.main.interfaces.IModelListener;

import java.util.ArrayList;
import java.util.List;

import mta.se.tema6.main.exceptions.InputException;
/**
 * Class for interacting eith the values for temperature and wind speed
 * @author George Iordache 22/11/2014
 *
 */
public class WeatherModel {

	private List<IModelListener> mListeners;
	private Float temp;
	private Float speed;
	
	/**
     * Constructor
     */
    public WeatherModel() {
        temp = new Float(0);
        speed=new Float(0);
    }
    
    /**
     * Set the values for temperature and wind speed.
     *
     * @param value New value that should be used for the calculator total.
     */
    public void setValue(String value,String val) throws InputException {
        try {
            temp = new Float(value);
            speed=new Float(val);
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }
    
    /**
     * Return current speed and temperature values.
     */
    public String getValue() {
        String tmp=Float.toString(temp);
        return tmp;
    }
    public String getVal()
    {
    	String spd=Float.toString(speed);
    	return spd;
    }

    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }
}
