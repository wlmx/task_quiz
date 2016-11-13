package com.maximov.quiz;

import com.maximov.quiz.domain.BaseResult;
import com.maximov.quiz.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Контроллер обработки исключений
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private static final String DEFAULT_ERROR_MSG = "Internal Server Error";

    @ExceptionHandler(Exception.class)
    public BaseResult handleAllException(Exception e) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);
        baseResult.getMessages().add(DEFAULT_ERROR_MSG);
        logger.error(e.getMessage(), e);
        return baseResult;
    }

    @ExceptionHandler(GeneralException.class)
    public BaseResult handleAllException(GeneralException e) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);
        baseResult.getMessages().add(e.getMessage());
        logger.error(e.getMessage(), e);
        return baseResult;
    }
}
