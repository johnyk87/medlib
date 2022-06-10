package jk.medlib.playlists;

import java.io.File;
import java.io.IOException;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class Playlist extends MediaCategory implements MediaFile {
	
	private static final String MEDIA_TYPE = "PLAYLIST";
	
	private String displayName;
	private File file;

	public Playlist(MediaCategory parent, String name)
	{
		super(parent, name, true, false, false);
		
		displayName = this.getCategoryName();
		this.file = null;
	}

	public Playlist(MediaCategory parent, File file)
	{
		super(parent, file.getName(), true, false, false);
		
		displayName = file.getName();
		this.file = file;
	}

	@Override
	public Object getValue(String attribute)
	{
		return null;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public String getMediaType()
	{
		return MEDIA_TYPE;
	}

	@Override
	public String getFilename()
	{
		if(file == null)
			return null;
		else
			return file.getName();
	}

	@Override
	public String getPath()
	{
		try
		{
			if(file == null)
				return null;
			else
				return file.getCanonicalPath();
		}
		catch(IOException e)
		{
			return file.getAbsolutePath();
		}
	}

	@Override
	public File getFile()
	{
		return file;
	}

	@Override
	public String getFormat()
	{
		if(file == null)
			return null;
		else
			return file.getName().substring(file.getName().lastIndexOf(".") + 1);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof Playlist) && ((Playlist) obj).file.equals(this.file);
	}
}
