package tor.java.cetus;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import JCommonTools.AsRegister;


public class Cetus 
{
	public final static String FN_RESOURCE_TEXT = "cetus-text";
	public final static String FD_RESOURCE_IMAGE = "img/";
	public final static String FD_RESOURCE_ICONS = "img/icons/";
	public final static String PREFERENCE_PATH = "/cetus";
	
	private ResourceBundle _bnd;
	private AsRegister _reg;

	public ResourceBundle get_bnd() 
	{
		return _bnd;
	}
	public String getString(String aKey)
	{
		return _bnd.getString(aKey);
	}
	public AsRegister get_reg() 
	{
		return _reg;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		fCetus prg = new fCetus(new Cetus());
		
		prg.setVisible(true);

	}
	
	public Cetus()
	{
		_bnd = ResourceBundle.getBundle(FN_RESOURCE_TEXT);
		_reg = new AsRegister();
	}
	
	public  ImageIcon getImageIcon(String aName)
	{
		URL url = this.getClass().getClassLoader().getResource(FD_RESOURCE_ICONS+aName);
		if (url ==null)
			url = this.getClass().getResource(FD_RESOURCE_ICONS+aName);
		
		if (url != null)
		{
			ImageIcon ico = new ImageIcon(url);
			return new ImageIcon(ico.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		}
		else
		{
			return new ImageIcon();
		}
	}

	public  Image getImage(String aName)
	{
		URL url = this.getClass().getClassLoader().getResource(FD_RESOURCE_IMAGE+aName);
		if (url != null)
		{
			return Toolkit.getDefaultToolkit().getImage(url).getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		}
		else
		{
			return new ImageIcon().getImage();
		}
		
		//return ImageTools.CreateIcon(aName, 24).getImage();
	}

	

}
