package jk.medlib.fs.events;

import java.io.File;
import java.util.Map;

public class LoadLibraryEvent extends FSEvent {
	
	private Map<File, Exception> errors; 

	public LoadLibraryEvent()
	{
		super();
	}
	
	public void setErrors(Map<File, Exception> errors)
	{
		this.errors = errors;
	}
	
	public Map<File, Exception> getErrors()
	{
		return errors;
	}
}
