package tor.java.cetus;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import JCommonTools.AsRegister;
import JCommonTools.CC;
import JCommonTools.TableTools;
import JCommonTools.DB.dDBConnection;

public class fOperation extends JFrame 
{
	private Cetus _cts;

	private boolean _isResultSave;
	
	private JButton _btnSave;
	private JButton _btnCancel;
	
	public fOperation(Cetus aCetus)
	{
		_cts = aCetus;
		_isResultSave = false;

		setIconImage(_cts.getImageIcon("Edit.png").getImage());
		
		GridBagLayout gbl = new GridBagLayout();
		JPanel pnl = new JPanel(gbl);
		
		add(pnl, BorderLayout.CENTER);

		JPanel pnlButton = new JPanel();
		_btnSave = new JButton(actSave);
		_btnSave.setText(_cts.getString("Button.Save"));
		pnlButton.add(_btnSave);
		_btnCancel = new JButton(actCancel);
		_btnCancel.setText(_cts.getString("Button.Cancel"));
		pnlButton.add(_btnCancel);
		this.add(pnlButton, BorderLayout.SOUTH);
		
		LoadProgramPreference ();
		
		this.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				SaveProgramPreference();
				super.windowClosing(e);
			}
		});
	}

	Action actSave = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			_isResultSave = true;
			setVisible(false);
		}
	};
	
	Action actCancel = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			_isResultSave = false;
			setVisible(false);
		}
	};

	private void LoadProgramPreference()
	{
		Preferences node = Preferences.userRoot().node(Cetus.PREFERENCE_PATH+"/operation");
		AsRegister.LoadFrameStateSizeLocation(node, this);
	}
	
	private void SaveProgramPreference()
	{
		Preferences node = Preferences.userRoot().node(Cetus.PREFERENCE_PATH+"/operation");
		AsRegister.SaveFrameStateSizeLocation(node, this);
	}

}
