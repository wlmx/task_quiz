package com.maximov.quiz.service.admin;

import com.maximov.quiz.dao.QuizDao;
import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.entity.Question;
import com.maximov.quiz.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса администрирования
 */
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    /**
     * Начальный индекс по умолчанию
     */
    private static final int DEFAULT_FIRST_INDEX = 0;
    /**
     * Количество записей на страницы по умолчанию
     */
    private static final int DEFAULT_COUNT = 10;

    @Autowired
    private QuizDao quizDao;

    @Override
    @Transactional
    public List<QuestionModel> getQuestions(ListModel listModel) {
        int firstIndex;
        int count;
        if (listModel == null) {
            logger.info("Не заполнена списковая структура, используются значения по умолчанию");
            firstIndex = DEFAULT_FIRST_INDEX;
            count = DEFAULT_COUNT;
        } else {
            int pageNumber = listModel.getPageNumber();
            count = listModel.getItemsByPage();
            firstIndex = (pageNumber - 1) * count;
        }
        List<Question> questionsList = quizDao.getQuestions(firstIndex, count);
        if (CollectionUtils.isEmpty(questionsList)) {
            return Collections.emptyList();
        }
        List<QuestionModel> questionModels = new ArrayList<>();
        for (Question question : questionsList) {
            QuestionModel questionModel = QuestionModelConverter.convert(question, true);
            questionModels.add(questionModel);
        }
        return questionModels;
    }

    @Override
    @Transactional
    public QuestionModel addQuestion(QuestionModel questionModel) {
        Question question = new Question();
        QuestionModelConverter.fill(question, questionModel);
        quizDao.addQuestion(question);
        return QuestionModelConverter.convert(question, true);
    }

    @Override
    @Transactional
    public QuestionModel updateQuestion(QuestionModel questionModel) {
        Long questionId = questionModel.getId();
        Question question = quizDao.getQuestionById(questionId);
        if (question == null) {
            throw new GeneralException("Не найден идентификатор вопроса");
        }
        QuestionModelConverter.fill(question, questionModel);
        quizDao.updateQuestion(question);
        return QuestionModelConverter.convert(question, true);
    }
}
