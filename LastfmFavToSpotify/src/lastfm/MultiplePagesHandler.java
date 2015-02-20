// THE MODEL
package lastfm;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import observerPattern.*;

public class MultiplePagesHandler implements Subject {
	
	private String songsList;
	private String username;
	
	private boolean errorEncountered;
	
	private String errorMessage;
	
	private ArrayList<Observer> observers;
	
	public MultiplePagesHandler() {
		songsList = "";
		observers = new ArrayList<Observer>();
	}
	
	public void getSonglistsFromAllPages() {
		GetSonglists getSonglists = new GetSonglists();
		getSonglists.execute();
	}
	
	class GetSonglists extends SwingWorker<String, String> {
		
		public String doInBackground() throws Exception {
			
			String pageContent;
	
			URLContent url = new URLContent(username);
			SonglistMaker songlistMaker = new SonglistMaker();
			
			while (!url.getItIsLastPage()) {
				pageContent = url.getPageContent();
				
				if (pageContent.isEmpty()) {
					errorEncountered = true;
					errorMessage = "Something went wrong.\nUser not found/connection problems.";
					return "ERROR";
				}
				
				songlistMaker.setNewPageContent(pageContent);
				songsList += songlistMaker.getSongsFromPage();
				
				if (!songlistMaker.getHasFavouriteTracks()) {
					errorEncountered = true;
					errorMessage = "No favourite tracks found on LastFM/Spotify.";
					return "ERROR";
				}
				
				url.increasePageNumber();
				url.setNewUrl();
				
				notifyObservers(songlistMaker.getNumberOfListedSongs());
				
			}
			
			return songsList;
			
		}
		
		public void done() {
			try {
				if (errorEncountered)
					notifyObservers(errorMessage);
				else
					notifyObservers(songsList);
			}
			catch (Exception e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSongsList() {
		return songsList;
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