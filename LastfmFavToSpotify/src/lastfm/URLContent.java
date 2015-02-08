package lastfm;

import java.io.*;
import java.net.*;

public class URLContent {
	
	static final String DEFAULT_URL_BEGINNING = "http://www.last.fm/user/";
	static final String DEFAULT_URL_ENDING = "/library/loved?sortBy=date&sortOrder=desc&page=";
	static final String NEXT_PAGE = "Next page"; // if there is no such string in the tested page's code, the program reached the last page of favourite tracks list

	private String username;
	private String usedURL;	
	private int pageNumber;
	private boolean itIsLastPage;
	
	public URLContent (String username) {
		
		this.username = username;
		this.pageNumber = 1;
		setNewUrl();
		
	}
	
	public String getPageContent() {
		
		URL currentPage = null;
		BufferedReader in = null;

		String pageContent = "";
		String currentLine;
		
		try {
			currentPage = new URL(usedURL);
			
			in = new BufferedReader (new InputStreamReader (currentPage.openStream()) );
			
			while ( (currentLine = in.readLine()) != null)
				pageContent += currentLine + "\n";
			
			in.close();
		} 
		catch (MalformedURLException e) {
			System.out.println("Wrong URL. Error: " + e.getMessage());
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("Page not found."); // TODO w okienku
			return "";
		}
		catch (IOException e) {
			System.out.println("An IOException was caught: " + e.getMessage());
			System.out.println("ERROR"); // TODO w okienku
			e.printStackTrace();
		}
		
		if (!(pageContent.contains(NEXT_PAGE)))
			itIsLastPage = true;

		return pageContent;
	}
	
	public void increasePageNumber() {
		
		this.pageNumber++;
		
	}
	
	public boolean isItLastPage() {
		
		return itIsLastPage;
		
	}
	
	public void setNewUrl() {
		
		this.usedURL = DEFAULT_URL_BEGINNING + username + DEFAULT_URL_ENDING + Integer.toString(pageNumber);

	}
	
}
