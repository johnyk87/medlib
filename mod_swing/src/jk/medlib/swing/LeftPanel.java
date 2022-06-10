package jk.medlib.swing;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class LeftPanel extends JScrollPane {
	
	private static final long serialVersionUID = 1L;
	
	protected Color background;
	protected JPanel leftPanel;
	protected ContentsPanel contentsPanel;

	public LeftPanel()
	{
		super();
		
		background = new Color(255, 255, 255);
		leftPanel = new JPanel();
		contentsPanel = new ContentsPanel();

		BoxLayout layout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(layout);
		leftPanel.setBackground(background);
		
		this.setBackground(background);
		this.setViewportView(leftPanel);
		this.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	public ContentsPanel getContentsPanel()
	{
		return contentsPanel;
	}
}
