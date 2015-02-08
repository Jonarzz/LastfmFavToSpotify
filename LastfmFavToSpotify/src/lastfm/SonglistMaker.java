package lastfm;

public class SonglistMaker {
	
	static final String SPOTIFY_TRACK = "spotify:track:";
	static final String USER_NOT_FOUND = "User not found";
	
	private String pageContent;
	
	private int actualSearchingPosition;
	private int actualCopyingPosition;
	private int numberOfListedSongs;
	
	private boolean hasFavouriteTracks;
	
	public void setNewPageContent(String pageContent) {
		
		this.pageContent = pageContent;
		
		actualSearchingPosition = 0;
		actualCopyingPosition = 0;
		hasFavouriteTracks = true;
		
	}
	
	private boolean userHasFavouriteTracks() {
		
		return pageContent.contains(SPOTIFY_TRACK);
		
	}
	
	private String getSongID() {
		
		String songID = "";
		
		while (pageContent.charAt(actualCopyingPosition) != '"') // while loop stops when end of Spotify ID ends
			songID += pageContent.charAt(actualCopyingPosition++);
		
		actualSearchingPosition = actualCopyingPosition;
		
		numberOfListedSongs++;
		
		return songID;
	}
	
	public String getSongsFromPage() {
		
		String songsIDs = "";
		
		if (!userHasFavouriteTracks()) {
			hasFavouriteTracks = false;
			return songsIDs;
		}
	
		actualCopyingPosition = pageContent.indexOf(SPOTIFY_TRACK, actualSearchingPosition); // returns -1 if substring is not found
		
		while (actualCopyingPosition != -1) {
			songsIDs += getSongID() + "\n";
			actualCopyingPosition = pageContent.indexOf(SPOTIFY_TRACK, actualSearchingPosition);
		}
			
		return songsIDs;
	}
	
	public int getNumberOfListedSongs() {
		return numberOfListedSongs;
	}
	
	public boolean getHasFavouriteTracks() {
		return hasFavouriteTracks;
	}
	
}
