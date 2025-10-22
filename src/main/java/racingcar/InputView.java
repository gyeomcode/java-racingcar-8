package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String CAR_NAME_SEPARATOR = ",";
    
    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        return parseCarNames(input);
    }

    public List<String> parseCarNames(String input) {
        List<String> carNames = Arrays.stream(input.split(CAR_NAME_SEPARATOR)).map(String::trim).toList();

        return carNames;
    }
}
