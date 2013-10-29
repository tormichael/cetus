package tor.java.cetus.data;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Base content for all documents, so as account, operation types etc.
 * 
 * @author M.Tor
 * 29.10.2013
 */
@XmlRootElement (name = "cBase")
public class ceBase 
{
    @XmlElementWrapper (name = "ceAccCol")
    @XmlElement (name = "ceAccount")
    public ArrayList<ceAccount> ArrAcc;
    
    public ceBase()
    {
    	
    }

}
