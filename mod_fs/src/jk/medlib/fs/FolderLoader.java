package jk.medlib.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jk.medlib.media.events.AddMediaFileEvent;

public class FolderLoader {
	
	static final String PROP_WATCH_FOLDERS = "watchFolders";
	static final String PROP_LIST_FILE = "listFile";
	
	private static final String SEP_WATCH_FOLDERS = ";";
	
	static Map<File, Exception> loadFolders(String watchedFolders)
	{
		Map<File, Exception> errors = new HashMap<File, Exception>();
		String[] folders = watchedFolders.split(SEP_WATCH_FOLDERS);
		
		for(int i=0; i<folders.length; i++)
			load(new File(folders[i]), errors);
		
		return errors;
	}
	
	static Map<File, Exception> loadFile(String listFile)
	{
		Map<File, Exception> errors = new HashMap<File, Exception>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(listFile));
			String line = null;
			int folders = 0;
			
			while((line = br.readLine()) != null)
			{
				if(!line.endsWith("/"))
					load(line, errors);
				else
					folders++;
			}
			
			br.close();
			//System.out.println("[DEBUG] Found " + folders + " folders");
			
			return errors;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return errors;
		}
	}
	
	private static void load(File file, Map<File, Exception> errors)
	{
		try
		{
			if(file.isFile())
			{
				new AddMediaFileEvent(file).trigger();
			}
			if(file.isDirectory())
			{
				for(File current : file.listFiles())
				{
					load(current, errors);
				}
			}
		}
		catch(Exception e)
		{
			errors.put(file, e);
		}
	}
	
	private static void load(String path, Map<File, Exception> errors)
	{
		File file = new File(path);
		try
		{
			AddMediaFileEvent addFile = new AddMediaFileEvent(file);
			addFile.trigger();
			/*if(addFile.getMediaFile() == null)
				System.out.println("[IGNORED] " + path);*/
		}
		catch(Exception e)
		{
			errors.put(file, e);
		}
	}
}
