package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Race;

public class RaceService {

    public Race createRace(List<String> carsName, int attempts) {
        List<Car> cars = carsName.stream().map(Car::new).toList();

        return new Race(cars, attempts);
    }
}
