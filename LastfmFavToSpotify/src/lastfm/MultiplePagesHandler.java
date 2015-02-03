package lastfm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MultiplePagesHandler {
		
	public void getSonglistsFromAllPages() {
		
		String username = "blindlife"; // TODO username input
		String pageContent;
		String songsList = "";

		URLContent url = new URLContent(username);
		SonglistMaker songlistMaker = new SonglistMaker();
		
		while (!url.isItLastPage()) {
			pageContent = url.getPageContent();
			
			if (pageContent.isEmpty())
				return;
			
			songlistMaker.setNewPageContent(pageContent);
			songsList += songlistMaker.getSongsFromPage();
			
			if (!songlistMaker.getHasFavouriteTracks()) {
				System.out.println("No favourite tracks found.");
				return;
			}
			
			url.increasePageNumber();
			url.setNewUrl();
			
			System.out.println("Making a list. Checking it twice.\nSongs processed: " + songlistMaker.getNumberOfListedSongs()); // TODO processed XXX songs
		}
		
		//TODO list on the screen
		PrintWriter out = null;
		try {
			out = new PrintWriter(username + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		out.println(songsList);
		out.close();
	}
	
}
// TODO wyœwietlanie awatarów 