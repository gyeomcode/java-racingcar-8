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
        cars.forEach(car -> {
            if (moveStrategy.movable()) {
                car.move();
            }
        });
    }

    public void printMoveStatus() {
        cars.forEach(System.out::println);
        System.out.println();
    }

    public List<Car> getWinners() {
        int maxDistance = getMaxDistance();

        return cars.stream().filter(car -> car.getDistance() == maxDistance).toList();
    }

    public int getMaxDistance() {
        return cars.stream().mapToInt(Car::getDistance).max().orElse(0);
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Car> getCars() {
        return cars;
    }
}
