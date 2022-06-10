package jk.medlib.playlists;

import java.io.File;

import jk.frame.FrameModule;
import jk.frame.exceptions.HandlerException;
import jk.frame.exceptions.InitializationException;
import jk.medlib.media.events.AddMediaFileEvent;
import jk.medlib.media.events.DeleteMediaFileEvent;
import jk.medlib.media.events.GetMediaFilesEvent;
import jk.medlib.media.events.GetMediaTreeEvent;
import jk.medlib.media.events.MediaEvent;
import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;
import jk.medlib.playlists.events.CreatePlaylistEvent;
import jk.medlib.playlists.events.PlaylistEvent;
import jk.medlib.playlists.events.SavePlaylistEvent;

public class PlaylistsModule extends FrameModule {
	
	PlaylistPool playlistPool;

	public PlaylistsModule()
	{
		super();
	}

	@Override
	public void init() throws InitializationException
	{
		System.out.println(this.getClass().getName() + " is now active.");
		playlistPool = new PlaylistPool();
	}

	public void handle(GetMediaTreeEvent event) throws HandlerException
	{		
		event.addRoot(playlistPool.getRoot());
	}

	public void handle(GetMediaFilesEvent event) throws HandlerException
	{
		MediaCategory cat = event.getCategory();
		if(cat != null && playlistPool.getRoot().isParentOf(cat))
			event.setMediaFiles(playlistPool.getMediaFiles(cat));
	}

	public void handle(AddMediaFileEvent event) throws HandlerException
	{
		File file = event.getFile();
		if(file != null && playlistPool.isPlaylist(file))
			event.setMediaFile(playlistPool.addPlaylist(file));
	}

	public void handle(DeleteMediaFileEvent event) throws HandlerException
	{
		MediaFile mediaFile = event.getFile();
		if(mediaFile != null)
			if(playlistPool.isPlaylist(mediaFile))
				playlistPool.deletePlaylist((Playlist) mediaFile);
			else
				playlistPool.deleteFile(mediaFile);
	}

	public void handle(MediaEvent event) throws HandlerException
	{
		throw new HandlerException("Unsupported event.");
	}

	public void handle(CreatePlaylistEvent event) throws HandlerException
	{
		playlistPool.createPlaylist(event.getFile(), event.getFiles());
	}

	public void handle(SavePlaylistEvent event) throws HandlerException
	{
		playlistPool.savePlaylist(event.getPlaylist(), event.getFiles());
	}

	public void handle(PlaylistEvent event) throws HandlerException
	{
		throw new HandlerException("Unsupported event.");
	}
}
