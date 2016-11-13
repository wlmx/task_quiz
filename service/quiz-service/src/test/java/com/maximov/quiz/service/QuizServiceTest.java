package com.maximov.quiz.service;

import com.maximov.quiz.dao.QuizDao;
import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.entity.AnswersType;
import com.maximov.quiz.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit тест сервиса QuizService
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

    @Mock
    private QuizDao quizDao;

    @InjectMocks
    private QuizServiceImpl quizService = new QuizServiceImpl();

    @Test
    public void testGetQuestions() {
        ListModel listModel1 = new ListModel();
        listModel1.setPageNumber(1);
        listModel1.setItemsByPage(10);

        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setId(2L);
        question1.setText("Вопрос?");
        Question question2 = new Question();
        question2.setId(1L);
        question2.setType(AnswersType.SINGLE);
        questions.add(question1);
        questions.add(question2);

        ListModel listModel2 = new ListModel();
        listModel2.setPageNumber(3);
        listModel2.setItemsByPage(1);

        when(quizDao.getQuestions(eq(0), anyInt())).thenReturn(questions);
        when(quizDao.getQuestions(gt(3), anyInt())).thenReturn(Collections.emptyList());
        List<QuestionModel> questionModels1 = quizService.getQuestions(listModel1);
        Assert.assertEquals("Размер выборки отличается!", questions.size(), questionModels1.size());

        QuestionModel questionModel1 = questionModels1.get(0);
        QuestionModel questionModel2 = questionModels1.get(1);

        Assert.assertEquals("Идентификаторы отличаются у первого объекта!", question1.getId(), questionModel1.getId());
        Assert.assertEquals("Идентификаторы отличаются у второго объекта!", question2.getId(), questionModel2.getId());

        Assert.assertEquals("Текст вопроса отличается", question1.getText(), questionModel1.getText());
        Assert.assertEquals("Тип вопроса отличается", AnswersType.MULTIPLE == question2.getType(), questionModel2.getMultiChoose());

        List<QuestionModel> questionModels2 = quizService.getQuestions(listModel2);
        Assert.assertEquals("Размер выборки отличается! Должна быть пуста!", 0, questionModels2.size());
    }
}
