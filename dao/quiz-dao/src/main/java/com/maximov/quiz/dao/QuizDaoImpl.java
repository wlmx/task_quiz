package com.maximov.quiz.dao;

import com.maximov.quiz.entity.Question;
import com.maximov.quiz.entity.QuestionResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализация сервиса получения данных опросника из БД
 */
public class QuizDaoImpl implements QuizDao {

    private SessionFactory sessionFactory;

    public QuizDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.save(question);
    }

    @Override
    public void updateQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.update(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Session session = sessionFactory.getCurrentSession();
        Question question = new Question();
        question.setId(questionId);
        session.delete(question);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Question.class, questionId);
    }

    @Override
    public List<Question> getQuestions(int firstIndex, int count) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> questionRoot = criteriaQuery.from(Question.class);
        criteriaQuery.select(questionRoot);
        Query<Question> query = session.createQuery(criteriaQuery);
        query.setFirstResult(firstIndex);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public void addQuestionResult(QuestionResult questionResult) {
        Session session = sessionFactory.getCurrentSession();
        session.save(questionResult);
    }
}
