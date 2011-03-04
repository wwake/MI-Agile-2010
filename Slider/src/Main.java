import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
	private static final long serialVersionUID = 1L;

	private Formatter formatter = new Formatter();

	private JTextArea input;
	private JTextArea answerKey;
	private JTextArea puzzle;

	public static void main(String[] args) {
		new Main().makeFrame();
	}

	private void makeFrame() {
		JFrame frame = new JFrame("Slider Puzzle Maker - Copyright 2011, Bill Wake, All Rights Reserved");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ActionListener listener = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				String[] strings = input.getText().toUpperCase().split("\n");
				Builder builder = new Builder(strings, new PieceScorer());
				builder.build();
				answerKey.setText(builder.result());
				puzzle.setText(formatter.format(builder.finalPiece()));
			}
		};

		JPanel panel = makeMainPanel(listener); 
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(800, 450);
		frame.setVisible(true);
	}

	private JPanel makeMainPanel(ActionListener listener) {
		JPanel panel = new JPanel(); 
		BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(layout);
		
		input = makeTextArea(15, Font.SANS_SERIF);
		answerKey = makeTextArea(20, Font.MONOSPACED);
	    puzzle = makeTextArea(20, Font.MONOSPACED);
	    
	    panel.add(makeLabeledPanel("Input", input)); 
	    panel.add(makeButton(listener));
		panel.add(makeLabeledPanel("Key", answerKey));
		panel.add(Box.createRigidArea(new Dimension(40, 0)));
		panel.add(makeLabeledPanel("Puzzle", puzzle));
		
		return panel;
	}

	private JPanel makeLabeledPanel(String labelName, JTextArea textArea) {
		JPanel labeled = new JPanel(new BorderLayout());
		labeled.add(new JLabel(labelName, JLabel.CENTER), BorderLayout.NORTH);
		labeled.add(new JScrollPane(textArea), BorderLayout.CENTER);
		return labeled;
	}

	private JTextArea makeTextArea(int textAreaWidth, String fontName) {
		JTextArea textArea = new JTextArea(20, textAreaWidth);
		textArea.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		textArea.setFont(new Font(fontName, Font.PLAIN, 16));
		return textArea;
	}

	private JButton makeButton(ActionListener listener) {
		JButton button = new JButton("Make it");
		button.addActionListener(listener);
		return button;
	}
}
