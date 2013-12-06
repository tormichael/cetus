package tor.java.cetus;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import JCommonTools.AsRegister;
import JCommonTools.CC;
import JCommonTools.CodeText;
import JCommonTools.GBC;
import JCommonTools.TableTools;
import JCommonTools.DB.dDBConnection;

public class fOperation extends JFrame 
{
	private Cetus _cts;

	private boolean _isResultSave;
	
	private JTextField _txfDate;
	private JButton _btnCalendar;
	private JTextField _txfSum;
	private JComboBox<CodeText> _cboCurrency;
	private JComboBox<CodeText> _cboType;
	private JTextArea _txaNote;
	
	
	private JButton _btnSave;
	private JButton _btnCancel;
	
	public fOperation(Cetus aCetus)
	{
		_cts = aCetus;
		_isResultSave = false;

		setIconImage(_cts.getImageIcon("Edit.png").getImage());
		
		GridBagLayout gbl = new GridBagLayout();
		JPanel pnl = new JPanel(gbl);
		// first row
		JLabel lbl = new JLabel(_cts.getString("Label.fOperation.Date"));
		gbl.setConstraints(lbl, new GBC(0,0).setIns(2).setAnchor(GBC.EAST));
		pnl.add(lbl);
		_txfDate = new JTextField(10);
		gbl.setConstraints(_txfDate, new GBC(1,0).setIns(2).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		pnl.add(_txfDate);
		_btnCalendar = new JButton();
		gbl.setConstraints(_btnCalendar, new GBC(2,0).setIns(2).setFill(GBC.BOTH).setWeight(1.0, 0.0));
		pnl.add(_btnCalendar);
		// next row
		lbl = new JLabel(_cts.getString("Label.fOperation.Value"));
		gbl.setConstraints(lbl, new GBC(0,1).setIns(2).setAnchor(GBC.EAST));
		pnl.add(lbl);
		_txfSum = new JTextField();
		gbl.setConstraints(_txfSum, new GBC(1,1).setIns(2).setGridSpan(2, 1).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		pnl.add(_txfSum);
		_cboCurrency = new JComboBox<CodeText>();
		gbl.setConstraints(_cboCurrency, new GBC(3,1).setIns(2).setFill(GBC.HORIZONTAL).setWeight(1., 0.0));
		pnl.add(_cboCurrency);
		// next row
		lbl = new JLabel(_cts.getString("Label.fOperation.Type"));
		gbl.setConstraints(lbl, new GBC(0,2).setIns(2).setAnchor(GBC.EAST));
		pnl.add(lbl);
		_cboType = new JComboBox<CodeText>();
		gbl.setConstraints(_cboType, new GBC(1,2).setIns(2).setFill(GBC.HORIZONTAL).setGridSpan(3, 1).setWeight(1.0, 0.0));
		pnl.add(_cboType);
		// next row
		JPanel pnlAn = new JPanel();
		pnlAn.setBorder(BorderFactory.createTitledBorder(""));
		gbl.setConstraints(pnlAn, new GBC(0,3).setIns(2).setFill(GBC.BOTH).setGridSpan(4, 1).setWeight(1.0, 1.0));
		pnl.add(pnlAn);
		// next row
		lbl = new JLabel(_cts.getString("Label.fOperation.Note"));
		gbl.setConstraints(lbl, new GBC(0,4).setIns(2).setAnchor(GBC.WEST));
		pnl.add(lbl);
		// next row
		_txaNote = new JTextArea();
		JScrollPane spnl = new JScrollPane(_txaNote);
		gbl.setConstraints(spnl, new GBC(0,5).setIns(2).setFill(GBC.BOTH).setGridSpan(4, 1).setWeight(1.0, 1.0));
		pnl.add(spnl);
		
		
		
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
