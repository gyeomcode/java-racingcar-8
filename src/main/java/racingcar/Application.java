package racingcar;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.service.RaceService;
import racingcar.strategy.RandomMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RaceService raceService = new RaceService();

        List<String> carNames = inputView.readCarNames();
        int attemptCount = inputView.readAttemptCount();
        inputView.close();

        Race race = raceService.createRace(carNames, attemptCount, new RandomMoveStrategy());
        raceService.startRace(race, outputView::printMoveStatus);

        List<Car> winners = race.getWinners();
        outputView.printWinnerNames(winners);
    }
}
