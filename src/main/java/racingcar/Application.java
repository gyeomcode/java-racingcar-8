package racingcar;

import racingcar.controller.GameController;
import racingcar.domain.Race;
import racingcar.dto.GameRequest;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        GameRequest gameRequest = gameController.readInput();
        Race race = gameController.play(gameRequest);
        gameController.showResult(race);
    }
}
