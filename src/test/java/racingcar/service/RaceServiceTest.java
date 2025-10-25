package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Race;
import racingcar.strategy.AlwaysMoveStrategy;
import racingcar.strategy.NeverMoveStrategy;
import racingcar.strategy.RandomMoveStrategy;


class RaceServiceTest {
    private final RaceService raceService = new RaceService();

    @Test
    @DisplayName("자동차 리스트를 Car로 매핑하고 Race를 생성한다.")
    void 경주_생성() {
        List<String> carNames = List.of("pobi", "woni");
        int attempts = 3;

        Race race = raceService.createRace(carNames, attempts, new RandomMoveStrategy());

        assertThat(race.getAttempts()).isEqualTo(3);
        assertThat(race.getCars())
                .hasSize(2)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni");
    }

    @Test
    @DisplayName("movable이 true이면 자동차가 이동한다.")
    void 자동차_이동() {
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Race race = new Race(cars, 1, new AlwaysMoveStrategy());

        race.moveCars();

        assertThat(race.getCars().get(0).getDistance()).isEqualTo(1);
        assertThat(race.getCars().get(1).getDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("movable이 false이면 자동차는 이동하지 않는다.")
    void 자동차_멈춤() {
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Race race = new Race(cars, 1, new NeverMoveStrategy());

        race.moveCars();

        assertThat(race.getCars().get(0).getDistance()).isEqualTo(0);
        assertThat(race.getCars().get(1).getDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("이동한 결과 출력한다.")
    void 이동결과_출력() {
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Race race = new Race(cars, 1, new AlwaysMoveStrategy());

        race.moveCars();

        assertThat(race.getCars().get(0).toString()).isEqualTo("pobi : -");
        assertThat(race.getCars().get(1).toString()).isEqualTo("woni : -");
    }
}