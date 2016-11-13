package com.maximov.quiz.dao;

import com.maximov.quiz.entity.Question;
import com.maximov.quiz.entity.QuestionResult;

import java.util.List;

/**
 * Сервис получения данных опросника из БД
 */
public interface QuizDao {

    /**
     * Добавить вопрос
     * @param question вопрос
     */
    void addQuestion(Question question);

    /**
     * Обновить вопрос
     * @param question вопрос
     */
    void updateQuestion(Question question);

    /**
     * Удалить вопрос
     * @param questionId идентификатор вопроса
     */
    void deleteQuestion(Long questionId);

    /**
     * Получить вопрос по id
     * @param questionId идентификатор
     * @return вопрос
     */
    Question getQuestionById(Long questionId);

    /**
     * Получение списка вопросов, отсортированных в порядке создания
     * сортировка на уровне БД по id
     * @param firstIndex начальный индекс отбора
     * @param count количество
     * @return список вопросов
     */
    List<Question> getQuestions(int firstIndex, int count);

    /**
     * Добавить результат вопроса
     * @param questionResult результат вопроса
     */
    void addQuestionResult(QuestionResult questionResult);

}
