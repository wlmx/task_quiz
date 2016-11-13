package com.maximov.quiz.service.admin;

import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;

import java.util.List;

/**
 * Сервис администрирования
 */
public interface AdminService {

    /**
     * Получение списка вопросов
     * @param listModel модель списка
     * @return Список вопросов
     */
    List<QuestionModel> getQuestions(ListModel listModel);

    /**
     * Добавление вопроса
     * @param questionModel модель вопроса
     * @return добавленный/отредактированный вопрос
     */
    QuestionModel addQuestion(QuestionModel questionModel);

    /**
     * Редактирование вопроса
     * @param questionModel модель вопроса
     * @return добавленный/отредактированный вопрос
     */
    QuestionModel updateQuestion(QuestionModel questionModel);
}
