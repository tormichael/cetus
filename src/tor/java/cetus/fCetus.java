package tor.java.cetus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import tor.java.cetus.data.ceDocument;
import tor.java.cetus.tm.tmOperation;
import JCommonTools.*;

public class fCetus extends JFrame 
{
	private Cetus _cts;

	private ceDocument _doc;
	private String _currDocFN;
	
	private JMenuBar _mnuBar;
	private JMenu _mnuDoc;
	private JMenuItem _mnuDocNew;
	private JMenuItem _mnuDocLoad;
	private JMenuItem _mnuDocSave;
	private JMenuItem _mnuDocSaveAs;
	private JMenu _mnuOp;
	private JMenuItem _mnuOpNew;
	private JMenuItem _mnuOpEdit;
	private JMenuItem _mnuOpDel;
	private JMenuItem _mnuOpExec;
	private JMenu _mnuTools;
	private JMenuItem _mnuToolsAccounts;
	private JMenuItem _mnuToolsOpTypes;
	private JMenuItem _mnuToolsRefBook;
	private JTable _tabOp;
	private JLabel _sbiMain;
	
	
	public fCetus(Cetus aCetus)
	{
		_cts = aCetus;
		_doc = new ceDocument();
		_currDocFN = null;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(_cts.getImage("cetus.png"));

		/**
		 * M E N U
		 */
		_mnuBar = new JMenuBar();
		this.setJMenuBar(_mnuBar);

		_mnuDoc = new JMenu();
			actPrjNew.putValue(Action.SMALL_ICON, _cts.getImageIcon("Doc.png"));
			_mnuDocNew = _mnuDoc.add(actPrjNew);
			
			_mnuDocLoad = new JMenuItem();
			_mnuDoc.add(_mnuDocLoad);
			_mnuDocLoad.addActionListener(new ActionProjectLoad());
			_mnuDocLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
			
			_mnuDocSave = new JMenuItem(new ActionProjectSave());
			_mnuDoc.add(_mnuDocSave);
			_mnuDocSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
			
			_mnuDocSaveAs = _mnuDoc.add(actPrjSaveAs);
		_mnuBar.add(_mnuDoc)	;

		_mnuOp = new JMenu();
			_mnuOpNew = _mnuOp.add(actOpNew);
			actOpNew.putValue(Action.SMALL_ICON, _cts.getImageIcon("Plus.png"));
			_mnuOpEdit = _mnuOp.add(actOpEdit);
			actOpEdit.putValue(Action.SMALL_ICON, _cts.getImageIcon("Edit.png"));
			_mnuOpDel = _mnuOp.add(actOpDelete);
			actOpDelete.putValue(Action.SMALL_ICON, _cts.getImageIcon("Minus.png"));
			_mnuOpExec = _mnuOp.add(actOpExecute);
			//_mnuOpExec.setSelected(false);
			//_mnuOpExec.setSelectedIcon(_cts.getImageIcon("Key.png"));
			actOpExecute.putValue(Action.SMALL_ICON, _cts.getImageIcon("Key.png"));
		_mnuBar.add(_mnuOp);
		
		_mnuTools = new JMenu();
			_mnuToolsAccounts = _mnuTools.add(actToolsAccounts);
			//actToolsAccounts.putValue(Action.SMALL_ICON, _cts.getImageIcon("Plus.png"));
			_mnuToolsOpTypes = _mnuTools.add(actToolsOpTypes);
			//actToolsOpTypes.putValue(Action.SMALL_ICON, _cts.getImageIcon("Plus.png"));
			_mnuToolsRefBook = _mnuTools.add(actToolsRefBook);
			//actToolsRefBook.putValue(Action.SMALL_ICON, _cts.getImageIcon("Plus.png"));
		_mnuBar.add(_mnuTools);
		

		/**
		 *   T O O L S   B A R
		 */
		JToolBar bar = new JToolBar();
		add(bar, BorderLayout.NORTH);
		bar.add(_mnuDocSave.getAction());
		bar.addSeparator();
		bar.add(_mnuOpNew.getAction());
		bar.add(_mnuOpEdit.getAction());
		bar.add(_mnuOpDel.getAction());
		bar.add(_mnuOpExec.getAction());
		bar.addSeparator();

		
		JPanel pnl = new JPanel(new BorderLayout());
			JPanel pnlFilter = new JPanel(new BorderLayout());
			pnl.add(pnlFilter, BorderLayout.NORTH);
			
			_tabOp = new JTable(new tmOperation(_cts));
			pnl.add(new JScrollPane(_tabOp), BorderLayout.CENTER);
		add(pnl, BorderLayout.CENTER);
		

		/**
		 * S T A T U S   B A R
		 */
		
		JPanel statusBar = new JPanel();
		statusBar.setBorder(BorderFactory.createRaisedBevelBorder());
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(statusBar, BorderLayout.SOUTH);
		_sbiMain = new JLabel();
		_sbiMain.setText("Welcome!");
		_sbiMain.setBorder(BorderFactory.createLoweredBevelBorder());
		statusBar.add(_sbiMain);
		
		
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
		
		UpdateLanguage();
	}

