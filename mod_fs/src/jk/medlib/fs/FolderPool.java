package jk.medlib.fs;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

class FolderPool {

	private MediaCategory root;
	private Map<MediaCategory, List<MediaFile>> pool;
	
	public FolderPool()
	{
		root = new MediaCategory(null, "Folders", false, false, true);
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

	public void addFile(MediaFile file)
	{
		MediaCategory cat = getCategory(file.getFile());
		if(cat == null)
			cat = newMediaCategory(file.getFile());
		
		if(!pool.get(cat).contains(file))
		{
			pool.get(cat).add(file);
			cat.incCount();
		}
	}

	public void deleteFile(MediaFile file)
	{
		MediaCategory cat = getCategory(file.getFile());
		if(cat == null)
			return;
		
		if(pool.get(cat).contains(file))
		{
			pool.get(cat).remove(file);
			cat.decCount();
		}
	}
	
	private String getMediaPath(File file)
	{
		String path = file.getAbsolutePath();

		path = path.substring(0, path.lastIndexOf(File.separator) + 1);
		path = path.replace(File.separator, MediaCategory.PATH_SEPARATOR);
		if(path.charAt(1) == ':')
			path = path.replace(":", "");
		if(path.startsWith("/"))
			path = path.substring(1);

		path = root.getCategoryPath() + path;

		return path;
	}
	
	private MediaCategory getCategory(String path)
	{
		Iterator<MediaCategory> it = pool.keySet().iterator();
		
		while(it.hasNext())
		{
			MediaCategory cat = it.next();

			if(cat.getCategoryPath().equals(path))
				return cat;
		}
		return null;
	}
	
	private MediaCategory getCategory(File file)
	{
		return getCategory(getMediaPath(file));
	}
	
	private MediaCategory newMediaCategory(File file)
	{
		String path = getMediaPath(file);
		
		String[] folders = path.split(MediaCategory.PATH_SEPARATOR);
		MediaCategory parent = root;
		MediaCategory cat = null;
		String temp = root.getCategoryPath();
		
		for(int i=2; i<folders.length; i++)
		{
			temp += folders[i] + MediaCategory.PATH_SEPARATOR;
			
			if((cat = getCategory(temp)) == null)
			{
				cat = new MediaCategory(parent, folders[i], false, false, true);
				pool.put(cat, new LinkedList<MediaFile>());
			}
			
			parent = cat;
		}

		return parent;
	}
}
