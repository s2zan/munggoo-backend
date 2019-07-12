package com.mashup.munggoo.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    private Integer score;
    private Integer perfectScore;
    private List<AnswerDto> result;

    public ScoreDto(List<AnswerDto> anwerDtoList){
        score = (int) anwerDtoList.stream().filter(answerDto -> answerDto.getMark() == 1).count();
        perfectScore = anwerDtoList.size();
        result = anwerDtoList;
    }

}
