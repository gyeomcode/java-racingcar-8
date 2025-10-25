package racingcar.dto;

import java.util.List;

public record GameRequest(List<String> carNames, int attempts) {
}
