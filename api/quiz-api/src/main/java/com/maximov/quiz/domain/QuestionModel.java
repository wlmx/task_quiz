package com.maximov.quiz.domain;

import java.util.List;

/**
 * Модель данных "Вопрос"
 */
public class QuestionModel implements BaseModel {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Список вариантов ответа
     */
    private List<AnswerModel> answers;

    /**
     * Тип вопроса
     */
    private Boolean multiChoose;

    /**
     * Заполняется при наличии ответа
     * null - нет данных о правильности ответа
     * true - был дан верный ответ
     * false - ответ был дан неверно
     */
    private Boolean result;

    /**
     * Текст вопроса
     */
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public Boolean getMultiChoose() {
        return multiChoose;
    }

    public void setMultiChoose(Boolean multiChoose) {
        this.multiChoose = multiChoose;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
