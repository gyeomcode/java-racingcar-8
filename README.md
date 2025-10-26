# java-racingcar-precourse

## 기능 목록

### 입력

- [X] 경주할 자동차 이름들을 쉼표(,) 기준으로 구분하여 입력한다
    - [X] 자동차 이름 앞뒤 공백을 제거한다
    - [X] 자동차 이름들을 리스트로 변환한다
    - [X] 잘못된 값을 입력한 경우 예외가 발생한다
        - [X] 자동차 이름이 빈 문자열인지 확인한다
        - [X] 자동차 이름이 5자 이하인지 확인한다
        - [X] 자동차 리스트의 길이가 1 이상인지 확인한다
- [X] 시도할 횟수를 입력한다
    - [X] 잘못된 값을 입력한 경우 예외가 발생한다
        - [X] 시도할 횟수가 0포함 자연수인지 확인한다

### 경주

- [X] 자동차 이름과 시도할 횟수로 경주를 생성한다
- [X] 시도할 횟수만큼 경주을 반복한다
    - [X] 모든 자동차를 순회하며 0에서 9 사이에서 무작위 값을 구한다
        - [X] 무작위 값이 4 이상일 경우 전진 횟수가 증가한다
    - [X] 실행 결과를 출력한다
- [X] 전진 횟수가 가장 많은 자동차들을 찾는다

### 출력

- [X] 최종 우승자를 출력한다

## 패키지 구조 설계

| 구성 요소                      | 설명                                        | 
|----------------------------|-------------------------------------------|
| Controller                 | 사용자 요청을 받아 Service에 전달하고, 처리 결과를 View에 전달 |
| Service                    | 비즈니스 로직 수행                                |
| View                       | 사용자가 볼 화면(UI) 또는 출력 결과를 표현                |
| Domain                     | 시스템이 다루는 핵심 개념과 비즈니스 규칙을 모델링한 엔티티를 정의     |
| DTO (Data Transfer Object) | Controller ↔ Service ↔ View 간 데이터 전달을 담당  |
| strategy                   | 자동차 이동 여부 판단 전략                           |

## 트러블 슈팅

### 전략 패턴을 활용한 이동 가능 여부 판단

레이싱 게임에서는 자동차가 매 라운드마다 “이동할지 말지”를 결정해야 한다. 문제는 이 결정이 Random에 의존한다는 점이었다.

초기 구현은 단순히 Race 클래스 내부에 이동 여부를 결정하는 로직을 작성했다.

```
int number = Randoms.pickNumberInRange(0, 9);
if(number >= MOVE_THRESHOLD){
    car.move();
}
```

하지만 이 방식은 난수를 생성하는 로직이 내부에 존재해 테스트 코드 작성이 까다로웠다. 따라서 이를 해결하기 위해 전략 패턴을 도입했다. Race 클래스는 외부에서 주입받은 전략 객체에 이동 여부 결정을 위임한다.

```
Race race = raceService.createRace(gameRequest.carNames(), gameRequest.attempts(), new RandomMoveStrategy());

...

public void moveCars() {
    cars.forEach(car -> {
        if (moveStrategy.movable()) {
            car.move();
        }
    });
}
```

이제 결정적인(Deterministic) 전략을 통해 테스트 결과를 예측 가능하게 만들 수 있다.

```
public class AlwaysMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable() {
        return true;
    }
}

public class NeverMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable() {
        return false;
    }
}

Race race = new Race(cars, 1, new AlwaysMoveStrategy());
Race race = new Race(cars, 1, new NeverMoveStrategy());
```

### 함수형 인터페이스를 활용한 onRoundEnd 콜백

레이싱 게임에서는 각 라운드가 끝날 때마다 자동차들의 이동 상태를 출력해야 했다. 처음에는 RaceSerive가 직접 OutputView.printMoveStatus()를 호출했다. 하지만 이 방식은 구조적으로
Service가 View를 직접 알고 있다는 문제가 있었다. 즉, 비즈니스 로직이 출력 로직에 의존하는 구조였다.

```
public void startRace(Race race) {
    System.out.println("\n실행 결과");

    IntStream.range(0, race.getAttempts()).forEach(i -> {
        race.moveCars();
        outputView.printMoveStatus(race.getCars());
    });
}
```

그래서 함수형 인터페이스를 활용해 startRace()가 라운드가 끝났을 때 실행할 행동을 외부에서 주입받도록 변경했다.

```
public void startRace(Race race, Consumer<List<Car>> onRoundEnd) {
    System.out.println("\n실행 결과");

    IntStream.range(0, race.getAttempts()).forEach(i -> {
        race.moveCars();
        onRoundEnd.accept(race.getCars());
    });
}
    
raceService.startRace(race, outputView::printMoveStatus);
```