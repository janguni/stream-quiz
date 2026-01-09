package com.mangkyu.stream.Quiz5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  [채점 현황]
 *
 *          5.1     5.2     5.3     5.4
 * ----------------------------------------
 * 1차       O       O       -       X
 * 2차       O       O       -       X
 *
 */
public class Quiz5 {

    private static final String[] STRING_ARR = {"aaa", "bb", "c", "dddd"};

    // 5.1 모든 문자열의 길이를 더한 결과를 출력 하여라.
    public int quiz1() {
        int result = Arrays.stream(STRING_ARR)
                .mapToInt(String::length)
                .sum();
        return result;
    }

    // 5.2 문자열 중에서 가장 긴 것의 길이를 출력하시오.
    public int quiz2() {
        int result = Arrays.stream(STRING_ARR)
                .mapToInt(String::length)
                .max()
                .orElse(0);
        return result;
    }

    // 5.3 임의의 로또번호(1~45)를 정렬해서 출력하시오.
    // 이건 문제가 이상함 PASS!!
    public List<Integer> quiz3() {
        return null;
    }

    // 5.4 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력 하시오.
    public List<Integer[]> quiz4() {
        // IntStream, Stream<Integer>의 차이
        List<Integer[]> result = IntStream.rangeClosed(1, 6)
                .boxed()
                .flatMap(r1 -> {
                    return IntStream.rangeClosed(1, 6)
                            .boxed()
                            .filter(r2 -> r1 + r2 == 6)
                            .map(r2 -> new Integer[]{r1, r2});
                })
                .collect(Collectors.toList());
        return result;
    }

}
