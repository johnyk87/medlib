package jk.medlib.media.events;

import jk.medlib.media.types.MediaFile;

public class DeleteMediaFileEvent extends MediaEvent {
	
	private MediaFile file;
	
	public DeleteMediaFileEvent(MediaFile file)
	{
		this.file = file;
	}
	
	public MediaFile getFile()
	{
		return file;
	}
}
