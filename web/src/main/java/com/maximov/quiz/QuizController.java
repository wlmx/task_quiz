package com.maximov.quiz;

import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.maximov.quiz.QuizController.QUIZ_ENDPOINT;

/**
 * REST контроллер опросника
 */
@Controller
@RequestMapping(name = QUIZ_ENDPOINT)
public class QuizController {

    public static final String QUIZ_ENDPOINT = "/quiz";

    @Autowired
    private QuizService quizService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<QuestionModel> getQuestions(@RequestBody ListModel listModel) {
        return quizService.getQuestions(listModel);
    }
}
