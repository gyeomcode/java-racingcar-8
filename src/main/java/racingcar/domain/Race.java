package racingcar.domain;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Race {
    private List<Car> cars;
    private int attempts;

    public Race(List<Car> cars, int attempts) {
        this.cars = cars;
        this.attempts = attempts;
    }

    public void moveCars() {
        cars.stream().forEach(car -> {
            int pickNumber = Randoms.pickNumberInRange(0, 9);
        });
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Car> getCars() {
        return cars;
    }
}
