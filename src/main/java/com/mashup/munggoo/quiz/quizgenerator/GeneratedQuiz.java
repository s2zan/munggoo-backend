package com.mashup.munggoo.quiz.quizgenerator;

import kr.co.shineware.nlp.komoran.model.Token;

import java.util.ArrayList;
import java.util.List;

public class GeneratedQuiz {
    public List<Token> selected;
    public List<Token> preliminary;

    public GeneratedQuiz(){
        selected = new ArrayList<Token>();
        preliminary = new ArrayList<Token>();
    }
}
