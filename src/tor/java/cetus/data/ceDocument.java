package tor.java.cetus.data;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import tor.java.cetus.Cetus;

/**
 * Main document content all data, so as operation, transaction etc.
 * 
 * @author M.Tor
 * 29.10.2013
 */
@XmlRootElement (name = "cDoc")
public class ceDocument 
{
	public String BaseFileName;
	
    @XmlElementWrapper (name = "ceOpCol")
    @XmlElement (name = "ceOperation")
	public ArrayList<ceOperation> ArrOp;
	
    @XmlElementWrapper (name = "ceTrCol")
    @XmlElement (name = "ceTransaction")
	public ArrayList<ceTransaction> ArrTr;
	
	public ceDocument()
	{
		BaseFileName = null;
		ArrOp = new ArrayList<ceOperation>();
		ArrTr = new ArrayList<ceTransaction>();
	}
	
	public static ceDocument Load(String aFN)
	{
		ceDocument ret = null;
		
		if (aFN != null && aFN.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(ceDocument.class);
	    		Unmarshaller um = context.createUnmarshaller();
	    		Object obj = um.unmarshal(new File(aFN));
	    		ret = (ceDocument) obj;
	    	}
	    	catch (JAXBException ex)
	    	{
	    		//ex.printStackTrace();
	    	}
		}
		return ret;
	}
	
	public String Save(String aFN)
	{
		String ret = null;
		
		if (aFN != null && aFN.length() > 0)
		{
	    	try
	    	{
	    		JAXBContext context = JAXBContext.newInstance(ceDocument.class);
	    		Marshaller m = context.createMarshaller();
	    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    		m.marshal(this, new File(aFN));
	    	}
	    	catch (JAXBException ex)
	    	{
	    		ret = ex.getMessage();
	    	}
		}
		return ret;
	}

}
