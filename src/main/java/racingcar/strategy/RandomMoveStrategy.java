package racingcar.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {
    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 9;
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean movable() {
        int number = Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE);

        return number >= MOVE_THRESHOLD;
    }
}
