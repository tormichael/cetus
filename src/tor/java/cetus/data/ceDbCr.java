package tor.java.cetus.data;

public class ceDbCr 
{
	private int _db;
	private int _cr;
	public int getDb() { return _db; }
	public void setDb(int _db) { this._db = _db; }
	public int getCr() { return _cr; }
	public void setCr(int _cr) { this._cr = _cr; }

	public ceDbCr(int aDb, int aCr)
	{
		_db = aDb;
		_cr = aCr;
	}
}
