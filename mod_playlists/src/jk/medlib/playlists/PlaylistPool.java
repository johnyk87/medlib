package jk.medlib.playlists;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

class PlaylistPool {

	private static final List<String> EXTENSIONS = getExtensionList();
	
	private static List<String> getExtensionList()
	{
		List<String> exts = new LinkedList<String>();
		
		exts.add("M3U");
		
		return exts;
	}

	private MediaCategory root;
	private Map<MediaCategory, List<MediaFile>> pool;
	
	public PlaylistPool()
	{
		root = new MediaCategory(null, "Playlists", false, true, true);
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

	public void createPlaylist(File file, List<MediaFile> files)
	{
		Playlist newPls = new Playlist(root, file);
		
		savePlaylist(newPls, files);
	}

	public void savePlaylist(Playlist playlist, List<MediaFile> files)
	{
		pool.put(playlist, files);
	}

	public boolean isPlaylist(File file)
	{
		String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase();
		
		return EXTENSIONS.contains(ext);
	}

	public boolean isPlaylist(MediaFile file)
	{
		return file instanceof Playlist;
	}

	public Playlist addPlaylist(File file)
	{
		Playlist pls = new Playlist(root, file);
		List<MediaFile> files = PlaylistReader.read(pls);
		
		savePlaylist(pls, files);
		
		return pls;
	}

	public void deletePlaylist(Playlist pls)
	{
		pool.remove(pls);
		pls.delete();
	}

	public void deleteFile(MediaFile file)
	{
		List<MediaCategory> cats = getCategories(file);
		
		for(int i=0; i<cats.size(); i++)
		{
			pool.get(cats.get(i)).remove(file);
			cats.get(i).decCount();
		}
	}
	
	public List<MediaCategory> getCategories(MediaFile file)
	{
		List<MediaCategory> cats = new LinkedList<MediaCategory>();
		
		Iterator<MediaCategory> it = pool.keySet().iterator();
		while(it.hasNext())
		{
			MediaCategory cat = it.next();
			List<MediaFile> pls = pool.get(cat);
			
			if(pls.contains(file))
			{				
				if(pls.indexOf(cat) != pls.lastIndexOf(cat))
					for(int i=0; i<pls.size(); i++)
						if(pls.get(i).equals(cat))
							cats.add(cat);
				else
					cats.add(cat);
			}
		}
		
		return cats;
	}
}
