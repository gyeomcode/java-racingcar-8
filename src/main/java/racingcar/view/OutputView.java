package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {
    
    public void printWinnerNames(List<Car> winners) {
        String[] carNames = winners.stream().map(Car::getName).toArray(String[]::new);

        System.out.println("최종 우승자 : " + String.join(", ", carNames));
    }
}
