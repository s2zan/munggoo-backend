package com.mashup.munggoo.quiz.quizGenerator;

import java.util.Arrays;
import java.util.List;

public class QuizConfig {
    public static int quizNum = 20;
    public static List<String> engPassWords = Arrays.asList(new String[]{
            "is", "are",
            "or", "and",
            "but", "so",
            "it", "she", "he", "we", "i", "you",
            "her", "him", "his",
            "a"
    });
}
