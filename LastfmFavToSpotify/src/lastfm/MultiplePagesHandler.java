// THE MODEL
package lastfm;

import java.util.ArrayList;

import observerPattern.*;

public class MultiplePagesHandler implements Subject {
	
	private String songsList;
	private String username;
	private int numberOfListedSongs;
	
	private boolean errorEncountered;
	
	private ArrayList<Observer> observers;
	
	public MultiplePagesHandler() {
		songsList = "";
		observers = new ArrayList<Observer>();
	}
		
	public void getSonglistsFromAllPages() {
		
		String pageContent;

		URLContent url = new URLContent(username);
		SonglistMaker songlistMaker = new SonglistMaker();
		
		while (!url.isItLastPage()) {
			pageContent = url.getPageContent();
			
			if (pageContent.isEmpty()) {
				errorEncountered = true;
				notifyObservers("Something went wrong.\nUser not found/connection problems.");
				return;
			}
			
			songlistMaker.setNewPageContent(pageContent);
			songsList += songlistMaker.getSongsFromPage();
			
			if (!songlistMaker.getHasFavouriteTracks()) {
				errorEncountered = true;
				notifyObservers("No favourite tracks found on LastFM/Spotify.");
				return;
			}
			
			url.increasePageNumber();
			url.setNewUrl();
			
			notifyObservers(songlistMaker.getNumberOfListedSongs());
		}
		

	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSongsList() {
		return songsList;
	}
		
	public int getNumberOfListedSongs() {
		return numberOfListedSongs;
	}
	
	public boolean getErrorEncountered() {
		return errorEncountered;
	}

	public void addObserver(Observer observer) {
		observers.add(observer);		
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers(int numberOfListedSongs) {
		for (Observer o : observers)	
			o.update(numberOfListedSongs);
	}
	
	public void notifyObservers(String errorMessage) {
		for (Observer o : observers)	
			o.update(errorMessage);
	}
	
}
// TODO wyœwietlanie awatarów 