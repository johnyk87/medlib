package jk.medlib.playlists.events;

import java.io.File;
import java.util.List;

import jk.medlib.media.types.MediaFile;

public class CreatePlaylistEvent extends PlaylistEvent {
	
	private File file;
	private List<MediaFile> files;
	
	public CreatePlaylistEvent(File file, List<MediaFile> files)
	{
		this.file = file;
		this.files = files;
	}
	
	public File getFile()
	{
		return file;
	}
	
	public List<MediaFile> getFiles()
	{
		return files;
	}
}
