package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  [채점 현황]
 *
 *          1.1     1.2     1.3
 * -------------------------------
 * 1차       X       O       O
 * 2차      헷갈      O       O
 *
 */
public class Quiz1 {

    // 1.1 각 취미를 선호 하는 인원이 몇 명인지 계산 하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> result = csvLines.stream()
                .map(list -> list[1].trim().split(":"))
                .flatMap(Arrays::stream)    // flatMap은 Stream을 반환할 것을 요구함
                .collect(Collectors.toMap(
                        hobby -> hobby,
                        hobby -> 1,
                        (oldCnt, newCnt) -> oldCnt += newCnt
                ));

        return result;
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Map<String, Integer> result = csvLines.stream()
                .filter(list -> StringUtils.equals(list[0].substring(0,1), "정"))
                .map(list -> list[1].trim().split(":"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(
                        hobby -> hobby,
                        hobby -> 1,
                        (oldCnt, newCnt) -> oldCnt += newCnt
                ));
        return result;
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        Integer result = csvLines.stream()
                .map(list -> {
                    String content = list[2];
                    return (content.length() - content.replaceAll("좋아", "").length()) / 2;
                })
                .reduce(0, Integer::sum);

        return result;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
