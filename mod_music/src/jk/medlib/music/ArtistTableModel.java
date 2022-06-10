package jk.medlib.music;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import jk.medlib.media.types.MediaFile;

public class ArtistTableModel implements TableModel {
	
	private enum COLUMNS
	{
		NUMBER(0, "Number"),
		NAME(1, "Name"),
		ARTISTS(2, "Artists"),
		FORMAT(3, "Format"),
		PATH(4, "Path");
		
		private int index;
		private String name;
		
		COLUMNS(int index, String name)
		{
			this.index = index;
			this.name = name;
		}
		
		public String toString()
		{
			return name;
		}
		
		public static COLUMNS forIndex(int index)
		{
			COLUMNS[] cols = COLUMNS.values();
			for(COLUMNS col : cols)
			{
				if(col.index == index)
					return col;
			}
			return null;
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private List<MediaFile> files;
	
	public ArtistTableModel(List<MediaFile> files)
	{
		this.files = files;
	}

	@Override
	public int getColumnCount()
	{
		return COLUMNS.values().length;
	}

	@Override
	public int getRowCount()
	{
		return files.size();
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		SongFile file = (SongFile) files.get(row);
		
		switch(COLUMNS.forIndex(col))
		{
			case NUMBER: return file.getValue(SongFile.ATTRIBUTES.NUMBER.name());
			case NAME: return file.getValue(SongFile.ATTRIBUTES.NAME.name());
			case ARTISTS: return file.getValue(SongFile.ATTRIBUTES.ARTIST_STRING.name());
			case FORMAT: return file.getFormat();
			case PATH: return file.getPath();
			default: return "";
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l)
	{
		//TODO: does nothing
	}

	@Override
	public Class<?> getColumnClass(int col)
	{
		return String.class;
	}

	@Override
	public String getColumnName(int col)
	{
		return COLUMNS.forIndex(col).toString();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l)
	{
		//TODO: does nothing
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		//TODO: does nothing
	}

}
