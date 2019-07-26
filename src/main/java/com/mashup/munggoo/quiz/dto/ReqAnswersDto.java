package com.mashup.munggoo.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqAnswersDto {
    List<ReqAnswerDto> answers;
}