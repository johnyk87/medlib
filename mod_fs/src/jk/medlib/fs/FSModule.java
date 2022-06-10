package jk.medlib.fs;

import jk.frame.FrameModule;
import jk.frame.exceptions.HandlerException;
import jk.frame.exceptions.InitializationException;
import jk.medlib.fs.events.FSEvent;
import jk.medlib.fs.events.LoadFileEvent;
import jk.medlib.fs.events.LoadFoldersEvent;
import jk.medlib.media.events.AddMediaFileEvent;
import jk.medlib.media.events.DeleteMediaFileEvent;
import jk.medlib.media.events.GetMediaFilesEvent;
import jk.medlib.media.events.GetMediaTreeEvent;
import jk.medlib.media.events.MediaEvent;
import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class FSModule extends FrameModule {
	
	private FolderPool folderPool;

	public FSModule()
	{
		super();
	}

	@Override
	public void init() throws InitializationException
	{
		System.out.println(this.getClass().getName() + " is now active.");
		folderPool = new FolderPool();
	}

	public void handle(GetMediaTreeEvent event) throws HandlerException
	{		
		event.addRoot(folderPool.getRoot());
	}

	public void handle(GetMediaFilesEvent event) throws HandlerException
	{
		MediaCategory cat = event.getCategory();
		if(cat != null && folderPool.getRoot().isParentOf(cat))
			event.setMediaFiles(folderPool.getMediaFiles(cat));
	}

	public void handle(AddMediaFileEvent event) throws HandlerException
	{
		MediaFile file = event.getMediaFile();
		if(file != null)
			folderPool.addFile(file);
	}

	public void handle(DeleteMediaFileEvent event) throws HandlerException
	{
		MediaFile mediaFile = event.getFile();
		if(mediaFile != null)
			folderPool.deleteFile(mediaFile);
	}

	public void handle(MediaEvent event) throws HandlerException
	{
		throw new HandlerException("Unsupported event.");
	}

	public void handle(LoadFoldersEvent event) throws HandlerException
	{
		event.setErrors(
				FolderLoader.loadFolders(this.getProperty(FolderLoader.PROP_WATCH_FOLDERS)));
	}

	public void handle(LoadFileEvent event) throws HandlerException
	{
		event.setErrors(
				FolderLoader.loadFile(this.getProperty(FolderLoader.PROP_LIST_FILE)));
	}

	public void handle(FSEvent event) throws HandlerException
	{
		throw new HandlerException("Unsupported event.");
	}
}
