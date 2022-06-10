package jk.medlib.swing;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ContentsPanel extends JScrollPane {
	
	private static final long serialVersionUID = 1L;

	protected Color background;

	public ContentsPanel()
	{
		super();
		
		background = new Color(255, 255, 255);
		
		this.setBackground(background);
		this.getVerticalScrollBar().setUnitIncrement(16);

		this.setViewportView(getEmptyPanel(background));
	}
	
	public void setContent(JComponent content)
	{
		this.setViewportView(content);
	}
	
	public static JPanel getEmptyPanel(Color background)
	{
		JPanel panel = new JPanel();
		panel.setBackground(background);
		panel.add(new JLabel("No items to display.", JLabel.CENTER));
		
		return panel;
	}
}
