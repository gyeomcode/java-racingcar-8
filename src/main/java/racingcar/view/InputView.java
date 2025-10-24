package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String CAR_NAME_SEPARATOR = ",";
    private static final int CAR_NAME_MAX_LENGTH = 5;

    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        return parseCarNames(input);
    }

    public int readAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        return Integer.parseInt(input);
    }

    public List<String> parseCarNames(String input) {
        List<String> carNames = Arrays.stream(input.split(CAR_NAME_SEPARATOR)).map(String::trim).toList();

        validateCarNames(carNames);

        return carNames;
    }

    private void validateCarNames(List<String> carNames) {
        if (carNames.isEmpty() || carNames.stream()
                .anyMatch(carName -> carName.length() > CAR_NAME_MAX_LENGTH || carName.isEmpty())) {
            throw new IllegalArgumentException("자동차 이름이 올바르지 않습니다.");
        }
    }
}
