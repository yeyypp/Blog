# Observer

- A subject maintains a list of observers and notifies them automatically of any
state changes.

```
public class Weather {
    private List<WeatherObsever> obseverList;

    public Weather() {
        obseverList = new LinkedList<>();
    }

    public void addObserver(WeatherObsever obsever) {
        obseverList.add(obsever);
    }

    public void removeObserver(WeatherObsever obsever) {
        obseverList.remove(obsever);
    }

    public void notifyAllObservers() {
        for (WeatherObsever observer : obseverList) {
            observer.update();
        }
    }
}
```
[observer](https://design-patterns.readthedocs.io/zh_CN/latest/behavioral_patterns/observer.html)