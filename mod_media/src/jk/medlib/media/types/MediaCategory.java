package jk.medlib.media.types;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.TableModel;
import javax.swing.tree.TreeNode;

public class MediaCategory implements TreeNode, Comparable<MediaCategory> {
	
	public static final String PATH_SEPARATOR = "/";
	
	private MediaCategory parent;
	private String name;
	private String path;
	private boolean leaf;
	private boolean macro;
	private int count;
	private boolean autoSortFiles;
	private List<MediaCategory> children;
	
	public MediaCategory(MediaCategory parent, String name, boolean leaf, boolean macro, boolean autoSortFiles)
	{
		this.parent = parent;
		this.name = name;
		this.leaf = leaf;
		this.macro = macro;
		this.count = 0;
		this.autoSortFiles = autoSortFiles;
		this.children = new LinkedList<MediaCategory>();
		if(parent != null)
		{
			this.path = parent.getCategoryPath() + name + PATH_SEPARATOR;
			parent.addChild(this);
		}
		else
		{
			this.path = PATH_SEPARATOR + name + PATH_SEPARATOR;
		}
	}
	
	public MediaCategory getParent()
	{
		return parent;
	}
	
	public String getCategoryName()
	{
		return name;
	}
	
	public String getCategoryPath()
	{
		return path;
	}
	
	public boolean isMacro()
	{
		return macro;
	}
	
	public int incCount()
	{
		return ++count;
	}
	
	public int decCount()
	{		
		return (count == 0) ? 0 : --count;
	}
	
	public int getCount()
	{
		if(macro)
			return children.size();
		else
			return count;
	}
	
	public boolean autoSortFiles()
	{
		return autoSortFiles;
	}
	
	public boolean isParentOf(MediaCategory sub)
	{
		return sub != null && sub.path.startsWith(this.path);
	}
	
	private void addChild(MediaCategory cat)
	{
		if(cat != null && !children.contains(cat))
		{
			children.add(cat);
			Collections.sort(children);
		}
	}
	
	public List<MediaCategory> getChildren()
	{
		return children;
	}
	
	public void delete()
	{
		if(parent != null)
			parent.getChildren().remove(this);
	}
	
	@Override
	public String toString()
	{
		return name + " (" + getCount() + ")";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof MediaCategory) && ((MediaCategory) obj).path.equals(this.path);
	}
	
	//--------------------------------------------------------------------------
	// TreeNode methods

	@Override
	public Enumeration<MediaCategory> children()
	{
		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren()
	{
		return true;
	}

	@Override
	public TreeNode getChildAt(int index)
	{
		return children.get(index);
	}

	@Override
	public int getChildCount()
	{
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node)
	{
		return children.indexOf(node);
	}

	@Override
	public boolean isLeaf()
	{
		return leaf;
	}
	
	//--------------------------------------------------------------------------
	// Comparable method

	@Override
	public int compareTo(MediaCategory cat)
	{
		return this.path.compareToIgnoreCase(cat.path);
	}
	
	//--------------------------------------------------------------------------
	// table model
	
	public TableModel getTableModel(List<MediaFile> files)
	{
		return new MediaFileTableModel(files);
	}
}
