package bot;

public interface Sujeito {
	
	public void registerObserver(Observador observer);
	
	public void notifyObservers(long chatId, String moedaData);

}
