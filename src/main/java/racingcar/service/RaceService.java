package racingcar.service;

import java.util.List;
import java.util.stream.IntStream;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.strategy.MoveStrategy;

public class RaceService {

    public Race createRace(List<String> carsName, int attempts, MoveStrategy moveStrategy) {
        List<Car> cars = carsName.stream().map(Car::new).toList();

        return new Race(cars, attempts, moveStrategy);
    }

    public void startRace(Race race) {
        System.out.println("\n실행 결과");
        
        IntStream.range(0, race.getAttempts()).forEach(i -> {
            race.moveCars();
            race.printMoveStatus();
        });
    }
}
