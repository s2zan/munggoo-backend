package com.mashup.munggoo.quiz;

import kr.co.shineware.nlp.komoran.model.Token;

import java.util.ArrayList;
import java.util.List;

public class GeneratedQuizDto {
    public List<Token> selected;
    public List<Token> preliminary;

    public GeneratedQuizDto(){
        selected = new ArrayList<Token>();
        preliminary = new ArrayList<Token>();
    }
}
