package jk.medlib.logger;

import java.util.Date;

import jk.frame.FrameEvent;
import jk.frame.FrameModule;
import jk.frame.events.ExceptionEvent;
import jk.frame.exceptions.HandlerException;
import jk.frame.exceptions.InitializationException;
import jk.medlib.logger.events.LoggerEvent;

/**
 * Printing to stdout
 * 
 * TODO: update logger to read config
 * 
 * 
 * @author JohnyK
 *
 */
public class LoggerModule extends FrameModule {

	public LoggerModule()
	{
		super();
	}

	@Override
	public void init() throws InitializationException
	{
		System.out.println(this.getClass().getName() + " is now active.");
	}

	public void handle(ExceptionEvent event) throws HandlerException
	{
		Exception e = event.getException();
		System.out.println("[" + new Date().toString() + "] Caught exception \"" +
				e.getClass().getName() + "\": " + e.getMessage());
		
		if(event.trace())
			e.printStackTrace();
	}

	public void handle(FrameEvent event) throws HandlerException
	{
		System.out.println("[" + new Date().toString() + "] Caught event \"" +
				event.getClass().getName() + "\": " + event.toString());
	}

	public void handle(LoggerEvent event) throws HandlerException
	{
		System.out.println("[" + new Date().toString() + "] " +
				event.getFrom() + ": " + event.getMessage());
	}
}
