package mta.se.tema6.main.webservice;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Class used for receiving information from the web.
 * @author George Iordache 22/11/2014
 *
 */
public class GetInformation {
	public static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
	    JSONObject subObj = jObj.getJSONObject(tagName);
	    return subObj;
	}
	 
	public static String getString(String tagName, JSONObject jObj) throws JSONException {
	    return jObj.getString(tagName);
	}
	 
	public static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
	    return (float) jObj.getDouble(tagName);
	}
	 
	public static int  getInt(String tagName, JSONObject jObj) throws JSONException {
	    return jObj.getInt(tagName);
	}
}
