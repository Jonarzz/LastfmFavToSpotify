// THE CONTROLLER

package gui;

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
		
		theModel.addObserver(this);
	}
	
	public void update(int numberOfListedSongs) {
		theView.setListedSongsArea("Generating a list.\n" + Integer.toString(numberOfListedSongs) + "songs listed.");
	}
	
	public void update(String errorMessage) {
		theView.setListedSongsArea(errorMessage);
	}
	
	private class GenerateListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
				theModel.setUsername(theView.getUsername());
				theModel.getSonglistsFromAllPages();
				
				if (!theModel.getErrorEncountered())
					theView.setListedSongsArea(theModel.getSongsList());
				
		}
		
	}

}
