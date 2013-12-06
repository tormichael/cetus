package tor.java.cetus.data;

import tor.java.cetus.Cetus;
import JCommonTools.RefBook.*;

public class ceRefBook extends RefBook 
{
	private Cetus _cts;
	
	public ceRefBook(Cetus aCetus)
	{
		_cts = aCetus;
		_init();
	}
	
	/**
	 * Default refbook item. 
	 * 
	 * !!! ID from 1 to 999 reserved for system needs !!!
	 * 
	 * @param _cts
	 */
	private void _init()
	{
		mRBNodes = new rbNode(1, _cts.getString("RefBook.Root.Name"), _cts.getString("RefBook.Root.Alias"));
		
		rbNode rbnCurrency = new rbNode(999, _cts.getString("RefBook.Currency.Name"), _cts.getString("RefBook.Currency.Alias"));
		mRBNodes.getNodes().add(rbnCurrency);
		rbnCurrency.getNodes().add(new rbNode(643, _cts.getString("RefBook.Currency.RUB.Name"), _cts.getString("RefBook.Currency.RUB.Alias")));
		rbnCurrency.getNodes().add(new rbNode(840, _cts.getString("RefBook.Currency.USD.Name"), _cts.getString("RefBook.Currency.USD.Alias")));
		rbnCurrency.getNodes().add(new rbNode(978, _cts.getString("RefBook.Currency.EUR.Name"), _cts.getString("RefBook.Currency.EUR.Alias")));
		
		rbNode rbnExpItem = new rbNode(20, _cts.getString("RefBook.ExpItem.Name"), _cts.getString("RefBook.ExpItem.Alias"));
		mRBNodes.getNodes().add(rbnExpItem);
		rbnExpItem.getNodes().add(new rbNode(21, _cts.getString("RefBook.ExpItem.Food.Name"), _cts.getString("RefBook.ExpItem.Food.Name")));
		rbnExpItem.getNodes().add(new rbNode(22, _cts.getString("RefBook.ExpItem.Clothes.Name"), _cts.getString("RefBook.ExpItem.Clothes.Name")));
		rbnExpItem.getNodes().add(new rbNode(23, _cts.getString("RefBook.ExpItem.Rent.Name"), _cts.getString("RefBook.ExpItem.Rent.Name")));
		rbnExpItem.getNodes().add(new rbNode(24, _cts.getString("RefBook.ExpItem.Household.Name"), _cts.getString("RefBook.ExpItem.Household.Name")));
		rbnExpItem.getNodes().add(new rbNode(25, _cts.getString("RefBook.ExpItem.Education.Name"), _cts.getString("RefBook.ExpItem.Education.Name")));

		rbNode rbnObject = new rbNode(100, _cts.getString("RefBook.Object.Name"), _cts.getString("RefBook.Object.Alias"));
		mRBNodes.getNodes().add(rbnObject);
		rbnObject.getNodes().add(new rbNode(101, _cts.getString("RefBook.Object.Person.Name"), _cts.getString("RefBook.Object.Person.Alias")));
		rbnObject.getNodes().add(new rbNode(102, _cts.getString("RefBook.Object.Auto.Name"), _cts.getString("RefBook.Object.Auto.Alias")));
		
		//mRBNodes.getNodes().add(new ceNode(2, _cts.getString(""), _cts.getString("")));
	}
	

}
