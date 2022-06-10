package jk.medlib.music;

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

public class MusicModule extends FrameModule {
	
	private MusicPool musicPool;

	public MusicModule()
	{
		super();
	}

	@Override
	public void init() throws InitializationException
	{
		System.out.println(this.getClass().getName() + " is now active.");
		musicPool = new MusicPool();
	}

	public void handle(GetMediaTreeEvent event) throws HandlerException
	{		
		event.addRoot(musicPool.getRoot());
	}

	public void handle(GetMediaFilesEvent event) throws HandlerException
	{
		MediaCategory cat = event.getCategory();
		if(cat != null && musicPool.getRoot().isParentOf(cat))
			event.setMediaFiles(musicPool.getMediaFiles(cat));
	}

	public void handle(AddMediaFileEvent event) throws HandlerException
	{
		File file = event.getFile();
		if(file != null && musicPool.isSong(file))
			event.setMediaFile(musicPool.addSong(file));
	}

	public void handle(DeleteMediaFileEvent event) throws HandlerException
	{
		MediaFile mediaFile = event.getFile();
		if(mediaFile != null && musicPool.isSong(mediaFile))
			musicPool.deleteSong((SongFile) mediaFile);
	}

	public void handle(MediaEvent event) throws HandlerException
	{
		throw new HandlerException("Unsupported event.");
	}
}
