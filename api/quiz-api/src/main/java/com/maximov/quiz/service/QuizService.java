package com.maximov.quiz.service;

import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;

import java.util.List;

/**
 * Сервис опросника
 */
public interface QuizService {

    /**
     * Получение списка вопросов
     * @param listModel модель списка
     * @return список моделей вопросов
     */
    List<QuestionModel> getQuestions(ListModel listModel);

    /**
     * Отправка ответа на вопрос
     * @param questionModel модель вопроса
     */
    void submitAnswer(QuestionModel questionModel);
}
