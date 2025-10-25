package racingcar;

import java.util.List;
import racingcar.domain.Race;
import racingcar.service.RaceService;
import racingcar.strategy.RandomMoveStrategy;
import racingcar.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        RaceService raceService = new RaceService();

        List<String> carNames = inputView.readCarNames();
        int attemptCount = inputView.readAttemptCount();
        inputView.close();

        Race race = raceService.createRace(carNames, attemptCount, new RandomMoveStrategy());
        raceService.startRace(race);
    }
}
