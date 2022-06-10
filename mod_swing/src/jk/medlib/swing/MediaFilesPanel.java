package jk.medlib.swing;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalIconFactory;

import jk.medlib.media.types.MediaFile;

public class MediaFilesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<MediaFile> files;
	
	public MediaFilesPanel(List<MediaFile> files, Color background)
	{
		super();
		
		this.files = files;
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBackground(background);
			
		populate();
	}
	
	private void populate()
	{
		for(MediaFile file : files)
		{
			JLabel label = new JLabel(file.getPath(),
					   				  MetalIconFactory.getTreeLeafIcon(),
					   				  JLabel.LEFT);
			
			this.add(label);						
		}
	}
}
