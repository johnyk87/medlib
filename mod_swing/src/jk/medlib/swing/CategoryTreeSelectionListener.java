package jk.medlib.swing;

import java.util.Collections;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import jk.medlib.media.events.GetMediaFilesEvent;
import jk.medlib.media.types.MediaCategory;
import jk.medlib.media.types.MediaFile;
import jk.medlib.media.types.MediaFileComparator;

public class CategoryTreeSelectionListener implements TreeSelectionListener
{		
	private List<JTree> trees;
	private ContentsPanel contents;
	
	public CategoryTreeSelectionListener(List<JTree> trees, ContentsPanel contents)
	{
		this.trees = trees;
		this.contents = contents;
	}

	@Override
	public void valueChanged(TreeSelectionEvent ev)
	{
		JTree tree = (JTree) ev.getSource();
		
		if(tree.getSelectionPath() == null)
			return;
		
		unselectTrees(tree);
		
		MediaCategory cat = (MediaCategory) tree.getLastSelectedPathComponent();
		
		if(cat == null)
			return;
		
		if(cat.isMacro())
		{
			if(cat.getChildren() == null || cat.getChildren().size() == 0)
				contents.setContent(ContentsPanel.getEmptyPanel(contents.background));
			else
				contents.setContent(new CategoriesContentsPanel(cat.getChildren(), contents.background));
		}
		else
		{
			GetMediaFilesEvent event = new GetMediaFilesEvent(cat);
			
			try
			{
				event.trigger();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			List<MediaFile> files = event.getMediaFiles();

			if(files == null || files.size() == 0)
			{
				contents.setContent(ContentsPanel.getEmptyPanel(contents.background));
			}
			else
			{
				if(cat.autoSortFiles())
					Collections.sort(files, new MediaFileComparator());
				
				contents.setContent(new MediaFilesTable(cat, files, contents.background));
			}
		}
	}
	
	private void unselectTrees(JTree tree)
	{
		for(int i=0; i<trees.size(); i++)
		{
			if(!trees.get(i).equals(tree))
				trees.get(i).setSelectionPath(null);
		}
	}
}