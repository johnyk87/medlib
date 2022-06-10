package jk.medlib.swing;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import jk.medlib.media.types.MediaCategory;

public class MediaCategoryPanel extends LeftPanel {

	private static final long serialVersionUID = 1L;
		
	private List<MediaCategory> trees;
	private ContentsPanel contents;
	private List<JTree> jTrees;

	public MediaCategoryPanel(List<MediaCategory> trees)
	{
		super();
		
		this.trees = trees;
		this.contents = new ContentsPanel();
		this.jTrees = new LinkedList<JTree>();	
		
		populateLeftPanel();
	}
	
	public ContentsPanel getContentsPanel()
	{
		return contents;
	}
	
	protected void populateLeftPanel()
	{
		jTrees.clear();
		for(int i=0; i<trees.size(); i++)
		{
			JTree tree = new JTree(trees.get(i));
			tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			tree.setAlignmentX(LEFT_ALIGNMENT);
			tree.setAlignmentY(RIGHT_ALIGNMENT);
			
			tree.setBackground(leftPanel.getBackground());
			
			tree.collapsePath(tree.getPathForRow(0));
			
			tree.addTreeSelectionListener(new CategoryTreeSelectionListener(jTrees, contents));
			
			leftPanel.add(tree);
			jTrees.add(tree);
		}
	}
}
