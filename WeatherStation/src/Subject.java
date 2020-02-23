import java.util.ArrayList;

public interface Subject {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}

class WeatherData implements Subject {
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = this.observers.indexOf(observer);
		if (i >= 0) {
			this.observers.remove(observer);	
		} 
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < this.observers.size(); i++) {
			Observer observer = this.observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}
	
	public void measurementsChanged() {
		notifyObservers();
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		this.measurementsChanged();
	}
}