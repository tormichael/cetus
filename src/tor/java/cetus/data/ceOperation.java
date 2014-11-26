package tor.java.cetus.data;

import java.util.Date;

/**
 * Financial operation. 
 * 
 * @author M.Tor
 * 29.10.2013
 */
public class ceOperation 
{
	private long _code;
	private Date _date;
	private float _value;
	private int _currency;
	private int _type;
	private String _note;
	
	
	
	public ceOperation()
	{
		
	}



	public long get_id() {
		return _code;
	}



	public void set_id(long _id) {
		this._code = _id;
	}



	public Date get_date() {
		return _date;
	}



	public void set_date(Date _date) {
		this._date = _date;
	}



	public float get_value() {
		return _value;
	}



	public void set_value(float _value) {
		this._value = _value;
	}



	public int get_currency() {
		return _currency;
	}



	public void set_currency(int _currency) {
		this._currency = _currency;
	}



	public int get_type() {
		return _type;
	}



	public void set_type(int _type) {
		this._type = _type;
	}



	public String get_note() {
		return _note;
	}



	public void set_note(String _note) {
		this._note = _note;
	}
}
