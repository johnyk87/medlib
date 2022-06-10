package jk.medlib.media.events;

import java.util.List;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;


public class GetMediaFilesEvent extends MediaEvent {
	
	private MediaCategory cat;
	private List<MediaFile> files;
	
	public GetMediaFilesEvent(MediaCategory cat)
	{
		this.cat = cat;
	}
	
	public MediaCategory getCategory()
	{
		return cat;
	}

	public void setMediaFiles(List<MediaFile> files)
	{
		this.files = files;
	}
	
	public List<MediaFile> getMediaFiles()
	{
		return files;
	}
}
