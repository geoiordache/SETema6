package mta.se.tema6.main.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



import mta.se.tema6.main.interfaces.IController;
import mta.se.tema6.main.interfaces.IModelListener;
import mta.se.tema6.main.interfaces.IView;
import mta.se.tema6.main.model.WeatherModel;

/**
 * Class which implements the controls the graphics for the application
 * @author George Iordache 22/11/2014
 *
 */
@SuppressWarnings("serial")
public class WeatherView extends JFrame implements IModelListener,IView{

	//View Components
	private JTextField mTemperature=new JTextField(18);
	private JTextField mWindSpeed=new JTextField(8);
	private JButton mUpdateBtn=new JButton("Update");
	
	//Constructor for the weather view
	public WeatherView()
	{
		mTemperature.setEditable(false);
		mWindSpeed.setEditable(false);
		
		//Layout the components
		JPanel content=new JPanel();
		content.setLayout(new FlowLayout());
        content.add(new JLabel("Temperature:"));
        content.add(mTemperature);
        content.add(new JLabel("Wind Speed:"));
        content.add(mWindSpeed);
        content.add(mUpdateBtn);
        
        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private WeatherModel mModel;
	/**
     * Sets the view's reference to the model - Only get operations allowed
     * Sets temperature and wind speed
     * @param model The weather model
     */
    public void addModel(WeatherModel model) {
        mModel = model;
        //System.out.println("model.getValue()"+model.getValue());
        mTemperature.setText(model.getVal()+" Celsius degrees.");
        //System.out.println("model.getVal()"+model.getVal());
        mWindSpeed.setText(model.getValue()+" m/s.");
    }
    
	/**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
        mUpdateBtn.setActionCommand(IController.ACTION_UPDATE);
        mUpdateBtn.addActionListener(controller);
    }    
    
	@Override
	public void onMessage(boolean isError, String message) {
		if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }
		
	}

	//When the Update button is hit
	@Override
	public void onUpdate() {
		mTemperature.setText(mModel.getVal()+" Celsius degrees.");
		mWindSpeed.setText(mModel.getValue()+" m/s.");
	}

}
