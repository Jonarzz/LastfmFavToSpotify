package main;

import lastfm.*;

public class LastfmFavToSpotify {

	public static void main(String[] args) {
		
		MultiplePagesHandler multiplePagesHandler = new MultiplePagesHandler();
		multiplePagesHandler.getSonglistsFromAllPages();
		
	}

}