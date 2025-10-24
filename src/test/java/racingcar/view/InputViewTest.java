package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    private final InputView inputView = new InputView();

    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni,jun", " pobi,woni , jun"})
    @DisplayName("자동차 이름을 trim하고 리스트로 변환한다.")
    void 자동차이름_리스트_파싱(String input) {
        List<String> result = inputView.parseCarNames(input);

        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", ",,", "pobi, ,woni", "pobi,javaji"})
    @DisplayName("올바르지 않은 자동차 이름은 예외가 발생한다.")
    void 자동차이름_예외(String input) {
        assertThatThrownBy(() -> inputView.parseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}