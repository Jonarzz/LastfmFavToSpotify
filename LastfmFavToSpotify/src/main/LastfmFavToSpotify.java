package main;

import lastfm.MultiplePagesHandler;
import gui.*;

public class LastfmFavToSpotify {

	public static void main(String[] args) {
		
		GUIView theView = new GUIView();
		MultiplePagesHandler theModel = new MultiplePagesHandler();
		
		GUIController theController = new GUIController(theView, theModel);

		theView.setVisible(true);
		
	}

}