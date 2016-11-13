package com.maximov.quiz.service;

import com.maximov.quiz.dao.QuizDao;
import com.maximov.quiz.domain.AnswerModel;
import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.entity.Answer;
import com.maximov.quiz.entity.AnswersType;
import com.maximov.quiz.entity.Question;
import com.maximov.quiz.entity.QuestionResult;
import com.maximov.quiz.exception.GeneralException;
import com.maximov.quiz.service.admin.QuestionModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса опросника
 */
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;

    @Override
    public List<QuestionModel> getQuestions(ListModel listModel) {
        int firstIndex;
        int count;
        if (listModel == null) {
            throw new GeneralException("Не задана модель списка");
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
    public void submitAnswer(QuestionModel questionModel) {
        Long questionId = questionModel.getId();
        Question question = quizDao.getQuestionById(questionId);
        if (question == null) {
            throw new GeneralException("Не найден вопрос");
        }
        List<Long> pickedAnswers = getPickedAnswersIds(questionModel);
        boolean multiChoose = question.getType() == AnswersType.MULTIPLE;
        if (!multiChoose && pickedAnswers.size() > 1) {
            throw new GeneralException("Выбрано более одного ответа при альтернативном выборе");
        }
        List<Long> correctAnswers = getCorrectAnswersIds(question);
        QuestionResult questionResult = new QuestionResult();
        questionResult.setQuestion(question);
        boolean isSuccess;
        if (pickedAnswers.size() != correctAnswers.size()) {
            isSuccess = false;
        } else {
            Collections.sort(pickedAnswers);
            Collections.sort(correctAnswers);
            isSuccess = pickedAnswers.equals(correctAnswers);
        }
        questionResult.setSuccess(isSuccess);
        quizDao.addQuestionResult(questionResult);
    }

    private List<Long> getPickedAnswersIds(QuestionModel questionModel) {
        List<Long> pickedAnswers = new ArrayList<>();
        List<AnswerModel> answers = questionModel.getAnswers();
        if (CollectionUtils.isEmpty(answers)) {
            throw new GeneralException("Ответы отсутсвуют");
        }
        for (AnswerModel answerModel : answers) {
            if (answerModel.getCorrect()) {
                pickedAnswers.add(answerModel.getId());
            }
        }
        if (CollectionUtils.isEmpty(pickedAnswers)) {
            throw new GeneralException("Не выбран ни один ответ");
        }
        return pickedAnswers;
    }

    private List<Long> getCorrectAnswersIds(Question question) {
        List<Answer> questionAnswers = question.getAnswers();
        if (CollectionUtils.isEmpty(questionAnswers)) {
            throw new IllegalArgumentException("Отсутвуют ответы на вопрос");
        }
        List<Long> correctAnswers = new ArrayList<>();
        for (Answer answer : questionAnswers) {
            if (answer.getCorrect()) {
                correctAnswers.add(answer.getId());
            }
        }
        if (CollectionUtils.isEmpty(correctAnswers)) {
            throw new IllegalArgumentException("Отсутвуют правильные ответы на вопрос");
        }
        return correctAnswers;
    }
}