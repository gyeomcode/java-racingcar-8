package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
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
    @DisplayName("경주가 끝나면 자동차들의 이동 결과와 우승자가 올바르게 계산된다.")
    void 경주_완료() {
        int attempts = 2;
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"), new Car("yordle"));
        Race race = new Race(cars, attempts, new RandomMoveStrategy());

        IntStream.range(0, attempts).forEach(i -> {
            race.getCars().get(0).move();
            race.getCars().get(1).move();
        });

        assertThat(race.getCars().get(0).toString()).isEqualTo("pobi : --");
        assertThat(race.getCars().get(1).toString()).isEqualTo("woni : --");
        assertThat(race.getMaxDistance()).isEqualTo(2);
        assertThat(race.getWinners()).containsExactly(race.getCars().get(0), race.getCars().get(1));
    }
}