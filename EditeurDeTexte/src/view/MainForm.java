package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tests.LaunchEditor;

public class MainForm extends JFrame {
    /**
	 * 
	 */
    private static final long      serialVersionUID     = 3140124981567096416L;
    public static final String     DEFAULT_TITLE        = "Text Editor";
    private static final String    DEFAULT_COMMAND_LINE = "Please, type your command here to start using the software";
    private static final int       DEFAULT_COLUMN_WIDTH = 40;
    private static final Dimension MINIMUM_DIMENSION    = new Dimension(700,
                                                                480);
    private static final Dimension MAX_DIMENSION        = new Dimension(
                                                                Toolkit.getDefaultToolkit()
                                                                        .getScreenSize().width,
                                                                Toolkit.getDefaultToolkit()
                                                                        .getScreenSize().height);
    private JLabel                 m_Text               = new JLabel(
                                                                DEFAULT_COMMAND_LINE);
    private JTextField             m_Command            = new JTextField(
                                                                DEFAULT_COMMAND_LINE,
                                                                DEFAULT_COLUMN_WIDTH);
    private JButton                m_Validate           = new JButton(
                                                                "Validate");
    private JList                  bufferMemory         = new JList();

    public MainForm() {
        if (!System.getProperty("os.name").toLowerCase().contains("mac os")) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                    LaunchEditor.class.getResource("images/notes.png")));
        }
        this.setTitle(DEFAULT_TITLE);
        this.setMinimumSize(MINIMUM_DIMENSION);
        this.setSize(MAX_DIMENSION);
        this.setLayout(new GridLayout(3, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel up = new JPanel(new FlowLayout());
        up.setMinimumSize(MINIMUM_DIMENSION);
        up.add(this.m_Text);
        up.setBorder(BorderFactory.createTitledBorder("Text"));
        JPanel down = new JPanel(new FlowLayout());
        down.add(this.m_Command);
        down.add(this.m_Validate);
        down.setBorder(BorderFactory.createTitledBorder("Management"));
        JScrollPane pane = new JScrollPane(up);
        this.bufferMemory.setBorder(BorderFactory.createTitledBorder("Memory"));
        this.getContentPane().add(pane, BorderLayout.NORTH);
        this.getContentPane().add(down, BorderLayout.SOUTH);
        this.getContentPane().add(new JScrollPane(this.bufferMemory),
                BorderLayout.EAST);
        this.setVisible(true);
    }

    /**
     * @return the bufferMemory
     */
    public JList getBufferMemory() {
        return this.bufferMemory;
    }

    public JTextField getCommand() {
        return this.m_Command;
    }

    public JLabel getText() {
        return this.m_Text;
    }

    public JButton getValidate() {
        return this.m_Validate;
    }

    /**
     * @param aBufferMemory
     *            the bufferMemory to set
     */
    public void setBufferMemory(JList aBufferMemory) {
        this.bufferMemory = aBufferMemory;
    }
}
