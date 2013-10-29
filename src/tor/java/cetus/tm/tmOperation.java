package tor.java.cetus.tm;

import javax.swing.table.AbstractTableModel;
import tor.java.cetus.Cetus;

public class tmOperation extends AbstractTableModel 
{
	private Cetus _cts;
	
	public tmOperation(Cetus aCTS)
	{
		_cts = aCTS;
	}
	
	@Override
	public int getColumnCount() 
	{
		return 6;
	}
	
	@Override
	public String getColumnName(int column) 
	{
		String ret =  null;
		switch (column) 
		{
		case 0:
			ret = _cts.getString("Table.Operation.Column.Number");
			break;
		case 1:
			ret = _cts.getString("Table.Operation.Column.Date");
			break;
		case 2:
			ret = _cts.getString("Table.Operation.Column.Value");
			break;
		case 3:
			ret = _cts.getString("Table.Operation.Column.Currency");
			break;
		case 4:
			ret = _cts.getString("Table.Operation.Column.Type");
			break;
		case 5:
			ret = _cts.getString("Table.Operation.Column.Comment");
			break;
		default:
			break;
		}
		return (ret != null ? ret : super.getColumnName(column));
	}
	
	@Override
	public int getRowCount() 
	{
		return 0;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		return null;
	}
}
