package racingcar.service;

import java.util.List;
import racingcar.domain.Car;

@FunctionalInterface
public interface RoundCallback {
    void onRoundEnd(List<Car> cars);
}
