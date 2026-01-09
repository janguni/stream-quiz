package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> result = csvLines.stream()
                .map(list -> list[1].replaceAll("\\s", "")) // 공백 제거
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":"))) // 콜론(:)으로 나뉜거 배열로 변경, 2차원 배열임으로 flatMap
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldCnt, newCnt) -> newCnt += oldCnt)); // 첫번째 param: key, 두번째 param: value, 세번째 param: 중복일 때 value를 어떻게 처리할건지

        return result;
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> result = csvLines.stream()
                .filter(list -> ObjectUtils.equals(list[0].charAt(0), '정')) // 필터
                .map(list -> list[1].replaceAll("\\s", ""))
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldCnt, newCnt) -> newCnt += oldCnt));
        return result;
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        int result = csvLines.stream()
                .map(list -> list[2])
                .mapToInt(content -> {
                    return (content.length() - content.replace("좋아", "").length()) / 2;
                })
                .sum();
        return result;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
