package com.maximov.quiz.exception;

/**
 * Runtime exception для обработки в ExceptionController
 */
public class GeneralException extends RuntimeException {

    /**
     * Сообщение для пользователя
     */
    private String userMessage;

    public GeneralException(String userMessage, Exception e) {
        super(e);
        this.userMessage = userMessage;
    }

    public GeneralException(String userMessage) {
        this.userMessage = userMessage;
    }
}
