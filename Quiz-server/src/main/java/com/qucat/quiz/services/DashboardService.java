package com.qucat.quiz.services;

import com.qucat.quiz.repositories.dao.implementation.DashboardDaoImpl;
import com.qucat.quiz.repositories.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DashboardService {
    @Autowired
    private DashboardDaoImpl dashboardDao;

    public List<User> getTopUsers(int limit) {
        List<User> users = dashboardDao.getTopUsers(limit);
        if (users.isEmpty()) {
            log.warn("There are no users");
            return null;
        } else {
            return users;
        }
    }

    public User getBestUserInTheQuiz(int quizId) {
        User user = dashboardDao.getBestUserByQuizId(quizId);
        if (user == null) {
            log.error("Such user doesn`t exist");
            return null;
        } else {
            return user;
        }
    }

    public List<CategoryStatistics> getStatisticInTheCategory(String login) {
        List<CategoryStatistics> categoryStatistics = dashboardDao.getStatisticInTheCategory(login);
        if (categoryStatistics.isEmpty()) {
            log.warn("Statistic is empty");
            return null;
        } else {
            return categoryStatistics;
        }
    }

    public List<Statistics> getPercentOfCorrectAnswers(String login) {
        List<Statistics> correctAnswers = dashboardDao.getPercentOfCorrectAnswers(login);
        if (correctAnswers.isEmpty()) {
            log.warn("Can`t get information about correct answers");
            return null;
        } else {
            return correctAnswers;
        }
    }

    public BestQuiz getTheMostSuccessQuiz(String login) {
        BestQuiz bestQuiz = dashboardDao.getTheMostSuccessfulQuiz(login);
        if (bestQuiz == null) {
            log.error("Such user doesn`t exist");
            return null;
        } else {
            return bestQuiz;
        }
    }

    public List<ComparedScores> getComparedScores(String login) {
        List<ComparedScores> comparedScores = dashboardDao.getComparedScores(login);
        if (comparedScores.isEmpty()) {
            log.warn("Can`t get information about scores");
            return null;
        } else {
            return comparedScores;
        }
    }

    public List<Statistics> getFriendsPreferences(int userId) {
        List<Statistics> friendsPreferences = dashboardDao.getFriendsPreferences(userId);
        if (friendsPreferences.isEmpty()) {
            log.warn("Can`t get information about friends` preferences");
            return null;
        } else {
            return friendsPreferences;
        }
    }

    public List<Statistics> getStatisticOfQuizzesPlayed() {
        List<Statistics> playedQuizzes = dashboardDao.getStatisticOfQuizzesPlayed();
        if (playedQuizzes.isEmpty()) {
            log.warn("There are no quizzes played");
            return null;
        } else {
            return playedQuizzes;
        }
    }

    public List<AdminStatistics> getAmountOfCreatedAndPublishedQuizzes() {
        List<AdminStatistics> adminStatistics = dashboardDao.getAmountOfCreatedAndPublishedQuizzes();
        if (adminStatistics.isEmpty()) {
            log.warn("There are no created or published quizzes");
            return null;
        } else {
            return adminStatistics;
        }
    }
}