package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

    }

    private void printRequestingCarNameInput() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    private String getCarNameInput() {
        return Console.readLine();
    }

    private void validateNotEmpty(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력입니다.") ;
        }
    }
}