package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.dto.GameRequest;
import racingcar.service.RaceService;
import racingcar.strategy.RandomMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;


public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RaceService raceService;

    public GameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.raceService = new RaceService();
    }

    public GameRequest readInput() {
        List<String> carNames = inputView.readCarNames();
        int attempts = inputView.readAttempts();
        inputView.close();

        return new GameRequest(carNames, attempts);
    }

    public Race play(GameRequest gameRequest) {
        Race race = raceService.createRace(gameRequest.carNames(), gameRequest.attempts(), new RandomMoveStrategy());
        raceService.startRace(race, outputView::printMoveStatus);

        return race;
    }

    public void showResult(Race race) {
        List<Car> winners = race.getWinners();

        outputView.printWinnerNames(winners);
    }
}


