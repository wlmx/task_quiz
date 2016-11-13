package com.maximov.quiz.service.admin;

import com.maximov.quiz.domain.AnswerModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.entity.Answer;
import com.maximov.quiz.entity.AnswersType;
import com.maximov.quiz.entity.Question;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс конвертирования
 */
public final class QuestionModelConverter {

    private QuestionModelConverter() {

    }

    /**
     * Конвертирование сущности вопрос в модель
     * @param question сущность вопрос
     * @param isAdmin заполнять ли поля доступные для администрации
     * @return модель вопрос
     */
    public static QuestionModel convert(Question question, boolean isAdmin) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(question.getId());
        questionModel.setMultiChoose(question.getType() == AnswersType.MULTIPLE);
        questionModel.setText(question.getText());
        List<Answer> answers = question.getAnswers();
        if (CollectionUtils.isEmpty(answers)) {
            return questionModel;
        }
        List<AnswerModel> answerModels = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerModel answerModel = convert(answer, isAdmin);
            answerModels.add(answerModel);
        }
        questionModel.setAnswers(answerModels);
        return questionModel;
    }

    /**
     * Конвертирование сущности ответ в модель
     * @param answer сущность ответ
     * @param isAdmin заполнять ли поля доступные для администрации
     * @return модель ответ
     */
    public static AnswerModel convert(Answer answer, boolean isAdmin) {
        AnswerModel answerModel = new AnswerModel();
        answerModel.setId(answer.getId());
        answerModel.setText(answer.getText());
        if (isAdmin) {
            answerModel.setCorrect(answer.getCorrect());
        }
        return answerModel;
    }

    /**
     * Заполнение сущности вопрос на основе модели
     * @param question сущность вопрос
     * @param questionModel модель
     */
    public static void fill(Question question, QuestionModel questionModel) {
        question.setType(questionModel.getMultiChoose() ? AnswersType.MULTIPLE : AnswersType.SINGLE);
        question.setText(questionModel.getText());
        List<AnswerModel> answersModels = questionModel.getAnswers();
        if (CollectionUtils.isEmpty(answersModels)) {
            question.setAnswers(new ArrayList<>());
            return;
        }
        List<Answer> answers = question.getAnswers();
        if (CollectionUtils.isEmpty(answers)) {
            answers = new ArrayList<>();
        }
        for (AnswerModel answerModel : answersModels) {
            fill(answers, answerModel);
        }
        question.setAnswers(answers);
    }

    /**
     * Заполнение сущности Ответ на основе модели
     * @param answers список ответов из вопроса
     * @param answerModel модель ответа
     */
    public static void fill(List<Answer> answers, AnswerModel answerModel) {
        Answer foundAnswer = null;
        for (Answer answer : answers) {
            if (answer.getId().equals(answerModel.getId())) {
                foundAnswer = answer;
                break;
            }
        }
        if (foundAnswer == null) {
            foundAnswer = new Answer();
            answers.add(foundAnswer);
        }
        foundAnswer.setCorrect(answerModel.getCorrect());
        foundAnswer.setText(answerModel.getText());
    }
}
