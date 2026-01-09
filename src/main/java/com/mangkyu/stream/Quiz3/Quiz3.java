package com.mangkyu.stream.Quiz3;

import java.util.*;
import java.util.stream.Collectors;

public class Quiz3 {

    private static final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> numbers2 = Arrays.asList(3, 4);

    // 3.1 모든 숫자 쌍의 배열 리스트를 반환하여라.
    // ex) numbers1 = [1,2,3], numbers2 = [3,4] -> [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)]
    public List<Integer[]> quiz1() {

//        List<Integer[]> result = new ArrayList<>();
//        for (int i = 0; i < numbers1.size(); i++) {
//            for (int j = 0; j < numbers2.size(); j++) {
//                HashSet<Integer> set = new HashSet<>();
//                set.add(numbers1.get(i));
//                set.add(numbers2.get(j));
//                result.add(set);
//            }
//        }

//        numbers1.stream()
//                .forEach(number1->
//                        numbers2.stream()
//                                .forEach(numbers2->{
//                                    Integer[] set = new Integer[2];
//                                    set[0] = (number1);
//                                    set[1] = (numbers2);
//                                    result.add(set);
//                                })
//                );

        List<Integer[]> result = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                        .map(n2 -> new Integer[]{n1, n2})
                )
                .collect(Collectors.toList());
        return result;
    }

    // 3.2 모든 숫자 쌍의 곱이 가장 큰 값을 반환하여라.
    // ex) numbers1 = [1,2,3], numbers2 = [3,4] -> 12
    public int quiz2() {
        Optional<Integer> max = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                        .map(n2 -> n1 * n2)
                )
                .max(Comparator.comparing(Integer::intValue));
        return max.get();
    }

}
