package jk.medlib.music;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

class MusicPool {

	private static final List<String> EXTENSIONS = getExtensionList();
	
	private static List<String> getExtensionList()
	{
		List<String> exts = new LinkedList<String>();
		
		exts.add("MP3");
		exts.add("M4A");
		exts.add("WMA");
		exts.add("WAV");
		
		return exts;
	}

	private MediaCategory root;
	private MediaCategory artists;
	private Map<String, MediaCategory> artistMap;
	private Map<MediaCategory, List<MediaFile>> pool;
	
	public MusicPool()
	{
		root = new MediaCategory(null, "Music", false, true, false);
		artists = new MediaCategory(root, "Artists", false, true, true);
		artistMap = new HashMap<String, MediaCategory>();
		pool = new HashMap<MediaCategory, List<MediaFile>>();
	}
	
	public MediaCategory getRoot()
	{
		return root;
	}

	public List<MediaFile> getMediaFiles(MediaCategory category)
	{
		return pool.get(category);
	}

	public boolean isSong(File file)
	{
		String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase();
		
		return EXTENSIONS.contains(ext);
	}

	public boolean isSong(MediaFile file)
	{
		return file instanceof SongFile;
	}

	public SongFile addSong(File file)
	{
		SongFile song = new SongFile(file);
		
		String artistString = (String) song.getValue(SongFile.ATTRIBUTES.ARTIST_STRING.name());
		List<String> artistList = getArtists(artistString);
		List<String> featArtistList = getFeaturedArtists(artistString);
		
		List<MediaCategory> artistsCats = new LinkedList<MediaCategory>();
		List<MediaCategory> featArtistsCats = new LinkedList<MediaCategory>();
		
		for(int i=0; i<artistList.size(); i++)
		{
			String artist = artistList.get(i);
			MediaCategory cat = artistMap.get(artist);
			if(cat == null)
			{
				cat = new Artist(artists, artist);
				pool.put(cat, new LinkedList<MediaFile>());
				artistMap.put(artist, cat);
			}
			
			pool.get(cat).add(song);
			cat.incCount();
			artistsCats.add(cat);
		}
		
		for(int i=0; i<featArtistList.size(); i++)
		{
			String artist = featArtistList.get(i);
			MediaCategory cat = artistMap.get(artist);
			if(cat == null)
			{
				cat = new Artist(artists, artist);
				pool.put(cat, new LinkedList<MediaFile>());
				artistMap.put(artist, cat);
			}
			
			pool.get(cat).add(song);
			cat.incCount();
			featArtistsCats.add(cat);
		}
		
		song.setArtists(artistsCats);
		song.setFeaturedArtists(featArtistsCats);
		
		return song;
	}

	@SuppressWarnings("unchecked")
	public void deleteSong(SongFile file)
	{
		List<MediaCategory> cats = (List<MediaCategory>) file.getValue(SongFile.ATTRIBUTES.ARTISTS.name());
		List<MediaCategory> cats1 =  (List<MediaCategory>) file.getValue(SongFile.ATTRIBUTES.FEATURED_ARTISTS.name());
		
		cats.removeAll(cats1);
		cats.addAll(cats1);
		
		for(int i=0; i<cats.size(); i++)
		{
			MediaCategory cat = cats.get(i);
			List<MediaFile> files = pool.get(cat);
			if(files.contains(file))
			{
				files.remove(file);
				cat.decCount();
				
				if(files.size() == 0)
				{
					pool.remove(cat);
					artistMap.remove(cat.getCategoryName());
					cat.delete();
				}
			}
		}
	}
	
	private List<String> getArtists(String artistString)
	{
		if(artistString == null || artistString.length() == 0)
			throw new IllegalArgumentException("Invalid artist declaration in string: " + artistString);
		
		List<String> artists = new LinkedList<String>();

		String[] split = artistString.split("( )(ft|feat|Ft|Feat)[.]?( )");
		if(split.length == 0 || split[0].length() == 0)
			throw new IllegalArgumentException("Invalid artist declaration in string: " + artistString);
		
		split = split[0].split("(,|( )&)( )");
		
		for(int i=0; i<split.length; i++)
		{
			String current = split[i].trim();
			if(current.length() > 0)
				artists.add(current);
		}
		
		return artists;
	}
	
	private List<String> getFeaturedArtists(String artistString)
	{
		List<String> artists = new LinkedList<String>();

		String[] split = artistString.split("( )(ft|feat|Ft|Feat)[.]?( )");
		if(split.length < 2)
			return artists;
		
		split = split[1].split("(,|( )&)( )");
		
		for(int i=0; i<split.length; i++)
		{
			String current = split[i].trim();
			if(current.length() > 0)
				artists.add(current);
		}
		
		return artists;
	}
}
