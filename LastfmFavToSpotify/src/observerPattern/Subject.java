package observerPattern;

public interface Subject {
	
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(int number);
	public void notifyObservers(String errorMessage);

}
