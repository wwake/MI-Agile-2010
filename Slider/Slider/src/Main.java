import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main {
	private static final long serialVersionUID = 1L;
	private static JTextArea input;
	private static JButton go;
	private static JTextArea output;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Slider Puzzle Maker - Copyright 2010, Bill Wake, All Rights Reserved");

		JPanel panel = new JPanel(new BorderLayout()); 
		
		input = new JTextArea(20, 15);
		input.setBorder(BorderFactory.createLineBorder(Color.red, 3));

		output = new JTextArea(20,15);
		output.setBorder(BorderFactory.createLineBorder(Color.red, 3));

		go = makeButton();
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(go);
		
		panel.add(input, BorderLayout.WEST);
		panel.add(buttonPanel, BorderLayout.CENTER);
		panel.add(output, BorderLayout.EAST);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(500, 350);
		frame.setVisible(true);
	}

	private static JButton makeButton() {
		JButton button = new JButton("Make it");
		
		ActionListener listener = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[] strings = input.getText().toUpperCase().split("\n");
				Builder builder = new Builder(strings);
				builder.build();
				output.setText(builder.result());
			}
			
		};
		
		button.addActionListener(listener);
		return button;
	}

}
