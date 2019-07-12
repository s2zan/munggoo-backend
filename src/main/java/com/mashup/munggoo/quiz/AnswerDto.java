package com.mashup.munggoo.quiz;

import lombok.Getter;

@Getter
public class AnswerDto {
    private String userAnswer;
    private String realAnswer;
    private Integer mark;

    public AnswerDto(ReqResultDto reqResultDto, Quiz quiz){
        userAnswer = reqResultDto.getUserAnswer();
        realAnswer = quiz.getContent();
        String tempUser = userAnswer.replace(" ", "").toLowerCase();
        String tempReal = realAnswer.replace(" ", "").toLowerCase();
        mark = tempUser.equals(tempReal)? 1 : 0;

        if(mark == 0 && tempReal.contains("(") && tempReal.contains(")")){
            String[] temp = tempReal.split("[\\(\\)]");
            mark = (tempUser.equals(temp[0]) ||tempUser.equals(temp[1]))? 1 : 0;
        }
    }

    public AnswerDto from(ReqResultDto reqResultDto, Quiz quiz){
        return new AnswerDto(reqResultDto, quiz);
    }
}
