package UpdateTrigger;

import javax.swing.event.*;
import javax.swing.JTextField;

public class UpdateTextEntry implements CaretListener {
	
	private boolean refresh;
	
	public UpdateTextEntry() {
		this.refresh = false;
	}
	
	public void caretUpdate(CaretEvent e) {
		JTextField source = (JTextField)e.getSource();
        this.refresh = true;
	}
    
    public boolean getTextChange() {
    	return this.refresh;
    }
    
    public void reset() {
    	this.refresh = false;
    }

}