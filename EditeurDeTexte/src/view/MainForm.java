package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3140124981567096416L;

	private static final String DEFAULT_COMMAND_LINE = "Please, type your command here to start using the software";
	private static final int DEFAULT_COLUMN_WIDTH = 40;
	private static final Dimension DEFAULT_DIMENSION = new Dimension(640, 480);

	private JLabel m_Text = new JLabel(DEFAULT_COMMAND_LINE);

	private JTextField m_Command = new JTextField(DEFAULT_COMMAND_LINE,
			DEFAULT_COLUMN_WIDTH);

	private JButton m_Validate = new JButton("Validate");

	public MainForm() {
		this.setSize(DEFAULT_DIMENSION);
		this.setVisible(true);
		this.setLayout(new GridLayout(2, 1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel up = new JPanel(new FlowLayout());
		up.add(m_Text);
		up.setBorder(BorderFactory.createTitledBorder("Text"));
		
		JPanel down = new JPanel(new FlowLayout());
		down.add(m_Command);
		down.add(m_Validate);
		down.setBorder(BorderFactory.createTitledBorder("Management"));
		
		this.add(up);
		this.add(down);
	}

	public JButton getValidate() {
		return m_Validate;
	}

	public JTextField getCommand() {
		return m_Command;
	}

	public JLabel getText() {
		return m_Text;
	}

}
