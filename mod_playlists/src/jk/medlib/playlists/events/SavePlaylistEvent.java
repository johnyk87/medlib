package jk.medlib.playlists.events;

import java.util.List;

import jk.medlib.media.types.MediaFile;
import jk.medlib.playlists.Playlist;


public class SavePlaylistEvent extends PlaylistEvent {
	
	private Playlist pls;
	private List<MediaFile> files;
	
	public SavePlaylistEvent(Playlist pls, List<MediaFile> files)
	{
		this.pls = pls;
		this.files = files;
	}
	
	public Playlist getPlaylist()
	{
		return pls;
	}
	
	public List<MediaFile> getFiles()
	{
		return files;
	}
}
