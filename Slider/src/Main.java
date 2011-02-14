import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
	private static final long serialVersionUID = 1L;
	private JTextArea input;
	private JTextArea output;

	public static void main(String[] args) {
		new Main().makeFrame();
	}

	private void makeFrame() {
		JFrame frame = new JFrame("Slider Puzzle Maker - Copyright 2011, Bill Wake, All Rights Reserved");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ActionListener listener = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				String[] strings = input.getText().toUpperCase().split("\n");
				Builder builder = new Builder(strings, new Scorer());
				output.setText(builder.build());
			}
		};

		JPanel panel = makeMainPanel(listener); 
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(550, 450);
		frame.setVisible(true);
	}

	private JPanel makeMainPanel(ActionListener listener) {
		JPanel panel = new JPanel(); 
		BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(layout);
		
		input = new JTextArea(20, 15);
		input.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		input.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

		output = new JTextArea(20, 20);
		output.setBorder(BorderFactory.createLineBorder(Color.red, 3));
	    output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		
		panel.add(new JScrollPane(input)); 
		panel.add(makeButton(listener));
		panel.add(new JScrollPane(output));
		return panel;
	}

	private JButton makeButton(ActionListener listener) {
		JButton button = new JButton("Make it");
		button.addActionListener(listener);
		return button;
	}
}
