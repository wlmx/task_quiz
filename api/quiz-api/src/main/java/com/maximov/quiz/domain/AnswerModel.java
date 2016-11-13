package com.maximov.quiz.domain;

/**
 * Модель данных "Ответ"
 */
public class AnswerModel implements BaseModel {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Правильный ли ответ/либо выбор пользователя
     */
    private Boolean correct;

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

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
