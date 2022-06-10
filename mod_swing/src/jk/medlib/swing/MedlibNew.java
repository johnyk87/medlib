/*
 * Medlib.java
 *
 * Created on __DATE__, __TIME__
 */

package jk.medlib.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import jk.frame.FrameApplication;
import jk.frame.FrameLoader;
import jk.medlib.fs.events.LoadFileEvent;
import jk.medlib.fs.events.LoadFoldersEvent;
import jk.medlib.fs.events.LoadLibraryEvent;
import jk.medlib.media.events.GetMediaFilesEvent;
import jk.medlib.media.events.GetMediaTreeEvent;
import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;

public class MedlibNew extends JFrame {

	private static final long serialVersionUID = 1L;

	private JSplitPane jSplitPane;

	public MedlibNew(List<MediaCategory> trees) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Media Library");
		this.setSize(new Dimension(800, 600));
		this.setBackground(new Color(255, 255, 255));

		LeftPanel left = new MediaCategoryPanel(trees);
		
		jSplitPane = new JSplitPane();
		jSplitPane.setBackground(new Color(255, 255, 255));
		jSplitPane.setDividerLocation(250);
		jSplitPane.setLeftComponent(left);
		jSplitPane.setRightComponent(left.getContentsPanel());

		this.add(jSplitPane);
	}

	public static void main(String args[])
	{
		try
		{
			new FrameLoader().load();
			
			//load library
			LoadLibraryEvent fsEvent = null;
			
			String loadFromFile = FrameApplication.getInstance().getProperty("loadFromFile");
			if(loadFromFile != null && Boolean.parseBoolean(loadFromFile))
				fsEvent = new LoadFileEvent();
			else
				fsEvent = new LoadFoldersEvent();
			
			fsEvent.trigger();
			Map<File, Exception> errors = fsEvent.getErrors();
			List<File> files = new LinkedList<File>(errors.keySet());
			Collections.sort(files);
			System.out.println();
			for(File file : files)
				System.out.println("[ERROR] " + file.getPath());
			
			//load categories
			GetMediaTreeEvent treeEvent = new GetMediaTreeEvent();
			treeEvent.trigger();
			List<MediaCategory> roots = treeEvent.getRoots();
			/*for(MediaCategory root : roots)
				printTree(root);*/
			
			new MedlibNew(roots).setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	@SuppressWarnings("unused")
	private static List<MediaFile> printTree(MediaCategory cat)
	{
		try
		{
			GetMediaFilesEvent getFiles = new GetMediaFilesEvent(cat);
			getFiles.trigger();
			
			List<MediaFile> all = new LinkedList<MediaFile>();
			int size = 0;
	
			if(getFiles.getMediaFiles() != null)
			{
				size = getFiles.getMediaFiles().size();
				all.removeAll(getFiles.getMediaFiles());
				all.addAll(getFiles.getMediaFiles());
			}
			
			System.out.println(cat.getCategoryPath() + " (" + size + ") - " + cat.getChildren().size() + " children");
			for(MediaCategory current : cat.getChildren())
			{
				List<MediaFile> files = printTree(current);
				all.removeAll(files);
				all.addAll(files);
			}
			
			if(cat.getParent() == null)
				System.out.println("[DEBUG] Found " + all.size() + " different files.");
			
			return all;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new LinkedList<MediaFile>();
		}
	}
}