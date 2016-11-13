package com.maximov.quiz.admin;

import com.maximov.quiz.domain.ListModel;
import com.maximov.quiz.domain.QuestionModel;
import com.maximov.quiz.domain.user.UserModel;
import com.maximov.quiz.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.maximov.quiz.admin.AdminController.ADMIN_ENDPOINT;

/**
 * REST контроллер администрирования
 */
@Controller
@RequestMapping(value = ADMIN_ENDPOINT)
public class AdminController {

    public static final String ADMIN_ENDPOINT = "/admin";
    public static final String QUESTION_ENDPOINT = "/question";

    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<QuestionModel> getQuestions(@RequestBody ListModel listModel) {
        return adminService.getQuestions(listModel);
    }

    @RequestMapping(value = QUESTION_ENDPOINT, method = RequestMethod.PUT)
    @ResponseBody
    public QuestionModel addQuestion(@RequestBody QuestionModel questionModel) {
        return adminService.addQuestion(questionModel);
    }

    @RequestMapping(value = QUESTION_ENDPOINT, method = RequestMethod.POST)
    @ResponseBody
    public QuestionModel updateQuestion(@RequestBody QuestionModel questionModel) {
        return adminService.updateQuestion(questionModel);
    }
}
