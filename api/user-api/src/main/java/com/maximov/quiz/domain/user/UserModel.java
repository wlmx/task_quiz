package com.maximov.quiz.domain.user;

import com.maximov.quiz.domain.BaseModel;

/**
 * Модель данных "Пользователь"
 */
public class UserModel implements BaseModel {

    /**
     * Логин пользователя
     */
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
