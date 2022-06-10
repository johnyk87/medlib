package jk.medlib.media.events;

import java.util.LinkedList;
import java.util.List;

import jk.medlib.media.types.MediaCategory;


public class GetMediaTreeEvent extends MediaEvent {
	
	private List<MediaCategory> roots;

	public GetMediaTreeEvent()
	{
		roots = new LinkedList<MediaCategory>();
	}

	public void addRoot(MediaCategory root)
	{
		if(root != null && !roots.contains(root))
			roots.add(root);
	}
	
	public List<MediaCategory> getRoots()
	{
		return roots;
	}
}
