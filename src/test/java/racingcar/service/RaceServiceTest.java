package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Race;

class RaceServiceTest {
    private final RaceService raceService = new RaceService();

    @Test
    @DisplayName("자동차 리스트를 Car로 매핑하고 Race를 생성한다.")
    void 경주_생성() {
        List<String> carNames = List.of("pobi", "woni");
        int attempts = 3;

        Race race = raceService.createRace(carNames, attempts);

        assertThat(race.getAttempts()).isEqualTo(3);
        assertThat(race.getCars())
                .hasSize(2)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni");
    }
}