	Action actPrjNew = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		}
	};

	Action actPrjLoad;
	private class ActionProjectLoad extends AbstractAction
	{
		public ActionProjectLoad()
		{
			putValue(Action.SMALL_ICON, _cts.getImageIcon("Download.png"));
		}
		
		public void actionPerformed(ActionEvent arg0) 
		{
			_setCurrentDocumentFileName();
			ceDocument doc = ceDocument.Load(_currDocFN);
			if (doc != null)
				_doc = doc;
			
		}
	}
	
	Action actPrjSave;
	private class ActionProjectSave extends AbstractAction
	{
		public ActionProjectSave()
		{
			putValue(Action.SMALL_ICON, _cts.getImageIcon("Save.png"));
		}
		
		public void actionPerformed(ActionEvent arg0) 
		{
			//JOptionPane.showMessageDialog(fCetus.this, "Do it [Save] later ...");
			if (_currDocFN == null || _currDocFN.length() == 0)
				_setCurrentDocumentFileName();
			
			_doc.Save(_currDocFN);
		}
	}

	Action actPrjSaveAs = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		}
		
	};
	
	Action actOpNew = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			fOperation op = new fOperation(_cts);
			op.setVisible(true);
		}
	};
	Action actOpEdit = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};
	Action actOpDelete = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};
	Action actOpExecute = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};

	Action actToolsAccounts = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};
	Action actToolsOpTypes = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};
	Action actToolsRefBook = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	};
	
	private void UpdateLanguage(){

		setTitle(_cts.getString("Title.cetus"));

		_mnuDoc.setText(_cts.getString("Menu.Document"));
		_mnuDocNew.setText(_cts.getString("Menu.Document.New"));
		_mnuDocLoad.setText(_cts.getString("Menu.Document.Load"));
		_mnuDocSave.setText(_cts.getString("Menu.Document.Save"));
		_mnuDocSaveAs.setText(_cts.getString("Menu.Document.SaveAs"));
		_mnuOp.setText(_cts.getString("Menu.Operation"));
		_mnuOpNew.setText(_cts.getString("Menu.Operation.New"));
		_mnuOpEdit.setText(_cts.getString("Menu.Operation.Edit"));
		_mnuOpDel.setText(_cts.getString("Menu.Operation.Del"));
		_mnuOpExec.setText(_cts.getString("Menu.Operation.Exec"));
		_mnuTools.setText(_cts.getString("Menu.Tools"));
		_mnuToolsAccounts.setText(_cts.getString("Menu.Tools.Account"));
		_mnuToolsOpTypes.setText(_cts.getString("Menu.Tools.OpType"));
		_mnuToolsRefBook.setText(_cts.getString("Menu.Tools.RefBook"));
		
	}
	
	private void LoadProgramPreference()
	{
		Preferences node = Preferences.userRoot().node(Cetus.PREFERENCE_PATH+"/main");
		AsRegister.LoadFrameStateSizeLocation(node, this);
		TableTools.SetColumnsWidthFromString(_tabOp, node.get("TabColWidth_Operation", CC.STR_EMPTY));
	}
	
	private void SaveProgramPreference()
	{
		Preferences node = Preferences.userRoot().node(Cetus.PREFERENCE_PATH+"/main");
		AsRegister.SaveFrameStateSizeLocation(node, this);
		node.put("TabColWidth_Operation", TableTools.GetColumnsWidthAsString(_tabOp));
	}

	private void _setCurrentDocumentFileName()
	{
		JFileChooser fDlg = new JFileChooser();
		FileNameExtensionFilter fnf = new FileNameExtensionFilter("Cetus document", "ced");
		fDlg.setFileFilter(fnf);
		if (_currDocFN != null && _currDocFN.length() > 0)
			fDlg.setCurrentDirectory(new File(_currDocFN));
		
		if (fDlg.showDialog(fCetus.this, _cts.getString("FileChooser.Text.CetusDoc")) == JFileChooser.APPROVE_OPTION)
		{
			_currDocFN = fDlg.getSelectedFile().getPath();
			//String[] ss = fDlg.getSelectedFile().
			//fnf.getExtensions();
		}
		else
		{
			_currDocFN = null;
		}
	}
	
	
	
}
