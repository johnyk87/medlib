package jk.medlib.swing;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JTable;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class MediaFilesTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	private MediaCategory cat;
	private List<MediaFile> files;
	
	public MediaFilesTable(MediaCategory cat, List<MediaFile> files, Color background)
	{
		super();
		
		this.cat = cat;
		this.files = files;
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBackground(background);
		this.setFillsViewportHeight(true);
			
		populate();
	}
	
	private void populate()
	{
		this.setModel(cat.getTableModel(files));
		this.repaint();
		this.invalidate();
	}
}
