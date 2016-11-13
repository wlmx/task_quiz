package com.maximov.quiz.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность вопрос
 */
@Entity
@Table(name = "QUESTION", indexes = {@Index(name = "IDX_TYPE", columnList = "type")})
public class Question {

    /**
     * Идентификатор вопроса
     */
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_seq")
    @SequenceGenerator(name = "question_id_seq", sequenceName = "question_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Формулировка вопроса
     */
    @Column(name = "TEXT", length = 4000)
    private String text;

    /**
     * Тип вопроса
     */
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AnswersType type;

    /**
     * Варианты ответа
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    /**
     * Результаты ответов пользователей и гостей
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private List<QuestionResult> results = new ArrayList<>();

    /**
     * Для отпимистичной блокировки
     */
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswersType getType() {
        return type;
    }

    public void setType(AnswersType type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<QuestionResult> getResults() {
        return results;
    }

    public void setResults(List<QuestionResult> results) {
        this.results = results;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
