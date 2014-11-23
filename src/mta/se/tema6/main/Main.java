package mta.se.tema6.main;

import mta.se.tema6.main.controllers.WeatherController;
import mta.se.tema6.main.model.WeatherModel;
import mta.se.tema6.main.views.WeatherView;

/**The entry point to the application
 * Testing Model View Controller
 * @author George Iordache 22/11/2014
 */
public class Main {

	/**
	 *The entry point to the project
	 * @param args The arguments to the executable
	 */
	public static void main(String[] args) {
		// Instantiate the MVC elements
		WeatherModel model = new WeatherModel();
		WeatherView view = new WeatherView();
		WeatherController controller = new WeatherController();

		// Attach the view to the model
		model.addModelListener(view);

		// Tell the view about the model and the controller
		view.addModel(model);
		view.addController(controller);

		// Tell the controller about the model and the view
		controller.addModel(model);
		controller.addView(view);

		// Display the view
		view.setVisible(true);

	}

}
