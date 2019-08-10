package com.mashup.munggoo.quiz.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScoreDto {
    private Integer score;
    private Integer perfectScore;
    private List<Result> result;

    public ScoreDto(List<Result> anwerDtoList){
        score = (int) anwerDtoList.stream().filter(answerDto -> answerDto.getMark() == 1).count();
        perfectScore = anwerDtoList.size();
        result = anwerDtoList;
    }
}
