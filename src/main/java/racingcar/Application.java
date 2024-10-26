package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Application {

    private final LinkedHashMap<String, Integer> carPositions = new LinkedHashMap<>();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Application race = new Application();

        race.readySetGo();
    }

    private void printRequestingCarNamesInput() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    private String getCarNamesInput() {
        return Console.readLine();
    }

    private void validateNotEmpty(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.") ;
        }
    }

    private String[] splitNamesByComma(String userInput) {
        return userInput.split(",");
    }

    private void validateMinimumTwoCars(String[] cars) {
        if (cars.length < 2) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
    }

    private void validateUniqueCarNames(String[] carNames) {
        if (carNames.length != carPositions.size()) {
            throw new IllegalArgumentException("자동차 이름에는 중복이 없어야 합니다.");
        }
    }

    private void validateNameLength(String carName) {
        if (carName.length() > 5) {
            throw new IllegalArgumentException("자동차의 이름은 5자 이하여야 합니다.");
        }
    }

    private void printRequestingRaceRound() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    private int getRaceRoundInput() {
        String str = Console.readLine();
        validateNotEmpty(str);
        return Integer.parseInt(str);
    }

    private void initializeCarPositions(String[] cars) {
        for (String carName : cars) {
            carPositions.put(carName, 0);
        }
    }

    private void moveCarForward(String car) {
        carPositions.put(car, carPositions.get(car) + 1);
    }

    private void printRoundResult(String car) {
        int position = carPositions.get(car);
        System.out.println(car + " : " + "-".repeat(position));
    }

    private void executeRaceRound() {
        for (String car : carPositions.keySet()) {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
                moveCarForward(car);
            }
            printRoundResult(car);
        }

        System.out.println();
    }

    private List<String> getWinnerList() {
        List<String> winner = new ArrayList<>();
        int firstPlacePosition = 0;

        for (String car : carPositions.keySet()) {
            if (carPositions.get(car) > firstPlacePosition) {
                winner.clear();
                winner.add(car);
                firstPlacePosition = carPositions.get(car);
            } else if (carPositions.get(car) == firstPlacePosition) {
                winner.add(car);
            }
        }

        return winner;
    }

    private void printWinner(List<String> winnerList) {
        String result = String.join(", ", winnerList);
        System.out.println("최종 우승자 : " + result);
    }

    private void readySetGo() {
        printRequestingCarNamesInput();
        String userCarNamesInput = getCarNamesInput();

        validateNotEmpty(userCarNamesInput);

        String[] cars = splitNamesByComma(userCarNamesInput);

        validateMinimumTwoCars(cars);
        for (String carName : cars) {
            validateNotEmpty(carName);
            validateNameLength(carName);
        }

        printRequestingRaceRound();
        int userRaceRoundInput = getRaceRoundInput();

        initializeCarPositions(cars);

        validateUniqueCarNames(cars);

        System.out.println("실행 결과");
        for (int n=0; n<userRaceRoundInput; n++) {
            executeRaceRound();
        }

        var result = getWinnerList();
        printWinner(result);
    }
}
