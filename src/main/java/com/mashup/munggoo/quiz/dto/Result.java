package com.mashup.munggoo.quiz.dto;

import com.mashup.munggoo.quiz.domain.Quiz;
import lombok.Getter;

@Getter
public class Result {
    private String userAnswer;
    private String realAnswer;
    private Integer mark;

    public Result(ReqAnswerDto reqAnswerDto, Quiz quiz){
        userAnswer = reqAnswerDto.getUserAnswer();
        realAnswer = quiz.getContent();
        String tempUser = userAnswer.replace(" ", "").toLowerCase();
        String tempReal = realAnswer.replace(" ", "").toLowerCase();
        mark = tempUser.equals(tempReal)? 1 : 0;

        if(mark == 0 && tempReal.contains("(") && tempReal.contains(")")){
            String[] temp = tempReal.split("[\\(\\)]");
            mark = (tempUser.equals(temp[0]) ||tempUser.equals(temp[1]))? 1 : 0;
        }
    }
}
