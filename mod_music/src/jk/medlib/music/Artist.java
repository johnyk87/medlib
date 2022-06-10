package jk.medlib.music;

import java.util.List;

import javax.swing.table.TableModel;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class Artist extends MediaCategory {

	public Artist(MediaCategory parent, String name)
	{
		super(parent, name, true, false, true);
	}
	
	//--------------------------------------------------------------------------------
	//table model
	
	@Override
	public TableModel getTableModel(List<MediaFile> files)
	{
		return new ArtistTableModel(files);
	}
}
