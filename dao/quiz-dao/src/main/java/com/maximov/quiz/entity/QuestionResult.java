package com.maximov.quiz.entity;

import com.maximov.quiz.entity.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Сущность Результат ответа на вопрос
 */
@Entity
@Table(name = "QUESTION_RESULT")
public class QuestionResult {

    /**
     * Идентификатор результата ответа
     */
    @Id
    @Column(name = "QUESTION_RES_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_res_id_seq")
    @SequenceGenerator(name = "question_res_id_seq", sequenceName = "question_res_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Вопрос на который был ответ
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    /**
     * Верен ли результат
     */
    @Column(name = "SUCCESS")
    private Boolean success;

    /**
     * Пользователь, может быть null,
     * в случае Гостя
     */
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
