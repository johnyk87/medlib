package jk.medlib.media.types;

import java.util.Comparator;

public class MediaFileComparator implements Comparator<MediaFile> {

	@Override
	public int compare(MediaFile f1, MediaFile f2)
	{
		return f1.getPath().compareTo(f2.getPath());
	}

}
