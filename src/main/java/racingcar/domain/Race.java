package racingcar.domain;


import java.util.List;
import racingcar.strategy.MoveStrategy;

public class Race {
    private List<Car> cars;
    private int attempts;
    private MoveStrategy moveStrategy;

    public Race(List<Car> cars, int attempts, MoveStrategy moveStrategy) {
        this.cars = cars;
        this.attempts = attempts;
        this.moveStrategy = moveStrategy;
    }

    public void moveCars() {
        cars.stream().forEach(car -> {
            if (moveStrategy.movable()) {
                car.move();
            }
        });
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Car> getCars() {
        return cars;
    }
}
