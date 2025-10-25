package racingcar.strategy;

public class NeverMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable() {
        return false;
    }
}
