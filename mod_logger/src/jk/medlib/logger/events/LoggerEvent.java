package jk.medlib.logger.events;

import jk.frame.events.MessageEvent;

public class LoggerEvent extends MessageEvent {

	public LoggerEvent(String from, String message)
	{
		super(from, message);
	}

	@Override
	public String toString()
	{
		return "[" + getFrom() + "] " + getMessage();
	}
}
