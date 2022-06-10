package jk.medlib.media.events;

import java.io.File;

import jk.medlib.media.types.MediaFile;

public class AddMediaFileEvent extends MediaEvent {
	
	private File file;
	private MediaFile mediaFile;
	
	public AddMediaFileEvent(File file)
	{
		this.file = file;
	}
	
	public File getFile()
	{
		return file;
	}
	
	public void setMediaFile(MediaFile mediaFile)
	{
		this.mediaFile = mediaFile;
	}
	
	public MediaFile getMediaFile()
	{
		return mediaFile;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + ": " + file.getAbsolutePath();
	}
}
