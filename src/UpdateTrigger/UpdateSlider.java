package UpdateTrigger;

import javax.swing.event.*;
import javax.swing.JSlider;

public class UpdateSlider implements ChangeListener {
	
	private boolean refresh;
	
	public UpdateSlider() {
		this.refresh = false;
	}
	
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        this.refresh = true;
    }
    
    public boolean getSliderChange() {
    	return this.refresh;
    }
    
    public void reset() {
    	this.refresh = false;
    }
}