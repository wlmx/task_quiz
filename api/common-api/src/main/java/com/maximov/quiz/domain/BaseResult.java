package com.maximov.quiz.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация базового результат для API
 */
public final class BaseResult implements BaseModel {

    /**
     * Признак успешности запроса
     */
    private boolean success;

    /**
     * Сообщения для вывода пользователю
     */
    private List<String> messages = new ArrayList<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
