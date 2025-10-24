package racingcar.domain;

import java.util.List;

public class Race {
    private List<Car> cars;
    private int attempts;

    public Race(List<Car> cars, int attempts) {
        this.cars = cars;
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Car> getCars() {
        return cars;
    }
}
