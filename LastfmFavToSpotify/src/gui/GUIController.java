// THE CONTROLLER

package gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lastfm.MultiplePagesHandler;
import observerPattern.Observer;

public class GUIController implements Observer {
	
	private GUIView theView;
	private MultiplePagesHandler theModel;
	
	public GUIController(GUIView theView, MultiplePagesHandler theModel) {
		
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addGenerateListener(new GenerateListener());
		this.theView.addCopyToClipboardListener(new CopyToClipboardListener());

	}
	
	public void update(int numberOfListedSongs) {

		theView.setListedSongsArea("Generating a list.\n" + Integer.toString(numberOfListedSongs) + " songs listed.");
		
	}
	
	public void update(String message) {
		theView.setListedSongsArea(message);
	}
	
	private class GenerateListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			theModel.setUsername(theView.getUsername());
			theModel.getSonglistsFromAllPages();

		}
		
	}
	
	private class CopyToClipboardListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String songList = theModel.getSongsList();
			StringSelection stringSelection = new StringSelection(songList);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			
		}
		
	}

}
