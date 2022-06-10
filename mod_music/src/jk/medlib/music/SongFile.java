package jk.medlib.music;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class SongFile implements MediaFile {
	
	public enum ATTRIBUTES {
		NUMBER,
		NAME,
		ARTIST_STRING,
		ARTISTS,
		FEATURED_ARTISTS
	}

	private static final String MEDIA_TYPE = "SONG";
	
	private int number;
	private String artistString;
	private String name;
	
	private File file;
	
	private List<MediaCategory> artists;
	private List<MediaCategory> featArtists;
	
	public SongFile(File file) throws IllegalArgumentException
	{
		try
		{
			String filename = file.getName();
			if(hasAll(filename))
			{
				this.number = Integer.parseInt(filename.substring(
						1, filename.indexOf(")")));
				this.artistString = filename.substring(
						filename.indexOf(")") + 2, filename.indexOf(" - "));
				this.name = filename.substring(
						filename.indexOf(" - ") + 3, filename.lastIndexOf("."));
			}
			else if(hasArtistAndName(filename))
			{
				this.number = 0;
				this.artistString = filename.substring(
						0, filename.indexOf(" - "));
				this.name = filename.substring(
						filename.indexOf(" - ") + 3, filename.lastIndexOf("."));
			}
			/*else if(hasNumberAndName(filename))
			{
				this.number = Integer.parseInt(filename.substring(
						1, filename.indexOf(")")));
				this.artistString = NO_ARTIST_STRING;
				this.name = filename.substring(
						filename.indexOf(")") + 2, filename.lastIndexOf("."));
			}*/
			else
			{
				throw new IllegalArgumentException("invalid song filename.");
			}
			
			this.file = file;
			
			this.artists = new LinkedList<MediaCategory>();
			this.featArtists = new LinkedList<MediaCategory>();
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(
					"Error while retrieving information from file \"" +
					file.getName() + "\": " + e.getMessage());
		}
	}

	@Override
	public String getDisplayName()
	{
		return artistString + " - " + name;
	}

	@Override
	public Object getValue(String attribute)
	{
		ATTRIBUTES att = ATTRIBUTES.valueOf(attribute);
		
		switch(att)
		{
			case NUMBER: return number;
			case ARTIST_STRING: return artistString;
			case NAME: return name;
			case ARTISTS: return artists;
			case FEATURED_ARTISTS: return featArtists;
			default: return null;
		}
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
		return (obj instanceof SongFile) && ((SongFile) obj).file.equals(this.file);
	}
	
	void setArtists(List<MediaCategory> artists)
	{
		this.artists = artists;
	}
	
	void setFeaturedArtists(List<MediaCategory> featArtists)
	{
		this.featArtists = featArtists;
	}
	
	private boolean hasAll(String string)
	{
		return string.matches("^[(][0-9]*[)]( ).*( - ).*$");
	}
	
	private boolean hasArtistAndName(String string)
	{
		return string.matches("^.*( - ).*$");
	}
}
