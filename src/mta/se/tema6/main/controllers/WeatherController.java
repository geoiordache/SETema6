package mta.se.tema6.main.controllers;


import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import org.json.JSONException;
import org.json.JSONObject;

import mta.se.tema6.main.exceptions.InputException;

import mta.se.tema6.main.interfaces.IController;
import mta.se.tema6.main.model.WeatherModel;
import mta.se.tema6.main.webservice.GetInformation;
import mta.se.tema6.main.webservice.HttpClient;
import mta.se.tema6.main.interfaces.IView;;

/**
 * The Controller which interacts with both the Model and the View
 * @author George Iordache 22/11/2014
 *
 */

public class WeatherController implements IController{
	
	// The Controller needs to interact with both the Model and View.
    private WeatherModel mModel;
    
    // The list of views that listen for updates
    private List<IView> mViews;

    /**
     * Constructor
     */
    public WeatherController() {
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(ACTION_UPDATE)) {
            // Make the operation
            try {
                JButton source = (JButton) event.getSource();
                if (source != null ) 
                {
                    //Function which calculates temperature and wind speed.
                    makeOperation();
                } 
                else 
                {
                    notifyViews(true, "Invalid operation data");
                }
            } catch (InputException e) {
                notifyViews(true, e.getMessage());
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            }
          } 
		
	}
	
	
	/**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(WeatherModel model) {
        mModel = model;
    }

    /**
     * Notifies the views when an message must be displayed
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }

    /**
     * Random generated values for temperature and wind speed. The operation can be generalized
     * Or values from the web.
     * @param No parameters
     */
    private void makeOperation() throws InputException {
        if (mModel != null) {
        	//Getting values using a web service
        	String informations=HttpClient. getWeatherData("lat=45&lon=26");
        	try {
        		JSONObject jObj = new JSONObject(informations);
				JSONObject coordObj = GetInformation.getObject("main", jObj);
				float temp=GetInformation.getFloat("temp", coordObj);
				float tmp=(float) (temp-273.15);
				JSONObject coordObj2 = GetInformation.getObject("wind", jObj);
				float wind=GetInformation.getFloat("speed", coordObj2);
				mModel.setValue(Float.toString(wind), Float.toString(tmp));
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	//Uncomment the following lines for testing the random values
        	/*Random r = new Random();
        	Random p = new Random();
        	int windspeed = r.nextInt(10 - 0) + 0; 
        	int temperature=p.nextInt(45 - 0)+0;
        	String wind=Integer.toString(windspeed);
        	String temp=Integer.toString(temperature);
        	mModel.setValue(wind,temp); */
        }
    }

}
