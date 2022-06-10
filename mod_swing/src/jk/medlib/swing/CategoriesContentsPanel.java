package jk.medlib.swing;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalIconFactory;

import jk.medlib.media.types.MediaCategory;

public class CategoriesContentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<MediaCategory> cats;
	
	public CategoriesContentsPanel(List<MediaCategory> cats, Color background)
	{
		super();
		
		this.cats = cats;
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBackground(background);
			
		populate();
	}
	
	private void populate()
	{
		for(MediaCategory cat : cats)
		{
			JLabel label = new JLabel(cat.getCategoryName(),
					   				  MetalIconFactory.getTreeFolderIcon(),
					   				  JLabel.LEFT);
			
			this.add(label);						
		}
	}
}
