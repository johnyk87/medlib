package jk.medlib.media.types;

import java.io.File;

public interface MediaFile {
	
	String getDisplayName();
	Object getValue(String attribute);
	String getMediaType();

	String getFilename();	
	String getPath();
	String getFormat();
	File getFile();

}
