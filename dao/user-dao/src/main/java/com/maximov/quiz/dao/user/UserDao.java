package com.maximov.quiz.dao.user;

import com.maximov.quiz.entity.user.User;

/**
 * Сервис получение данных о пользователе из БД
 */
public interface UserDao {

    /**
     * Поиск пользователя в БД
     * @param login логин пользователя
     * @return пользователь
     */
    User getUser(String login);
}
