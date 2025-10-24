package racingcar.service;

import java.util.List;
import java.util.stream.IntStream;
import racingcar.domain.Car;
import racingcar.domain.Race;

public class RaceService {

    public Race createRace(List<String> carsName, int attempts) {
        List<Car> cars = carsName.stream().map(Car::new).toList();

        return new Race(cars, attempts);
    }

    public void startRace(Race race) {
        IntStream.range(0, race.getAttempts()).forEach(i -> race.moveCars());
    }
}
