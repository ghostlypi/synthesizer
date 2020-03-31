package GUI;

import UpdateTrigger.UpdateSlider;
import UpdateTrigger.UpdateTextEntry;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel; 

public class CoreGUI {

	private JFrame frame;
	private JSlider amplitudeSlider;
	private JTextField enterAmplitude;
	public UpdateSlider amplitudeSliderChange;
	public UpdateTextEntry amplitudeTextChange;
	private JTextField enterFrequency;
	private JTextField enterDuration;
	private JSlider frequencySlider;
	private JSlider durationSlider;
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoreGUI window = new CoreGUI();
					window.frame.setVisible(true);
					new Thread() {
						public void run() {
							long start = System.currentTimeMillis();
							while(true) {
								long now = System.currentTimeMillis();
								if(now - start > 8) {
									start = now;
									window.refresh();
								}
							}
						}
					}.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CoreGUI() {
		amplitudeSliderChange = new UpdateSlider();
		amplitudeTextChange = new UpdateTextEntry();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		amplitudeSlider = new JSlider(JSlider.VERTICAL,0,100,50);
		amplitudeSlider.setPaintTicks(true);
		amplitudeSlider.setPaintLabels(true);
		amplitudeSlider.setMajorTickSpacing(10);
		amplitudeSlider.setMinorTickSpacing(1);
		amplitudeSlider.addChangeListener(amplitudeSliderChange);
		amplitudeSlider.setBounds(168, 101, 49, 205);
		frame.getContentPane().add(amplitudeSlider);
		
		enterAmplitude = new JTextField();
		enterAmplitude.setBounds(168, 79, 49, 20);
		enterAmplitude.setText("50");
		enterAmplitude.addCaretListener(amplitudeTextChange);
		frame.getContentPane().add(enterAmplitude);
		enterAmplitude.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Amplitude");
		lblNewLabel.setBounds(168, 61, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		frequencySlider = new JSlider(JSlider.HORIZONTAL,20,720,370);
		frequencySlider.setBounds(274, 114, 200, 45);
		frequencySlider.setMajorTickSpacing(100);
		frequencySlider.setMinorTickSpacing(10);
		frequencySlider.setPaintLabels(true);
		frequencySlider.setPaintTicks(true);
		frame.getContentPane().add(frequencySlider);
		
		durationSlider = new JSlider(JSlider.HORIZONTAL,0,16,16);
		durationSlider.setBounds(274, 170, 200, 45);
		durationSlider.setMajorTickSpacing(1);
		durationSlider.setMinorTickSpacing(10);
		durationSlider.setPaintLabels(true);
		durationSlider.setPaintTicks(true);
		frame.getContentPane().add(durationSlider);
		
		enterFrequency = new JTextField();
		enterFrequency.setBounds(484, 114, 49, 20);
		frame.getContentPane().add(enterFrequency);
		enterFrequency.setColumns(10);
		
		enterDuration = new JTextField();
		enterDuration.setColumns(10);
		enterDuration.setBounds(484, 170, 49, 20);
		frame.getContentPane().add(enterDuration);
	}
	
	private void sync() {
		int amplitude = (enterAmplitude.getText().equals("") || enterAmplitude.getText().contains(" ")) ? 0:Integer.valueOf(enterAmplitude.getText());
		if(amplitudeSliderChange.getSliderChange()) {
			enterAmplitude.setText(""+amplitudeSlider.getValue());
			amplitudeSliderChange.reset();
		}else if(amplitudeTextChange.getTextChange() && amplitude > 200 && amplitude < 800) {
			amplitudeSlider.setValue(amplitude);
			amplitudeTextChange.reset();
		}
		amplitude = (enterAmplitude.getText().equals("") || enterAmplitude.getText().contains(" ")) ? 0:Integer.valueOf(enterAmplitude.getText());
		if(amplitude > 100) {
			enterAmplitude.setText("100");
		}
	}
	
	public void refresh() {
		this.sync();
		frame.repaint();
	}
}
